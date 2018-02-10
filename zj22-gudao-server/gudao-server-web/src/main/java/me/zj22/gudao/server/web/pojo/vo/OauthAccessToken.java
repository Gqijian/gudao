package me.zj22.gudao.server.web.pojo.vo;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/8.
 */
public class OauthAccessToken extends AccessToken {
    private String refresh_token;
    private String openid;
    private String scope;

    public OauthAccessToken() {
    }

    public String getRefresh_token() {
        return this.refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return this.openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}

