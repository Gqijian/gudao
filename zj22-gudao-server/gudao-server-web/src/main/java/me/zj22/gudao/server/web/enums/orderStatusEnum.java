package me.zj22.gudao.server.web.enums;

/**
 * 订单状态
 * daogu
 * Created by 袁鹏 on 2018/2/6.
 */
public enum OrderStatusEnum {
    NEW((byte)0, "新订单"),
    FINIFSHED((byte)1, "完结"),
    CANCEL((byte)2, "已取消"),
    //由于表中没有支付状态，所以这里在订单状态中设置
    PAY_WAIT((byte)3, "等待支付"),

    PAY_FINISH((byte)4, "支付完成"),
    ;


    private Byte code;
    private String message;

    OrderStatusEnum(Byte code, String message){
        this.code = code;
        this.message = message;
    }

    public Byte getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
