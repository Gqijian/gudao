package me.zj22.gudao.server.web.monitor.datasource;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * Druid的Servlet
 * @author Zhangchengwei
 * @since 2017/12/19
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/monitor/druid/*",
        initParams={
//                @WebInitParam(name="allow",value="127.0.0.1"),// IP白名单 (没有配置或者为空，则允许所有访问)
//                @WebInitParam(name="deny",value="192.168.16.111,127.0.0.1"),// IP黑名单 (存在共同时，deny优先于allow)
                @WebInitParam(name="loginUsername",value="Zhangchengwei"),// 用户名
                @WebInitParam(name="loginPassword",value="123456"),// 密码
                @WebInitParam(name="resetEnable",value="false")// 禁用HTML页面上的“Reset All”功能
        })
public class DruidStatViewServlet extends StatViewServlet {

}