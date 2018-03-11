package me.zj22.gudao.server.web.pojo.dto;

import java.math.BigDecimal;

/**
 * @Program:zj22-gudao-server
 * @Description:订单商品详情信息表
 * @Author Gqjian
 * @Create 2018/2/5 15:38:07
 */

public class OrderDetail {
    private String detailId;

    private Integer productQuantity;    //数量

    private BigDecimal price;    //金额,单位分

    private String orderId;    //订单id

    private Integer productId;    //商品id

    private BigDecimal productTotal;

    public BigDecimal getProductTotal() {
        return this.price.multiply(new BigDecimal(this.productQuantity));
    }

//    public void setProductTotal(BigDecimal productTotal) {
//        this.productTotal = price.multiply(new BigDecimal(productQuantity));
//    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}