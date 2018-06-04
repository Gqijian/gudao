package me.zj22.gudao.server.web.service.impl;

import me.zj22.gudao.server.web.dao.db.wrap.OrderDetailWrapperMapper;
import me.zj22.gudao.server.web.pojo.dto.OrderDetail;
import me.zj22.gudao.server.web.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Program:zj22-gudao-server
 * @Description:
 * @Author Gqjian
 * @Create 2018/5/7 16:42:33
 */
@Service
@Repository
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailWrapperMapper orderDetailWrapperMapper;

    @Override
    public List<OrderDetail> findByOrderId(String orderId) {
        List<OrderDetail> lists = orderDetailWrapperMapper.findByOrderId(orderId);
        return lists;
    }
}
