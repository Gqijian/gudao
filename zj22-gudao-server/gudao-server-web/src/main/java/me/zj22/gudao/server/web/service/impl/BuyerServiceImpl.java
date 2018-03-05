package me.zj22.gudao.server.web.service.impl;

import me.zj22.gudao.server.web.dao.db.OrderDetailMapper;
import me.zj22.gudao.server.web.dao.db.OrderMapper;
import me.zj22.gudao.server.web.dao.db.UserMapper;
import me.zj22.gudao.server.web.enums.OrderStatusEnum;
import me.zj22.gudao.server.web.pojo.dto.Order;
import me.zj22.gudao.server.web.pojo.vo.OrderDTO;
import me.zj22.gudao.server.web.pojo.dto.OrderDetail;
import me.zj22.gudao.server.web.pojo.dto.User;
import me.zj22.gudao.server.web.service.Buyer2Service;
import me.zj22.gudao.server.web.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户购买Service
 * @Program:zj22-gudao-server
 * @Description:
 * @Author Gqjian
 * @Create 2018/2/20 17:59:01
 */
@Service
public class BuyerServiceImpl implements Buyer2Service {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

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

    @Override
    public OrderDTO findOrderOne(String openId, String orderId) {
        User user = userMapper.selectUserByOpenId(openId);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getUserId());
        map.put("orderId",orderId);
        Order order = orderMapper.selectOrderOne(map);
        List<OrderDetail> orderDetails = orderDetailMapper.selectOrderDetailsId(order.getOrderId());
        OrderDTO result = copyOderParams(order);
        result.setOrderDetailList(orderDetails);
        return result;
    }

    @Override
    public OrderDTO cancelOrder(String openId, String orderId) {
        Order order = orderMapper.selectOrderById(orderId.toString());
        order.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        if(orderMapper.updateByPrimaryKey(order)>0){
            OrderDTO orderDTO = copyOderParams(order);
            return orderDTO;
        }
        return null;
    }
}
