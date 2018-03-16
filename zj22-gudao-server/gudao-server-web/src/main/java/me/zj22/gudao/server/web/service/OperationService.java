package me.zj22.gudao.server.web.service;

import me.zj22.gudao.server.web.pojo.dto.Operation;

import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/3/16.
 */
public interface OperationService {
    //查找用户权限
    List<Operation> findOperationByRoleId(Integer roleId);
}
