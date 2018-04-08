package me.zj22.gudao.server.web.controller;

import me.zj22.gudao.server.web.pojo.vo.CartProduct;
import me.zj22.gudao.server.web.service.CartService;
import me.zj22.gudao.server.web.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 购物车商品Contoller
 * @Program:zj22-gudao-server
 * @Description:
 * @Author Gqjian
 * @Create 2018/2/11 01:26:49
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/add")
    @ResponseBody
    public String addCartProduct(
            @RequestParam(defaultValue = "1") Integer num,
            Integer id,
            HttpServletRequest request, HttpServletResponse response){
        try {
            String json = cartService.addCartItem(id, num, request, response);
            String result = "callback(" ;
            return result+json+")";
        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }

    @RequestMapping("/carts")
    @ResponseBody
    public String showCart(@RequestParam String cb, String _,
                           HttpServletRequest request, HttpServletResponse response){

        try{
            List<CartProduct> cartList = cartService.getCartProductList(request, response);
            String json = JsonUtils.objectToJson(cartList);
            String result = "callback(" ;
            return result+json+")";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
