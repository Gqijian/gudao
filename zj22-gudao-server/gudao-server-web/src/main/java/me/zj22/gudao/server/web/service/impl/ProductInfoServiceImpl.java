package me.zj22.gudao.server.web.service.impl;

import me.zj22.gudao.server.web.dao.db.wrap.ProductInfoMapperWrapper;
import me.zj22.gudao.server.web.enums.ProductStatusEnum;
import me.zj22.gudao.server.web.pojo.dto.ProductInfo;
import me.zj22.gudao.server.web.pojo.vo.Cart;
import me.zj22.gudao.server.web.pojo.vo.Page;
import me.zj22.gudao.server.web.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/5.
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoMapperWrapper productInfoMapperWrapper;

    @Override
    public ProductInfo selectByPrimaryKey(Integer productId) {
        return productInfoMapperWrapper.selectByPrimaryKey(productId);
    }

    @Override
    public List<ProductInfo> findDownAll(Byte productStatus) {
        return productInfoMapperWrapper.findDownAll(ProductStatusEnum.DOWN.getCode());
    }

    @Override
    public List<ProductInfo> findUpAll(Byte productStatus) {
        //默认上架
        return productInfoMapperWrapper.findUpAll(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Page<ProductInfo> page) {
        List<ProductInfo> productList = productInfoMapperWrapper.findPageList(page);
        Integer count = productInfoMapperWrapper.findCount();
        page.setList(productList);
        page.setTotalRecord(count);
        return page;
    }

    @Override
    public void save(ProductInfo productInfo) {
        productInfoMapperWrapper.save(productInfo);
    }

    @Override
    public void increaseStock(List<Cart> cartList) {

    }

    @Override
    public void decreaseStock(List<Cart> cartList) {

    }
}
