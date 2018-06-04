package me.zj22.gudao.server.web.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 进行表达验证的订单字段
 * daogu
 * Created by 袁鹏 on 2018/2/6.
 */
public class OrderForm {
    //TODO 其它字段看订单详情页面而定

    /**买家姓名*/
    @NotEmpty(message = "姓名必填")
    private String name;

    /**买家手机号*/
    @NotEmpty(message = "手机号必填")
    private String phone;

    /**买家地址*/
    @NotEmpty(message = "地址必填")
    private String address;

    /**买家微信openid*/
    @NotEmpty(message = "openid必填")
    private String openid;

    /**购物车*/
    @NotEmpty(message = "购物车不能为空")
    private String items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }
}
