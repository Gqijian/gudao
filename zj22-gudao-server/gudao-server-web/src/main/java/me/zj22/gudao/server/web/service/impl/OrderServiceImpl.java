package me.zj22.gudao.server.web.service.impl;

import me.zj22.gudao.server.web.dao.db.OrderDetailMapper;
import me.zj22.gudao.server.web.dao.db.OrderMapper;
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

import java.util.Date;
import java.util.List;

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
    private OrderDetailMapper orderDetailMapper;

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        Order order = new Order();
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
        order.setOrderId(orderDTO.getOrderId());
        order.setOrderStatus(OrderStatusEnum.NEW.getCode());
        order.setCreateTime(date);
        orderMapper.insert(order);
        //插入订单表明细
        List<OrderDetail> orderDetails = orderDTO.getOrderDetailList();
        for (OrderDetail orderDetail:orderDetails) {
            orderDetail.setOrderId(orderId.toString());
            orderDetailMapper.insert(orderDetail);
        }
        return orderDTO;
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
    public OrderDTO cancel(OrderDTO orderDTO) {
        Order order = orderMapper.selectOrderById(orderDTO.getOrderId());
        order.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        if(orderMapper.updateByPrimaryKey(order)>0){
            OrderDTO orderDTO2 = copyOderParams(order);
            return orderDTO2;
        }
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
