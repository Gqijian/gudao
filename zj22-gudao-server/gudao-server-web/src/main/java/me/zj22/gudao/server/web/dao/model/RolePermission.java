package me.zj22.gudao.server.web.dao.model;

public class RolePermission {
    private Integer roleId;

    private Integer opId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer opId) {
        this.opId = opId;
    }
}