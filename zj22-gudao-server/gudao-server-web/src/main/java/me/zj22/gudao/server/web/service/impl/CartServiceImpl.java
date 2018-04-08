package me.zj22.gudao.server.web.service.impl;

import com.alibaba.fastjson.JSON;
import me.zj22.gudao.server.web.dao.db.wrap.ProductInfoWrapperMapper;
import me.zj22.gudao.server.web.pojo.dto.ProductInfo;
import me.zj22.gudao.server.web.pojo.vo.CartProduct;
import me.zj22.gudao.server.web.service.CartService;
import me.zj22.gudao.server.web.utils.CookieUtil;
import me.zj22.gudao.server.web.utils.CookieUtils;
import me.zj22.gudao.server.web.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
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
@Service("CartServiceImpl")
@Repository
public class CartServiceImpl implements CartService{

    //@Value("${GD_CART}")
    private static final String GD_CART = "GD_CART";

    @Autowired
    private ProductInfoWrapperMapper productInfoWrapperMapper;

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
            ProductInfo productInfo = productInfoWrapperMapper.selectByPrimaryKey(productId);
            cartProduct.setProductId(productInfo.getProductId());
            cartProduct.setProductName(productInfo.getProductName());
            cartProduct.setProductIconOne(productInfo.getProductIconOne());
            cartProduct.setProductPrice(productInfo.getProductPrice());
            cartProduct.setNum(num);
        }
        //添加购物车列表
        cartList.add(cartProduct);
        cartList.toString();
        String json = JSON.toJSONString(cartList);
        System.out.print(json);
        //写入Cookie
        CookieUtils.setCookie(request,response,GD_CART,"[{\"num\":1,\"productIconOne\":\"u=3373476364,2880429076&fm=27&gp=0.jpg\",\"productId\":2,\"productName\":\"酸辣粉\",\"productPrice\":0.01}]",3600,"utf-8");
        //CookieUtil.set(response,"GD_CART",json,3600);
        return "200";
    }

    @Override
    public List<CartProduct> getCartProductList(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
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
        String cartJson = CookieUtils.getCookieValue(request,"GD_CART", "utf-8");//cartJson

        //Cookie cookie = CookieUtil.get(request, "GD_CART");

        if(cartJson == null){
            return new ArrayList<>();
        }
         //把json式转换成商品列表
        try{
            //String cartJson = cookie.getValue();
            List<CartProduct> list = JsonUtils.jsonToList(cartJson, CartProduct.class);
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  new ArrayList<>();
    }
}
