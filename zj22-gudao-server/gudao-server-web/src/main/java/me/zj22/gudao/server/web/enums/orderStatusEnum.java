package me.zj22.gudao.server.web.enums;

/**
 * 订单状态
 * daogu
 * Created by 袁鹏 on 2018/2/6.
 */
public enum OrderStatusEnum implements  CodeEnum {
    NEW((byte)0, "新订单"),
    FINIFSHED((byte)1, "完结"),
    CANCEL((byte)2, "已取消"),
    ;


    private byte code;
    private String message;

    OrderStatusEnum(Byte code, String message){
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
