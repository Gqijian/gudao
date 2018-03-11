package me.zj22.gudao.server.web.service;

import me.zj22.gudao.server.web.pojo.dto.ProductInfo;
import me.zj22.gudao.server.web.pojo.vo.Cart;
import me.zj22.gudao.server.web.pojo.vo.Page;

import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/5.
 */
public interface ProductInfoService {

    ProductInfo selectByPrimaryKey(Integer productId);

    /**查询所有下架商品 0 1*/
    List<ProductInfo> findDownAll(byte productStatus);

    /**查询所有在架商品 0 1*/
    List<ProductInfo> findUpAll(byte productStatus);

    /**查询所有商品，后台管理员使用,分页*/
    Page<ProductInfo> findAll(Page<ProductInfo> page);

    /**保存商品*/
    void save(ProductInfo productInfo);

    /**更新商品*/
    void update(ProductInfo productInfo);

    //加库存
    void increaseStock(List<Cart> cartList);

    //减库存
    void decreaseStock(List<Cart> cartList);

    //上架
    Integer onSale(Integer productId);

    //下架
    Integer offSale(Integer productId);

    //删除商品
    Integer delete(String pks[]);
}
