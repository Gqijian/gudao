package me.zj22.gudao.server.web.controller;

import me.zj22.gudao.server.web.config.WeChatConfig;
import me.zj22.gudao.server.web.constants.CookieConstant;
import me.zj22.gudao.server.web.enums.ResultEnum;
import me.zj22.gudao.server.web.pojo.dto.Operator;
import me.zj22.gudao.server.web.service.OperatorService;
import me.zj22.gudao.server.web.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

/**
 * 卖家用户
 * daogu
 * Created by 袁鹏 on 2018/2/20.
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private OperatorService operatorService;

    @RequestMapping("/auth")
    public ModelAndView auth() {
        return new ModelAndView("login");
    }

    @RequestMapping("/login")
    public ModelAndView login(Operator operator,
                              HttpServletRequest request,
                              HttpServletResponse response,
                              Map<String, Object> map) {

        //1.查询是否存在用户
        Operator seller = operatorService.findSeller(operator);
        if (seller == null) {
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url", "/gudao/seller/auth");
            return new ModelAndView("common/error");
        }
        //2. 设置session 过期时间30分钟 web.xml
        request.getSession().setAttribute("seller",seller);
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.EXPIRE;

        //3. 设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);
        return new ModelAndView("redirect:" + WeChatConfig.getWechatUrl() + "/base/goURL/main");
//        return new ModelAndView("main");
//        return new ModelAndView("redirect:" + WeChatConfig.getWechatUrl() + "/seller/order/list");

    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String, Object> map) {
        //1. 清除seller session
        request.getSession().removeAttribute("seller");
        //2. 从cookie里查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //3. 清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }

        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url", "/gudao/seller/order/list");
        return new ModelAndView("common/success", map);
    }
}
