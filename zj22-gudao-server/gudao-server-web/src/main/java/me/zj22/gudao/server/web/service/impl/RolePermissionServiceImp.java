package me.zj22.gudao.server.web.service.impl;

import me.zj22.gudao.server.web.dao.db.RolePermissionMapper;
import me.zj22.gudao.server.web.pojo.dto.RolePermission;
import me.zj22.gudao.server.web.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * daogu
 * Created by 袁鹏 on 2018/3/17.
 */
@Service
public class RolePermissionServiceImp implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public Integer delete(Map<String, Object> map) {
        return rolePermissionMapper.delete(map);
    }

    @Override
    public void deleteByRoleId(Integer roleId) {
        rolePermissionMapper.deleteByRoleId(roleId);
    }

    @Override
    public int insert(RolePermission record) {
        return rolePermissionMapper.insert(record);
    }
}
