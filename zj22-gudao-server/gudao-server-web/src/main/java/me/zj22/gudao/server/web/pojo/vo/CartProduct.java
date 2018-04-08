package me.zj22.gudao.server.web.pojo.vo;

import me.zj22.gudao.server.web.pojo.dto.ProductInfo;

import java.math.BigDecimal;

/**
 * @Program:zj22-gudao-server
 * @Description:
 * @Author Gqjian
 * @Create 2018/2/10 16:12:28
 */
public class CartProduct{

    private Integer productId;

    private String productName; //商品名称

    private String productIconOne;  //图片

    private BigDecimal productPrice;   //单价，单位分

    private Integer num;    //数量

    private String productDetail;

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

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }
}
