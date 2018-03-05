package me.zj22.gudao.server.web.service.impl;

import me.zj22.gudao.server.web.enums.ResultEnum;
import me.zj22.gudao.server.web.exception.daoGuException;
import me.zj22.gudao.server.web.pojo.vo.OrderDTO;
import me.zj22.gudao.server.web.service.BuyerService;
import me.zj22.gudao.server.web.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/7.
 */
@Service
public class BuyerServiceImp implements BuyerService {

    private static final Logger LOG = LoggerFactory.getLogger(BuyerServiceImp.class);
    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openId, String orderId) {
        return checkOrderOwner(openId, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openId, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openId, orderId);
        if(orderDTO == null){
            LOG.error(" [取消订单] 查不到当前订单， orderId = {}", orderId);
            throw new daoGuException(ResultEnum.ORDER_NOT_EXIT);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openId, String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        //安全检查
        if(!orderDTO.getUserId().equals(openId)){
            LOG.error(" [查询订单] 订单状态不一致， openId = {}, orderDTO = {}", openId, orderDTO);
            throw new daoGuException(ResultEnum.ORDER_OWNER_ERRR);
        }

        return orderDTO;
    }
}
