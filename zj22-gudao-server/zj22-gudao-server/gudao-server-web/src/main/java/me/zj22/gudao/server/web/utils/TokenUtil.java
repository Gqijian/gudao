package me.zj22.gudao.server.web.utils;

import com.alibaba.fastjson.JSONObject;
import me.zj22.gudao.server.web.enums.RequestMethodType;
import me.zj22.gudao.server.web.handler.HttpsRequestHandler;
import me.zj22.gudao.server.web.pojo.vo.AccessToken;
import me.zj22.gudao.server.web.pojo.vo.OauthAccessToken;
import me.zj22.gudao.server.web.pojo.vo.RequestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/8.
 */
public class TokenUtil {
    private static final Logger LOG = LoggerFactory.getLogger(TokenUtil.class);

    public TokenUtil() {
    }

    public static String getRandomToken(int tokenLength) {
        char[] charTable = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        StringBuffer tokenBuffer = new StringBuffer();

        for(int i = 0; i < tokenLength; ++i) {
            tokenBuffer.append(charTable[(new Random()).nextInt(charTable.length)]);
        }

        return tokenBuffer.toString();
    }

    public static AccessToken getAccessToken(String appId, String appSecret) {
        AccessToken accessToken = null;
        String requestUrl = (new String("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET")).replace("APPID", appId).replace("APPSECRET", appSecret);
        JSONObject jsonObject = HttpsRequestHandler.httpsRequest(requestUrl, RequestMethodType.GET, (String)null);
        if(null != jsonObject) {
            if(jsonObject.containsKey("errcode")) {
                accessToken = null;
                RequestResult requestResult = new RequestResult(jsonObject);
                LOG.error("获取token失败 " + requestResult.toString());
            } else {
                accessToken = new AccessToken();
                accessToken.setAccess_token(jsonObject.getString("access_token"));
                accessToken.setExpires_in(jsonObject.getIntValue("expires_in"));
            }
        }

        return accessToken;
    }

    public static OauthAccessToken getOauthAccessToken(String appId, String appSecret, String code) {
        String requestUrl = (new String("https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code")).replace("APPID", appId).replace("SECRET", appSecret).replace("CODE", code);
        JSONObject jsonObject = HttpsRequestHandler.httpsRequest(requestUrl, RequestMethodType.GET, (String)null);
        if(null != jsonObject) {
            if(!jsonObject.containsKey("errcode")) {
                OauthAccessToken accessToken1 = new OauthAccessToken();
                accessToken1.setAccess_token(jsonObject.getString("access_token"));
                accessToken1.setExpires_in(jsonObject.getIntValue("expires_in"));
                accessToken1.setRefresh_token(jsonObject.getString("refresh_token"));
                accessToken1.setOpenid(jsonObject.getString("openid"));
                accessToken1.setScope(jsonObject.getString("scope"));
                return accessToken1;
            }

            RequestResult accessToken = new RequestResult(jsonObject);
            LOG.error("获取token失败 " + accessToken.toString());
        }

        return null;
    }

    public static String getOauthAuthorizeBaseUrl(String appId, String redirectUri, String state) {
        return getOauthAuthorizeUrl(appId, redirectUri, "snsapi_base", state);
    }

    public static String getOauthAuthorizeUserInfoUrl(String appId, String redirectUri, String state) {
        return getOauthAuthorizeUrl(appId, redirectUri, "snsapi_userinfo", state);
    }

    private static String getOauthAuthorizeUrl(String appId, String redirectUri, String scope, String state) {
        String url = (new String("https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect")).replace("APPID", appId).replace("REDIRECT_URI", redirectUri).replace("SCOPE", scope);
        if(state != null) {
            url = url.replace("STATE", state);
        }

        return url;
    }
}

