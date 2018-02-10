package me.zj22.gudao.server.web.pojo.vo;

/**
 * 购物车
 * daogu
 * Created by 袁鹏 on 2018/2/6.
 */
public class Cart {
    //商品id
    private Integer productId;

    //商品数量
    private Integer productQuantity;

    public Cart(Integer productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
    //TODO 其它购物车可能传入的字段


    public Integer getProductId() {
        return productId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }
}
