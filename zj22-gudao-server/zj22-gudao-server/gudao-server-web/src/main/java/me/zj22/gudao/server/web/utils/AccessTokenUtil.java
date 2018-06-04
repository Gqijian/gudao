package me.zj22.gudao.server.web.utils;

import me.zj22.gudao.server.web.config.WeChatConfig;
import me.zj22.gudao.server.web.controller.WeiXinConfigController;
import me.zj22.gudao.server.web.pojo.vo.AccessToken;
import me.zj22.gudao.server.web.pojo.vo.JsapiTicket;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

/**
 * Created by fengzheng on 2018/5/1.
 */
@Component
public class AccessTokenUtil {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(WeiXinConfigController.class);
    /**
     * 设置access_token
     */
    public static void initAndSetAccessToken() {
        log.info("execute initAndSetAccessToken Start : {}", System.currentTimeMillis());

            AccessToken accessToken = TokenUtil.getAccessToken( WeChatConfig.getWechatAppId(), WeChatConfig.getWechatAppSecret());
            if(null != accessToken) {
                /**
                 * cache access_token
                 */
                ServletContext sc = ServletContextUtil.get();
//                HttpSession sc = ServletContextUtil.getSession();
                sc.removeAttribute("access_token");
                sc.setAttribute("access_token", accessToken);

                /**
                 * cache jsapi_ticket
                 */
                JsapiTicket jsApiTicket = JsapiTicketUtil.getJsapiTicket(accessToken);
                if(null != jsApiTicket) {
                    sc.removeAttribute("jsapi_ticket");
                    sc.setAttribute("jsapi_ticket", jsApiTicket);
                }
                //这里可以向数据库中写access_token
            }

        log.info("execute initAndSetAccessToken End   : {}", System.currentTimeMillis());
    }
}