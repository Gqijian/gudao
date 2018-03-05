package me.zj22.gudao.server.web.service.impl;

import me.zj22.gudao.server.web.dao.db.ProductInfoMapper;
import me.zj22.gudao.server.web.pojo.dto.ProductInfo;
import me.zj22.gudao.server.web.pojo.vo.CartProduct;
import me.zj22.gudao.server.web.service.CartService;
import me.zj22.gudao.server.web.utils.CookieUtils;
import me.zj22.gudao.server.web.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车Service
 * @Program:zj22-gudao-server
 * @Description:
 * @Author Gqjian
 * @Create 2018/2/11 00:54:43
 */
@Service
public class CartServiceImpl implements CartService{

    //@Value("${GD_CART}")
    private static final String GD_CART = "gu_cart";

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public String addCartItem(Integer productId, Integer num,
                              HttpServletRequest request, HttpServletResponse response) {
        CartProduct cartProduct = null;
        List<CartProduct> cartList = getCartProductList(request);
        //验证是否已存在
        for (CartProduct cProduct : cartList) {
            //如果有，增加
            cProduct.setNum(cProduct.getNum()+num);
            cartProduct = cProduct;
            break;
        }
        if(cartProduct == null){
            cartProduct = new CartProduct();
            ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(productId);
            cartProduct.setProductId(productInfo.getProductId());
            cartProduct.setProductName(productInfo.getProductName());
            cartProduct.setProductIconOne(productInfo.getProductIconOne());
            cartProduct.setNum(num);
            cartProduct.setProductPrice(productInfo.getProductPrice());
        }
        //添加购物车列表
        cartList.add(cartProduct);
        //写入Cookie
        CookieUtils.setCookie(request,response,GD_CART,JsonUtils.objectToJson(cartList),true);
        return "200";
    }

    @Override
    public List<CartProduct> getCartProductList(HttpServletRequest request, HttpServletResponse response) {
        List<CartProduct> cartProductList = getCartProductList(request);
        return cartProductList;
    }

    /**
     * 从Cookie中取商品
     * @param request
     * @return
     */
    private List<CartProduct> getCartProductList(HttpServletRequest request){
        //从Cookie中取商品
        String cartJson = CookieUtils.getCookieValue(request,GD_CART, true);
        if(cartJson == null){
            return new ArrayList<>();
        }
        //把json式转换成商品列表
        try{
            List<CartProduct> list = JsonUtils.jsonToList(cartJson, CartProduct.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  new ArrayList<>();
    }
}
