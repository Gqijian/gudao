package me.zj22.gudao.server.web.enums;

/**
 * 返回给前端的状态
 * daogu
 * Created by 袁鹏 on 2018/2/6.
 */
public enum  ResultEnum {
    SUCCESS(0, "成功"),

    PARAM_ERROR(1, "参数不正确"),

    PRODUCT_NOT_EXIT(10,"商品不存在"),

    PRODUCT_STOCK_ERROR(11, "库存不正确"),

    ORDER_NOT_EXIT(12, "订单不存在"),

    ORDERDETAIL_NOT_EXIST(13, "订单详情不存在"),

    ORDER_STATUS_ERROR(14, "订单状态不正确"),

    ORDER_UPDATE_FAIL(15, "订单更新失败"),

    ORDER_PAY_STATUS_ERROR(16, "订单支付状态不正确"),

    CART_EMPTY(17, "购物车为空"),

    ORDER_OWNER_ERRR(18, "当前订单不属于您"),

    WXPAY_NOTIFY_MONEY_ERROR(19, "异步支付微信金额不一致"),

    ORDER_CALCLE_SUCCESS(20, "订单取消成功"),

    ORDER_FINISH_SUCCESS(21, "订单完结成功"),

    PRODUCT_STATUS_ERROR(22, "商品状态不正确"),

    LOGIN_FAIL(23, "登录失败"),

    LOGOUT_SUCCESS(24, "登出成功"),
    ;

    private  Integer code;
    private  String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
