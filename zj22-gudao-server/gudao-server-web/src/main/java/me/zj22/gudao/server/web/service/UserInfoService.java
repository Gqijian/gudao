package me.zj22.gudao.server.web.service;

import com.alibaba.fastjson.JSONObject;
import me.zj22.gudao.server.web.enums.RequestMethodType;
import me.zj22.gudao.server.web.handler.HttpsRequestHandler;
import me.zj22.gudao.server.web.pojo.vo.RequestResult;
import me.zj22.gudao.server.web.pojo.vo.SubscribeUserInfo;
import me.zj22.gudao.server.web.utils.WeChatJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/8.
 */
public class UserInfoService {
    private static final Logger LOG = LoggerFactory.getLogger(UserInfoService.class);

    public UserInfoService() {
    }

    public static SubscribeUserInfo getSubscribeUserInfo(String accessToken, String openId, String lang) {
        String uri = (new String("https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN")).replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        uri = lang == null?uri:uri.replace("zh_CN", lang);
        JSONObject subscribeUserInfo = HttpsRequestHandler.httpsRequest(uri, RequestMethodType.GET, (String)null);
        SubscribeUserInfo user = null;
        if(subscribeUserInfo.containsKey("errcode")) {
            RequestResult result = new RequestResult(subscribeUserInfo);
            LOG.error("获取微信用户信息失败: " + result.toString());
        } else {
            user = WeChatJsonUtil.parseJsonToSubscribeUserInfo(subscribeUserInfo);
        }

        return user;
    }
}
