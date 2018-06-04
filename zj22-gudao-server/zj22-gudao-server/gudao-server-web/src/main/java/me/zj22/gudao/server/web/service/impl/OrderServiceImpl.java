package me.zj22.gudao.server.web.service.impl;

import me.zj22.gudao.server.web.dao.db.OrderDetailMapper;
import me.zj22.gudao.server.web.dao.db.OrderMapper;
import me.zj22.gudao.server.web.dao.db.wrap.OrderDetailWrapperMapper;
import me.zj22.gudao.server.web.dao.db.wrap.OrderWrapperMapper;
import me.zj22.gudao.server.web.enums.OrderStatusEnum;
import me.zj22.gudao.server.web.enums.PayStatusEnum;
import me.zj22.gudao.server.web.pojo.dto.Order;
import me.zj22.gudao.server.web.pojo.dto.OrderDTO;
import me.zj22.gudao.server.web.pojo.dto.OrderDetail;
import me.zj22.gudao.server.web.pojo.vo.Page;
import me.zj22.gudao.server.web.service.Order2Service;
import me.zj22.gudao.server.web.utils.IdWorkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单Service
 * @Program:zj22-gudao-server
 * @Description:
 * @Author Gqjian
 * @Create 2018/2/13 19:50:57
 */
@Service("OrderServiceImpl")
public class OrderServiceImpl implements Order2Service {

    @Autowired(required=true)
    private OrderMapper orderMapper;

    @Autowired
    private OrderWrapperMapper orderWrapperMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private OrderDetailWrapperMapper orderDetailWrapperMapper;

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
//        Order order = new Order();
        //获得一个订单ID
        IdWorkerUtil idWork = new IdWorkerUtil(0,0);
        Long orderId = idWork.nextId();
        //补全pojo
        orderDTO.setOrderId(String.valueOf(orderId));
        //订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.NEW.getCode());
        Date time = new Date();
        long date = time.getTime();
        orderDTO.setCreateTime(date);
        //插入订单表
        OrderDTO result = defaultOrderValue(orderDTO);
        //插入总价
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        BigDecimal amount = new BigDecimal(0);
        for (OrderDetail orderDetail:orderDetailList) {
            amount = amount.add(orderDetail.getPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())));
        }
        result.setOrderAmount(amount);
        orderMapper.insert(result);
        //插入订单表明细
        List<OrderDetail> orderDetails = result.getOrderDetailList();
        for (OrderDetail orderDetail:orderDetails) {
            orderDetail.setDetailId(String.valueOf(idWork.nextId()));
            orderDetail.setOrderId(orderId.toString());
            orderDetailMapper.insert(orderDetail);
        }
        return result;
    }

    /**
     * 取消订单
     * @param orderDTO
     * @return
     */
    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        Order order = orderMapper.selectOrderById(orderDTO.getOrderId());
        order.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        if(orderMapper.updateByPrimaryKey(order)>0){
            OrderDTO result = copyOderParams(order);
            return result;
        }
        return null;
    }
    
    /**
     * 查询单个订单
     * @param orderId
     * @return
     */
    @Override
    public OrderDTO findOne(String orderId) {
        Order order = orderMapper.selectOrderById(orderId);
        List<OrderDetail> orderDetails = orderDetailMapper.selectOrderDetailsId(orderId);
        OrderDTO orderDTO = copyOderParams(order);
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findAllListOrder(Page<OrderDTO> page, Integer userId) {
        return null;
    }


    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        Order order = orderMapper.selectOrderById(orderDTO.getOrderId());
        order.setOrderStatus(OrderStatusEnum.FINIFSHED.getCode());
        if(orderMapper.updateByPrimaryKey(order)>0){
            orderDTO = copyOderParams(order);
            return orderDTO;
        }
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        Order order = orderMapper.selectOrderById(orderDTO.getOrderId());
        order.setOrderStatus(PayStatusEnum.PAY_WAIT.getCode());
        if(orderMapper.updateByPrimaryKey(order)>0){
            orderDTO = copyOderParams(order);
            return orderDTO;
        }
        return null;
    }

    @Override
    public Page<OrderDTO> findAllList(Page<OrderDTO> page) {
        return null;
    }

    /**
     * 删除选定订单
     * @param orderId
     * @return
     */
    @Override
    public Map deleteOrder(String orderId) {
        Map map = new HashMap();
        int result1 = orderMapper.deleteByPrimaryKey(orderId);
        Integer result2 = orderDetailWrapperMapper.deleteByOrderId(orderId);
        map.put("result1",result1);
        map.put("result2",result2);
        return map;
    }

    /**
     * 更新完结订单
     * @param orderDTO
     * @return
     */
    @Override
    public String updateOrder(OrderDTO orderDTO) {
        int result = orderMapper.updateByPrimaryKeySelective(orderDTO);
        return result+"";
    }

    @Override
    public String KdMessage(OrderDTO orderDTO) {
        List<OrderDetail> orderDetailList = orderDetailWrapperMapper.findByOrderId(orderDTO.getOrderId());
        Integer num = 0;
        for (OrderDetail orderDetail:orderDetailList) {
            num += orderDetail.getProductQuantity();
        }
        return num+"";
    }

    @Override
    public List<OrderDTO> findUserOrdersByStatus(HashMap map) {
        List<OrderDTO> list = orderWrapperMapper.findUserOrdersByStatus(map);
        return list;
    }

    private OrderDTO defaultOrderValue(OrderDTO orderDTO){
        orderDTO.setReceiverName("0");
        orderDTO.setReceiverPhone("0");
        orderDTO.setProv("0");
        orderDTO.setCity("0");
        orderDTO.setCounty("0");
        orderDTO.setAddress("0");
        orderDTO.setZipcode("0");
        //orderDTO.setOrderAmount(new BigDecimal(0.01));
        orderDTO.setPostage(new BigDecimal(0));
        orderDTO.setOperationTime(1l);
        orderDTO.setOperator("0");
        orderDTO.setRemark("0");
        orderDTO.setTrackNumber("000");
        return orderDTO;
    }

    private OrderDTO copyOderParams(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setReceiverName(order.getReceiverName());
        orderDTO.setReceiverPhone(order.getReceiverPhone());
        orderDTO.setProv(order.getProv());
        orderDTO.setCity(order.getCity());
        orderDTO.setCounty(order.getCounty());
        orderDTO.setAddress(order.getAddress());
        orderDTO.setZipcode(order.getZipcode());
        orderDTO.setOrderAmount(order.getOrderAmount());
        orderDTO.setPostage(order.getPostage());
        orderDTO.setOrderStatus(order.getOrderStatus());
        orderDTO.setCreateTime(order.getCreateTime());
        orderDTO.setOperationTime(order.getOperationTime());
        orderDTO.setOperator(order.getOperator());
        orderDTO.setRemark(order.getRemark());
        orderDTO.setUserId(order.getUserId());
        return orderDTO;
    }
}
