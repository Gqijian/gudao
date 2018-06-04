package me.zj22.gudao.server.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/8.
 */
@Configuration
@Component
public class WeChatConfig {

    private static String WECHAT_APP_ID;

    private static String WECHAT_APP_SECRET;

    /**
     * 微信设置的token
     */
    private static String WECHAT_APP_TOKEN;

    private static String WECHAT_URL;

    private static String WECHAT_TEMPLATE_ID;


    /**
     * 微信支付设置
     */
    private static String WECHAT_MCH_ID;

    private static String WECHAT_MCHKEY;

    private static String WECHAT_KEYPATH;

    private static String WECHAT_TRADE_TYPE;

    private static String WECHAT_KEY;

    private static String WECHAT_NOTIFYURL;

    /**public WeChatConfig(Environment env) {
     WECHAT_APP_ID               = env.getProperty("wechat.app.id");
     WECHAT_APP_SECRET           = env.getProperty("wechat.app.secret");
     WECHAT_APP_TOKEN            = env.getProperty("wechat.app.token");
     }*/

    static {
        Properties properties = new Properties();
        //当前类
        InputStream inputStream = WeChatConfig.class.getResourceAsStream("/application.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        WECHAT_APP_ID = properties.getProperty("wechat.app.id");
        WECHAT_APP_SECRET = properties.getProperty("wechat.app.secret");
        WECHAT_APP_TOKEN = properties.getProperty("wechat.app.token");
        WECHAT_URL = properties.getProperty("wechat.URL");
        WECHAT_TEMPLATE_ID = properties.getProperty("wechat.Template.ID");

        //微信支付相关
        WECHAT_MCHKEY = properties.getProperty("wechat.mchKey");
        WECHAT_MCH_ID = properties.getProperty("wechat.mch.id");
        WECHAT_KEYPATH = properties.getProperty("wechat.keyPath");
        WECHAT_TRADE_TYPE = properties.getProperty("wechat.trade.type");
        WECHAT_KEY = properties.getProperty("wechat.key");
        WECHAT_NOTIFYURL = properties.getProperty("wechat.notifyUrl");

    }

    public static String getWechatNotifyurl() {
        return WECHAT_NOTIFYURL;
    }

    public static void setWechatNotifyurl(String wechatNotifyurl) {
        WECHAT_NOTIFYURL = wechatNotifyurl;
    }

    public static String getWechatMchkey() {
        return WECHAT_MCHKEY;
    }

    public static void setWechatMchkey(String wechatMchkey) {
        WECHAT_MCHKEY = wechatMchkey;
    }

    public static String getWechatKeypath() {
        return WECHAT_KEYPATH;
    }

    public static void setWechatKeypath(String wechatKeypath) {
        WECHAT_KEYPATH = wechatKeypath;
    }

    public static String getWechatAppId() {
        return WECHAT_APP_ID;
    }

    public static String getWechatAppSecret() {
        return WECHAT_APP_SECRET;
    }

    public static String getWechatAppToken() {
        return WECHAT_APP_TOKEN;
    }

    public static String getWechatUrl() {
        return WECHAT_URL;
    }

    public static String getWechatTemplateId() {
        return WECHAT_TEMPLATE_ID;
    }

    public static String getWechatMchId() {
        return WECHAT_MCH_ID;
    }

    public static String getWechatTradeType() {
        return WECHAT_TRADE_TYPE;
    }

    public static String getWechatKey() {
        return WECHAT_KEY;
    }

    public static void setWechatAppId(String wechatAppId) {
        WECHAT_APP_ID = wechatAppId;
    }

    public static void setWechatAppSecret(String wechatAppSecret) {
        WECHAT_APP_SECRET = wechatAppSecret;
    }

    public static void setWechatAppToken(String wechatAppToken) {
        WECHAT_APP_TOKEN = wechatAppToken;
    }

    public static void setWechatUrl(String wechatUrl) {
        WECHAT_URL = wechatUrl;
    }

    public static void setWechatTemplateId(String wechatTemplateId) {
        WECHAT_TEMPLATE_ID = wechatTemplateId;
    }

    public static void setWechatMchId(String wechatMchId) {
        WECHAT_MCH_ID = wechatMchId;
    }

    public static void setWechatTradeType(String wechatTradeType) {
        WECHAT_TRADE_TYPE = wechatTradeType;
    }

    public static void setWechatKey(String wechatKey) {
        WECHAT_KEY = wechatKey;
    }
}
