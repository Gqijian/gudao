package me.zj22.gudao.server.web.form;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/20.
 */
public class ProductInfoForm {
    private Integer productId;

    @NotEmpty(message = "商品名称必填")
    private String productName; //商品名称

    @NotEmpty(message = "图片1必填")
    private String productIconOne;  //图片

    private String productIconTwo;     //图片

    private String productIconThree;//图片

    @NotNull(message = "单价必填")
    private BigDecimal productPrice;   //单价，单位分

    private BigDecimal cost;   //成本价，单位分

    @NotNull(message = "重量必填")
    @Range(min=0,message="不能小于0")
    private Integer weight; //重量，单位克

    @NotEmpty(message = "简介必填")
    private String productAbout;    //商品简介

    @Range(min=0,message="不能小于0")
    private Integer productStock;   //库存

    private String productDescription;  //商品详情

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductIconOne() {
        return productIconOne;
    }

    public void setProductIconOne(String productIconOne) {
        this.productIconOne = productIconOne;
    }

    public String getProductIconTwo() {
        return productIconTwo;
    }

    public void setProductIconTwo(String productIconTwo) {
        this.productIconTwo = productIconTwo;
    }

    public String getProductIconThree() {
        return productIconThree;
    }

    public void setProductIconThree(String productIconThree) {
        this.productIconThree = productIconThree;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getProductAbout() {
        return productAbout;
    }

    public void setProductAbout(String productAbout) {
        this.productAbout = productAbout;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}
