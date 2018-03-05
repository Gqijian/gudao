package me.zj22.gudao.server.web.handler;

import me.zj22.gudao.server.web.config.WeChatConfig;
import me.zj22.gudao.server.web.exception.SellerAuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/20.
 */
@ControllerAdvice
public class SellExceptionHandler {
    //拦截登录异常
    //http://yuanpeng.free.ngrok.cc/gudao/seller/login
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:"
        .concat(WeChatConfig.getWechatUrl())
        .concat("/seller/auth"));
    }
}
