package me.zj22.gudao.server.web.service;


import me.zj22.gudao.server.web.pojo.dto.OrderDTO;

/**
 * 推送消息
 */
public interface PushMessageService {

    //订单状态变更消息
    void orderStatus(OrderDTO orderDTO);

    String sendWechatMsgToUser(OrderDTO orderDTO);
}
