package me.zj22.gudao.server.web.service;

import me.zj22.gudao.server.web.pojo.dto.RolePermission;

import java.util.Map;

/**
 * daogu
 * Created by 袁鹏 on 2018/3/17.
 */
public interface RolePermissionService {

    //删除选择的操作者
    Integer delete(Map<String, Object> map);

    //根据role_id删除
    void deleteByRoleId(Integer roleId);

    //添加未有的权限
    int insert(RolePermission record);
}
