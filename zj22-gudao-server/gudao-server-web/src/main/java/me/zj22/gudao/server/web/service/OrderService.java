package me.zj22.gudao.server.web.service;

import me.zj22.gudao.server.web.pojo.dto.Order;
import me.zj22.gudao.server.web.pojo.dto.OrderDetail;
import me.zj22.gudao.server.web.pojo.vo.OrderDTO;
import me.zj22.gudao.server.web.pojo.vo.Page;

import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/6.
 */
public interface OrderService {
    /**创建订单*/
    OrderDTO create(OrderDTO orderDTO);

    /**查询单个订单*/
    OrderDTO findOne(Integer orderId);

    /**查询订单列表,分页  里面的keyWord也填充为openId*/
    Page<OrderDTO> findAllListOrder(Page<OrderDTO> page, String openId);

    /**取消订单*/
    OrderDTO cancel(OrderDTO orderDTO);

    /**完结订单*/
    OrderDTO finish(OrderDTO orderDTO);

    /**支付订单*/
    OrderDTO paid(OrderDTO orderDTO);
}
