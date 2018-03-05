package me.zj22.gudao.server.web.service.impl;

import me.zj22.gudao.server.web.dao.db.OrderDetailMapper;
import me.zj22.gudao.server.web.dao.db.wrap.OrderDetailWrapperMapper;
import me.zj22.gudao.server.web.dao.db.wrap.OrderWrapperMapper;
import me.zj22.gudao.server.web.enums.OrderStatusEnum;
import me.zj22.gudao.server.web.enums.PayStatusEnum;
import me.zj22.gudao.server.web.enums.ResultEnum;
import me.zj22.gudao.server.web.exception.daoGuException;
import me.zj22.gudao.server.web.pojo.dto.Order;
import me.zj22.gudao.server.web.pojo.dto.OrderDTO;
import me.zj22.gudao.server.web.pojo.dto.OrderDetail;
import me.zj22.gudao.server.web.pojo.dto.ProductInfo;
import me.zj22.gudao.server.web.pojo.vo.Cart;
import me.zj22.gudao.server.web.pojo.vo.Page;
import me.zj22.gudao.server.web.service.OrderService;
import me.zj22.gudao.server.web.service.PayService;
import me.zj22.gudao.server.web.service.ProductInfoService;
import me.zj22.gudao.server.web.service.WebSocket;
import me.zj22.gudao.server.web.utils.KeyUtil;
import me.zj22.gudao.server.web.utils.TimeParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/6.
 */
@Service
public class OrderServiceImp implements OrderService {
    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImp.class);
    @Autowired
    private OrderWrapperMapper orderWrapperMapper;

    @Autowired
    private OrderDetailWrapperMapper orderDetailMapperWrapper;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private PayService payService;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private WebSocket webSocket;

    @Override
    @Transactional //开启事物
    public OrderDTO create(OrderDTO orderDTO) {
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        String orderId = KeyUtil.genUniqueKey();
        //1. 查询商品：数量（库存判断）价格（前端传入的可修改，不准确）
        for(OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.selectByPrimaryKey(orderDetail.getProductId());
            if(productInfo == null){
                throw new daoGuException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            //2. 计算总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());//非自增长
            orderDetail.setOrderId(orderId);
            orderDetail.setProductId(productInfo.getProductId());
            orderDetailMapperWrapper.insert(orderDetail);
        }

        //3. 创建订单写入数据库 order
        Date date = new Date();
        Order order = new Order();
        long nuixTime = TimeParse.Time2NUIX(TimeParse.NUIX2Time(date));
        order.setCreateTime(nuixTime);
        order.setOperationTime(nuixTime);
        order.setOrderStatus(OrderStatusEnum.NEW.getCode());
        //把总价计算后在存入order表，即更新
        BigDecimal finalAmmount = orderAmount.add(orderDTO.getPostage());//加上邮费
        order.setOrderAmount(finalAmmount);
        orderWrapperMapper.insert(order);

        //4. 扣除库存
        List<Cart> cartList = orderDTO.getOrderDetailList().stream().map(
                e->new Cart(e.getProductId(), e.getProductQuantity()) )
                .collect(Collectors.toList());
        productInfoService.decreaseStock(cartList);

        //webSocket发送消息
        webSocket.sendMessage(orderDTO.getOrderId());
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        Order order = orderWrapperMapper.selectByPrimaryKey(orderId);
        if(order == null){
            throw new daoGuException(ResultEnum.CART_EMPTY);
        }
//        List<OrderDetail> orderDetailList =orderDetailMapper.findByOrderId(orderId);
        List<OrderDetail> orderDetailList = orderDetailMapperWrapper.findByOrderId(orderId);
        if(orderDetailList == null){
            throw new daoGuException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order, orderDTO);//属性赋值
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findAllListOrder(Page<OrderDTO> page, Integer userId) {
        List<OrderDTO> orderList = orderWrapperMapper.findAllOrderByUserId(page);
        Integer allOrderCount = orderWrapperMapper.findAllOrderCount(userId);
        page.setList(orderList);
        page.setTotalRecord(allOrderCount);
        return page;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            LOG.error(" [取消订单] 订单状态不正确，orderId = {}, orderStatus = {}",
                    orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new daoGuException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        order.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        int update = orderWrapperMapper.updateByPrimaryKey(order);
        if(update == 0){
            LOG.error(" [取消订单] 订单更新失败");
            throw new daoGuException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //库存返回
        List<Cart> cartList = orderDTO.getOrderDetailList().stream().map(
                e->new Cart(e.getProductId(), e.getProductQuantity()) )
                .collect(Collectors.toList());
        productInfoService.increaseStock(cartList);
        //已经付款的，进行退款
        if(orderDTO.getOrderStatus().equals(PayStatusEnum.PAY_FINISH.getCode())){
            //微信退款
            payService.refund(orderDTO);
        }

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            LOG.error(" [订单完成] 订单状态不正确，orderId = {}, orderStatus = {}",
                    orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new daoGuException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改状态
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        order.setOrderStatus(OrderStatusEnum.FINIFSHED.getCode());
        int update = orderWrapperMapper.updateByPrimaryKey(order);
        if(update == 0){
            LOG.error(" [订单完成] 订单更新失败");
            throw new daoGuException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            LOG.error(" [订单支付完成] 订单状态不正确，orderId = {}, orderStatus = {}",
                    orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new daoGuException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if(!orderDTO.getPayStatus().equals(PayStatusEnum.PAY_WAIT.getCode())){
            LOG.error(" [订单支付完成] 订单支付状态不正确， orderDTO = {}",orderDTO);
            throw new daoGuException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.PAY_FINISH.getCode());
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        int update = orderWrapperMapper.updateByPrimaryKey(order);
        if(update == 0){
            LOG.error(" [订单支付完成] 订单更新失败");
            throw new daoGuException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findAllList(Page<OrderDTO> page) {
        List<OrderDTO> orderList = orderWrapperMapper.findAllOrder(page);
        Integer allOrderCount = orderWrapperMapper.findOrderCount();
        page.setList(orderList);
        page.setTotalRecord(allOrderCount);
        return page;
    }
}
