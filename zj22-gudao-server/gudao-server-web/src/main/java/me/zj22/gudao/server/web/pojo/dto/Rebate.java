package me.zj22.gudao.server.web.pojo.dto;

public class Rebate {
    private Integer rebateId;

    private Double rebateRatio;

    private Byte available;

    private Long createTime;

    private String createUser;

    private Long updateTime;

    private String updateUser;

    public Integer getRebateId() {
        return rebateId;
    }

    public void setRebateId(Integer rebateId) {
        this.rebateId = rebateId;
    }

    public Double getRebateRatio() {
        return rebateRatio;
    }

    public void setRebateRatio(Double rebateRatio) {
        this.rebateRatio = rebateRatio;
    }

    public Byte getAvailable() {
        return available;
    }

    public void setAvailable(Byte available) {
        this.available = available;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }
}