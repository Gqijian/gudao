package me.zj22.gudao.server.web.service.impl;

import me.zj22.gudao.server.web.dao.db.wrap.ProductInfoWrapperMapper;
import me.zj22.gudao.server.web.enums.ProductStatusEnum;
import me.zj22.gudao.server.web.enums.ResultEnum;
import me.zj22.gudao.server.web.exception.SellerAuthorizeException;
import me.zj22.gudao.server.web.exception.daoGuException;
import me.zj22.gudao.server.web.pojo.dto.Operator;
import me.zj22.gudao.server.web.pojo.dto.ProductInfo;
import me.zj22.gudao.server.web.pojo.vo.Cart;
import me.zj22.gudao.server.web.pojo.vo.Page;
import me.zj22.gudao.server.web.service.ProductInfoService;
import me.zj22.gudao.server.web.utils.TimeParse;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/5.
 * @Change Gqijian 2018/3/18
 */
@Service
public class ProductInfoServiceImp implements ProductInfoService {
    @Autowired
    private ProductInfoWrapperMapper productInfoWrapperMapper;

    @Autowired
    private  HttpServletRequest request;

    @Override
    public ProductInfo selectByPrimaryKey(Integer productId) {
        return productInfoWrapperMapper.selectByPrimaryKey(productId);
    }

    @Override
    public List<ProductInfo> findDownAll(byte productStatus) {
        return productInfoWrapperMapper.findDownAll(ProductStatusEnum.DOWN.getCode());
    }

    @Override
    public List<ProductInfo> findUpAll(byte productStatus) {
        //默认上架
        return productInfoWrapperMapper.findUpAll(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Page<ProductInfo> page) {
        List<ProductInfo> productList = productInfoWrapperMapper.findPageList(page);
        Integer count = productInfoWrapperMapper.findCount();
        page.setList(productList);
        page.setTotalRecord(count);
        return page;
    }

    @Override
    public void save(ProductInfo productInfo) {
        // 从session中获取修改人保存
        Operator seller = (Operator)request.getSession().getAttribute("seller");
        if(seller == null){
            throw new SellerAuthorizeException();
        }
        productInfo.setCreateTime(TimeParse.Time2NUIX(TimeParse.NUIX2Time(new Date())));
        productInfo.setUpdateTime(TimeParse.Time2NUIX(TimeParse.NUIX2Time(new Date())));
        productInfo.setCreatUser(seller.getRealName());
        productInfo.setUpdateUser(seller.getRealName());
        productInfoWrapperMapper.insert(productInfo);
    }

    @Override
    public void update(ProductInfo productInfo) {
        // 从session中获取修改人保存
        Operator seller = (Operator)request.getSession().getAttribute("seller");
        if(seller == null){
            throw new SellerAuthorizeException();
        }
        productInfo.setUpdateTime(TimeParse.Time2NUIX(TimeParse.NUIX2Time(new Date())));
        productInfo.setUpdateUser(seller.getRealName());
        productInfoWrapperMapper.updateByPrimaryKeySelective(productInfo);
    }


    @Override
    @Transactional
    public void increaseStock(List<Cart> cartList) {
        for(Cart cart : cartList){
            ProductInfo productInfo = productInfoWrapperMapper.selectByPrimaryKey(cart.getProductId());
            if(productInfo == null){
                throw  new daoGuException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            Integer result = productInfo.getProductStock() + cart.getProductQuantity();
            productInfo.setProductStock(result);
            //保存
            productInfoWrapperMapper.updateByPrimaryKey(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<Cart> cartList) {
        for(Cart cart : cartList){
            ProductInfo productInfo = productInfoWrapperMapper.selectByPrimaryKey(cart.getProductId());
            if(productInfo == null){
                throw  new daoGuException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            Integer result = productInfo.getProductStock() - cart.getProductQuantity();
            if(result < 0){
                throw  new daoGuException(ResultEnum.PRODUCT_STOCK_ERROR);//库存不够
            }
            productInfo.setProductStock(result);
            //保存
            productInfoWrapperMapper.updateByPrimaryKey(productInfo);
        }
    }

    @Override
    public Integer onSale(Integer productId) {
        ProductInfo productInfo = productInfoWrapperMapper.selectByPrimaryKey(productId);
        if(productInfo == null){
            throw new daoGuException(ResultEnum.PRODUCT_NOT_EXIT);
        }
        if(productInfo.getProductStatus() == ProductStatusEnum.UP.getCode()){
            throw new daoGuException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return productInfoWrapperMapper.updateByPrimaryKey(productInfo);
    }

    @Override
    public Integer offSale(Integer productId) {
        ProductInfo productInfo = productInfoWrapperMapper.selectByPrimaryKey(productId);
        if(productInfo == null){
            throw new daoGuException(ResultEnum.PRODUCT_NOT_EXIT);
        }
        if(productInfo.getProductStatus() == ProductStatusEnum.DOWN.getCode()){
            throw new daoGuException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return productInfoWrapperMapper.updateByPrimaryKey(productInfo);
    }

    @Override
    public Integer delete(String[] pks) {
        return null;
    }

    @Override
    public List<ProductInfo> findAllProducts() {

        return null;
    }
}
