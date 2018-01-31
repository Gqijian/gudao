package me.zj22.gudao.server.web.dao.model;

public class Points extends PointsKey {
    private Integer createTime;

    private Integer pointsRecord;

    private Byte flag;

    private Integer userId;

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
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