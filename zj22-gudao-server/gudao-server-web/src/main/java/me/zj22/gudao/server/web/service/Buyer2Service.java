package me.zj22.gudao.server.web.service;

import me.zj22.gudao.server.web.pojo.dto.OrderDTO;

/**
 * 买家
 * daogu
 * Created by 袁鹏 on 2018/2/7.
 */
public interface Buyer2Service {

    /**查询一个订单*/
    OrderDTO findOrderOne(String openId, String orderId);

    /**取消一个订单*/
    OrderDTO cancelOrder(String openId, String orderId);
}
