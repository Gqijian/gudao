package me.zj22.gudao.server.web.pojo.dto;

/**
 * @Program:zj22-gudao-server
 * @Description:用户角色
 * @Author Gqjian
 * @Create 2018/2/5 15:38:07
 */

public class Role {
    private Integer roleId;

    private String roleName;    //权限角色名称1

    private String description; //描述

    private Byte available; //是否可用，1，不可用，0，可用

    private Long createTime;    //创建时间

    private Integer createUser; //创建人

    private Long updateTime;    //修改时间

    private Integer updateUser; //修改人

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }
}