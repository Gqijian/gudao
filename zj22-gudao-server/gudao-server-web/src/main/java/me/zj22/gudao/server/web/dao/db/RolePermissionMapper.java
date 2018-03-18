package me.zj22.gudao.server.web.dao.db;

import me.zj22.gudao.server.web.pojo.dto.RolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;


@Mapper
public interface RolePermissionMapper {
    //添加未有的权限
    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    //删除选择的操作者
    Integer delete(Map<String, Object> map);

    //根据role_id删除
    void deleteByRoleId(Integer roleId);
}