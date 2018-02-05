package me.zj22.gudao.server.web.pojo.dto;

/**
 * @Program:zj22-gudao-server
 * @Description:积分表
 * @Author Gqjian
 * @Create 2018/2/5 15:38:07
 */

public class PointsKey {
    private Integer pointsId;

    private Integer orderId;    //订单id

    public Integer getPointsId() {
        return pointsId;
    }

    public void setPointsId(Integer pointsId) {
        this.pointsId = pointsId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}