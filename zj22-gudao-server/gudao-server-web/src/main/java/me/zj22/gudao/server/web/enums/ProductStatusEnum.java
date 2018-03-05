package me.zj22.gudao.server.web.enums;

/**
 * 商品在架状态
 * daogu
 * Created by 袁鹏 on 2018/2/5.
 */
public enum ProductStatusEnum implements  CodeEnum {
    UP((byte) 0, "在架"),
        DOWN((byte)1, "下架");

    private byte code;
    private String message;

    ProductStatusEnum(Byte code, String message){
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
