package me.zj22.gudao.server.web.pojo.dto;

import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/6.
 */
public class OrderDTO extends  Order {
    private List<OrderDetail> orderDetailList;

    private String viewTimeToString;

    public String getViewTimeToString() {
        return viewTimeToString;
    }

    public void setViewTimeToString(String viewTimeToString) {
        this.viewTimeToString = viewTimeToString;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
