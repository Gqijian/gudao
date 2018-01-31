package me.zj22.gudao.server.web.dao.db;

import me.zj22.gudao.server.web.dao.model.RolePermission;

public interface RolePermissionMapper {
    int insert(RolePermission record);

    int insertSelective(RolePermission record);
}