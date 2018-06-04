package me.zj22.gudao.server.web.utils;

import com.alibaba.fastjson.JSONObject;
import me.zj22.gudao.server.web.pojo.vo.SubscribeUserInfo;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/8.
 */
public class WeChatJsonUtil {
    public WeChatJsonUtil() {
    }

    public static final SubscribeUserInfo parseJsonToSubscribeUserInfo(JSONObject subscribeUserInfo) {
        SubscribeUserInfo user = new SubscribeUserInfo();
        user.setCity(subscribeUserInfo.getString("city"));
        user.setCountry(subscribeUserInfo.getString("country"));
        user.setHeadimgurl(subscribeUserInfo.getString("headimgurl"));
        user.setLanguage(subscribeUserInfo.getString("language"));
        user.setNickname(subscribeUserInfo.getString("nickname"));
        user.setOpenid(subscribeUserInfo.getString("openid"));
        user.setProvince(subscribeUserInfo.getString("province"));
        user.setSex(subscribeUserInfo.getIntValue("sex"));
        user.setSubscribe(subscribeUserInfo.getIntValue("subscribe"));
        user.setSubscribeTime(subscribeUserInfo.getIntValue("subscribe_time"));
        if(subscribeUserInfo.containsKey("unionid")) {
            user.setUnionid(subscribeUserInfo.getString("unionid"));
        }

        return user;
    }
}
