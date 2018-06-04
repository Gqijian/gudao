package me.zj22.gudao.server.web.controller;

import com.alibaba.fastjson.JSONObject;
import me.zj22.gudao.server.web.config.WeChatConfig;
import me.zj22.gudao.server.web.constants.Constants;
import me.zj22.gudao.server.web.enums.RequestMethodType;
import me.zj22.gudao.server.web.handler.HttpsRequestHandler;
import me.zj22.gudao.server.web.pojo.vo.AccessToken;
import me.zj22.gudao.server.web.pojo.vo.JsapiTicket;
import me.zj22.gudao.server.web.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Program:gdao
 * @Description:
 * @Author Gqjian
 * @Create 2018/4/23 19:57:44
 */
@Controller
@RequestMapping("/static")
public class WeiXinConfigController {
    private static final Logger LOG = LoggerFactory.getLogger(WeiXinConfigController.class);

    @RequestMapping(value = "/getAddress", method = {RequestMethod.GET})
    @ResponseBody
    public Object geAddress(HttpServletRequest req, @RequestParam String cb, String _ ,String orderId) throws NoSuchAlgorithmException {
        //1.获取accessTocken，从缓存中拿出来
//        AccessToken accessToken = (AccessToken) ServletContextUtil.get().getAttribute("access_token");
//        JsapiTicket jt = (JsapiTicket)ServletContextUtil.get().getAttribute("jsapi_ticket");

        AccessToken accessToken = TokenUtil.getAccessToken( WeChatConfig.getWechatAppId(), WeChatConfig.getWechatAppSecret());
        JsapiTicket jt = JsapiTicketUtil.getJsapiTicket(accessToken);
        String ticket=jt.getTicket();
        //StringBuffer url=req.getRequestURL();//得到当前的url
        String url="http://yuanpeng.free.ngrok.cc/gudao/frontPage/orderInfo.html?orderId="+orderId;
        Map<String, String> map = SignUtil.sign(ticket, url.toString());
        req.setAttribute("sign", map);
        //跳转到config配置的html页面
        String json = JsonUtils.objectToJson(map);
        String result = "callback(" ;
        return result+json+")";
    }
}
