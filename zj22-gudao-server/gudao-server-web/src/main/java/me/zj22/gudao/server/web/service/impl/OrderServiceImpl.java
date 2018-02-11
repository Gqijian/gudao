package me.zj22.gudao.server.web.service.impl;

import me.zj22.gudao.server.web.pojo.dto.OrderDTO;
import me.zj22.gudao.server.web.pojo.vo.Page;
import me.zj22.gudao.server.web.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * 订单service
 * @Program:zj22-gudao-server
 * @Description:
 * @Author Gqjian
 * @Create 2018/2/12 00:14:07
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        return null;
    }

    @Override
    public OrderDTO findOne(Integer orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findAllListOrder(Page<OrderDTO> page, String openId) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
