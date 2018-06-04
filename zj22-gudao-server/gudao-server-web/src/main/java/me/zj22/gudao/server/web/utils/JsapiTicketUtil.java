package me.zj22.gudao.server.web.utils;

import com.alibaba.fastjson.JSONObject;
import me.zj22.gudao.server.web.config.WeChatConfig;
import me.zj22.gudao.server.web.constants.Constants;
import me.zj22.gudao.server.web.enums.RequestMethodType;
import me.zj22.gudao.server.web.handler.HttpsRequestHandler;
import me.zj22.gudao.server.web.pojo.vo.AccessToken;
import me.zj22.gudao.server.web.pojo.vo.JsapiTicket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by fengzheng on 2018/5/1.
 */
public class JsapiTicketUtil {
    private static final Logger LOG = LoggerFactory.getLogger(JsapiTicketUtil.class);
    public static JsapiTicket getJsapiTicket(AccessToken accessToken){
        //2.用第一步拿到的access_token 采用http GET方式请求获得jsapi_ticket
        JsapiTicket ticket=new JsapiTicket();
        String requestUrl = new String(Constants.JS_API_TICKET_URL)
                .replace("ACCESS_TOKEN",accessToken.getAccess_token());
        JSONObject jsonObject = HttpsRequestHandler.httpsRequest(requestUrl, RequestMethodType.GET, (String)null);
        if(jsonObject.getString("errcode").equals("0")){
            ticket.setTicket(jsonObject.getString("ticket"));
            ticket.setExpiresIn(jsonObject.getString("expires_in"));
        }else{
            LOG.error("error");
        }
        return ticket;
    }
}