package me.zj22.gudao.server.web.pojo.vo;




import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by fengzheng on 2018/5/11.
 */
public class WxMpTemplateMessage implements Serializable {
    private static final long serialVersionUID = 5063374783759519418L;

    /**
     * 接收者openid
     */
    private String toUser;

    /**
     * 模板ID
     */
    private String templateId;

    /**
     * <pre>
     * 跳小程序所需数据，不需跳小程序可不用传该数据
     * url和miniprogram都是非必填字段，若都不传则模板无跳转；若都传，会优先跳转至小程序。
     * 开发者可根据实际需要选择其中一种跳转方式即可。当用户的微信客户端版本不支持跳小程序时，将会跳转至url。
     * </pre>
     */
    private String url;
    /**
     * 模板跳转链接
     *
     * @see #url
     */
    private me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage.MiniProgram miniProgram;

    /**
     * 模板数据
     */
    private List<WxMpTemplateData> data = new ArrayList<>();

    public WxMpTemplateMessage() {
    }

    public static me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage.WxMpTemplateMessageBuilder builder() {
        return new me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage.WxMpTemplateMessageBuilder();
    }

    public String getToUser() {
        return this.toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getTemplateId() {
        return this.templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<WxMpTemplateData> getData() {
        return this.data;
    }

    public void setData(List<WxMpTemplateData> data) {
        this.data = data;
    }

    public void addWxMpTemplateData(WxMpTemplateData datum) {
        this.data.add(datum);
    }

    public me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage.MiniProgram getMiniProgram() {
        return this.miniProgram;
    }

    public void setMiniProgram(me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage.MiniProgram miniProgram) {
        this.miniProgram = miniProgram;
    }

}