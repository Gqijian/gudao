package me.zj22.gudao.server.web.utils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by fengzheng on 2018/5/1.
 * 全局缓存servletcontext
 */
@Component
public class ServletContextUtil {
    private static ServletContext serveltContext = null;
//    @Autowired
//    private static HttpSession session = null;
//    @Autowired
//    private static HttpServletRequest request = null;

    private ServletContextUtil(){};

    public synchronized static ServletContext get() {
        if(null == serveltContext) {
            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
            serveltContext = webApplicationContext.getServletContext();
        }
        return serveltContext;
    }

//    public synchronized static HttpSession getSession() {
//        if(null == session){
//            session = request.getSession();
//        }
//        return session;
//    }
}