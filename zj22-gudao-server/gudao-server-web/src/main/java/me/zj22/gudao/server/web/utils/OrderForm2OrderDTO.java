package me.zj22.gudao.server.web.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.zj22.gudao.server.web.enums.ResultEnum;
import me.zj22.gudao.server.web.exception.daoGuException;
import me.zj22.gudao.server.web.form.OrderForm;
import me.zj22.gudao.server.web.pojo.vo.OrderDTO;
import me.zj22.gudao.server.web.pojo.dto.OrderDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/6.
 */
public class OrderForm2OrderDTO {
    private static final Logger LOG = LoggerFactory.getLogger(OrderForm2OrderDTO.class);

    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setReceiverName(orderForm.getName());
        orderDTO.setReceiverPhone(orderForm.getPhone());
        orderDTO.setAddress(orderForm.getAddress());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<OrderDetail>() {
                    }.getType());
        } catch (Exception e) {
            LOG.error("[对象转换]， 参数异常 String = {}", orderForm.getItems());
            throw new daoGuException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;

    }
}
