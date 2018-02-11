package me.zj22.gudao.server.web.controller;

import me.zj22.gudao.server.web.pojo.vo.CartProduct;
import me.zj22.gudao.server.web.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/add/{productId }")
    @ResponseBody
    public String addCartProduct(@PathVariable Integer productId,
                                 @RequestParam(defaultValue = "1") Integer num,
                                 HttpServletRequest request, HttpServletResponse response){

        String result = cartService.addCartItem(productId, num, request, response);
        return "cartSuccess";
    }

    @RequestMapping("/cart")
    public String showCart(HttpServletRequest request, HttpServletResponse response, Model model){

        List<CartProduct> cartList = cartService.getCartProductList(request, response);
        model.addAttribute("cartList", cartList);
        return "cart";
    }
}
