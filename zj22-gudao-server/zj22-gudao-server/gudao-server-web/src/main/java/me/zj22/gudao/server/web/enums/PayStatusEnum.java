package me.zj22.gudao.server.web.enums;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/19.
 */
public enum  PayStatusEnum implements CodeEnum {

    PAY_WAIT((byte)0, "等待支付"),

    PAY_FINISH((byte)1, "支付完成"),
    ;

    private byte code;
    private String message;

    PayStatusEnum(Byte code, String message){
        this.code = code;
        this.message = message;
    }

    public byte getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
