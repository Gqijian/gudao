package me.zj22.gudao.server.web.dao.db;

import me.zj22.gudao.server.web.dao.model.RolePermission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RolePermissionMapper {
    int insert(RolePermission record);

    int insertSelective(RolePermission record);
}