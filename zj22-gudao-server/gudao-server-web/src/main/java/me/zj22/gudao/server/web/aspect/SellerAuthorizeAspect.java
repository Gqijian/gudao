package me.zj22.gudao.server.web.aspect;

import me.zj22.gudao.server.web.constants.CookieConstant;
import me.zj22.gudao.server.web.exception.SellerAuthorizeException;
import me.zj22.gudao.server.web.pojo.dto.Operator;
import me.zj22.gudao.server.web.utils.CookieUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录验证拦截器
 * daogu
 * Created by 袁鹏 on 2018/2/20.
 */
@Component
@Aspect
public class SellerAuthorizeAspect {

    private static final Logger LOG = LoggerFactory.getLogger(SellerAuthorizeAspect.class);

    @Pointcut("execution(public * me.zj22.gudao.server.web.controller.Seller*.*(..))" +
              "&& execution(public * me.zj22.gudao.server.web.controller.BaseAction.*(..))"+
            "&& !execution(public * me.zj22.gudao.server.web.controller.SellerUserController.*(..))")
    public void verify() {}

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            LOG.warn("【登录校验】Cookie中查不到token");
            throw new SellerAuthorizeException();
        }

        //去session里查询
        Operator seller = (Operator)request.getSession().getAttribute("seller");
        if (seller == null) {
            LOG.warn("【登录校验】session中没有该用户");
            throw new SellerAuthorizeException();
        }
    }
}
