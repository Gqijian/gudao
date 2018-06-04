package me.zj22.gudao.server.web.pojo.vo;

/**
 * Created by fengzheng on 2018/5/1.
 */
public class JsapiTicket {
    private String ticket;
    private String expiresIn;
    public String getTicket() {
        return ticket;
    }
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
    public String getExpiresIn() {
        return expiresIn;
    }
    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

}