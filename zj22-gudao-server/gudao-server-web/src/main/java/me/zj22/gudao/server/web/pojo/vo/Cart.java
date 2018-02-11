package me.zj22.gudao.server.web.pojo.vo;

/**
 * @Program:zj22-gudao-server
 * @Description:
 * @Author Gqjian
 * @Create 2018/2/11 00:48:12
 */
public class Cart {

    private Integer productId;

    private Integer productQuantity;

    public Cart(Integer productId, Integer productQuantity){

        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }
}
