package me.zj22.gudao.server.web.pojo.dto;

/**
 * @Program:zj22-gudao-server
 * @Description:返点表
 * @Author Gqjian
 * @Create 2018/2/5 15:38:07
 */

public class Rebate {
    private Integer rebateId;

    private Double rebateRatio; //返点比例

    private Byte available; //是否可用，1，可用，0，不可用

    private Long createTime;    //创建时间

    private String createUser;  //创建人

    private Long updateTime;    //修改时间

    private String updateUser;  //修改人

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