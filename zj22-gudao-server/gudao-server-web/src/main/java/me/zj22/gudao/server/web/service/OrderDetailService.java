package me.zj22.gudao.server.web.service;

import me.zj22.gudao.server.web.pojo.dto.OrderDetail;

import java.util.List;

/**
 * @Program:zj22-gudao-server
 * @Description:
 * @Author Gqjian
 * @Create 2018/5/7 16:41:30
 */
public interface OrderDetailService {
    /**根据订单id查询订单详情*/
    List<OrderDetail> findByOrderId(String orderId);
}
