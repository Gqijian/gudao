package me.zj22.gudao.server.web.pojo.vo;

import me.zj22.gudao.server.web.constants.ResponseCodeConsts;

/**
 * @author Zhangchengwei
 * @since 2018-02-04
 */
public class JsonResponse<T> {

    /**
     * 返回码
     */
    private final Integer code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 默认标识码为成功
     */
    public JsonResponse() {
        this.code = ResponseCodeConsts.SUCCESS;
    }

    public JsonResponse(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
