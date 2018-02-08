package me.zj22.gudao.server.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/8.
 */
@Configuration
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

    private static String WECHAT_PARTNER_KEY;

    private static String WECHAT_TRADE_TYPE;

    private static String WECHAT_KEY;

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
        WECHAT_PARTNER_KEY = properties.getProperty("wechat.mch.id");
        WECHAT_MCH_ID = properties.getProperty("wechat.partner.key");
        WECHAT_TRADE_TYPE = properties.getProperty("wechat.trade.type");
        WECHAT_KEY = properties.getProperty("wechat.key");
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

    public static String getWechatPartnerKey() {
        return WECHAT_PARTNER_KEY;
    }

    public static String getWechatTradeType() {
        return WECHAT_TRADE_TYPE;
    }

    public static String getWechatKey() {
        return WECHAT_KEY;
    }
}
