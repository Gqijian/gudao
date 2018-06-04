package me.zj22.gudao.server.web.pojo.vo;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/8.
 */
public class AccessToken {
    private String access_token;
    private int expires_in;

    public AccessToken() {
    }

    public String getAccess_token() {
        return this.access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return this.expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String toString() {
        return "AccessToken [access_token=" + this.access_token + ", expires_in=" + this.expires_in + "]";
    }
}
