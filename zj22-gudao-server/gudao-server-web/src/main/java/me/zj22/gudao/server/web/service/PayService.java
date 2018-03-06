package me.zj22.gudao.server.web.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import me.zj22.gudao.server.web.pojo.dto.OrderDTO;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/9.
 */
public interface PayService {

    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDTO orderDTO);
}
