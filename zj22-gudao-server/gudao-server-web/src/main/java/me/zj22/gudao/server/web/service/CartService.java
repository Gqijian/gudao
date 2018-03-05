package me.zj22.gudao.server.web.service;

import me.zj22.gudao.server.web.pojo.vo.CartProduct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Program:zj22-gudao-server
 * @Description:
 * @Author Gqjian
 * @Create 2018/2/11 00:54:24
 */
public interface CartService {

   String addCartItem(Integer productId, Integer num, HttpServletRequest request, HttpServletResponse response);

   List<CartProduct> getCartProductList(HttpServletRequest request, HttpServletResponse response);
}
