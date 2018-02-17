package me.zj22.gudao.server.web.dao.db.wrap;

import me.zj22.gudao.server.web.dao.db.OrderMapper;
import me.zj22.gudao.server.web.pojo.vo.OrderDTO;
import me.zj22.gudao.server.web.pojo.vo.Page;

import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/6.
 */
public interface OrderMapperWrapper extends OrderMapper{
    /**创建订单*/
    OrderDTO create(OrderDTO orderDTO);

    /**根据用户openid查询所有订单，在service层分页操作*/
    List<OrderDTO> findAllOrderByOpenId(Page<OrderDTO> page);

    /**根据用户的openid查询订单数*/
    Integer findAllOrderCount(String openId);

    /**取消订单*/
    OrderDTO cancel(OrderDTO orderDTO);

    /**完结订单*/
    OrderDTO finish(OrderDTO orderDTO);

    /**支付订单*/
    OrderDTO paid(OrderDTO orderDTO);
}
