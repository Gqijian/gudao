package me.zj22.gudao.server.web.pojo.vo;

import com.alibaba.fastjson.JSONObject;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/8.
 */
public class RequestResult {
    private int errcode;
    private String errmsg;

    public RequestResult(JSONObject jsonObject) {
        this.errcode = jsonObject.getIntValue("errcode");
        this.errmsg = jsonObject.getString("errmsg");
    }

    public int getErrcode() {
        return this.errcode;
    }

    public String getErrmsg() {
        return this.errmsg;
    }

    public String toString() {
        return "RequestResult [errcode=" + this.errcode + ", errmsg=" + this.errmsg + "]";
    }
}

