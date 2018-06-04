package me.zj22.gudao.server.web.exception;

import me.zj22.gudao.server.web.enums.ResultEnum;

/**
 * 自定义异常
 * daogu
 * Created by 袁鹏 on 2018/2/6.
 */
public class daoGuException extends  RuntimeException {
    private Integer code;

    public daoGuException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public daoGuException(Integer code, String message){
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
