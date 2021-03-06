package me.zj22.gudao.server.web.pojo.dto;

import me.zj22.gudao.server.web.utils.TimeParse;

/**
 * @Program:zj22-gudao-server
 * @Description:积分表
 * @Author Gqjian
 * @Create 2018/2/5 15:38:07
 */

public class Points extends PointsKey {
    private long createTime; //创建时间

    private Integer pointsRecord;   //积分

    private Byte flag;  //能否提现标志，1，已提现，0，未提现，

    private Integer userId; //用户id

    public String getCreateTimeToString() {
        //时间装换
        return TimeParse.NUIX2Time((int)(createTime/1000));
    }

    public String getAllFlag(){
        //能否提现
        if(flag == 1) {
            return "已提现";
        }
        else if(flag == 0) {
            return "未提现";
        }else {
            return "未知错误";
        }
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Integer getPointsRecord() {
        return pointsRecord;
    }

    public void setPointsRecord(Integer pointsRecord) {
        this.pointsRecord = pointsRecord;
    }

    public Byte getFlag() {
        return flag;
    }

    public void setFlag(Byte flag) {
        this.flag = flag;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}