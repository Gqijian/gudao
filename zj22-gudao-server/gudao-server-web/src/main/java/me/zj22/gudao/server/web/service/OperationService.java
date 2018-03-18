package me.zj22.gudao.server.web.service;

import me.zj22.gudao.server.web.pojo.dto.Operation;
import me.zj22.gudao.server.web.pojo.dto.Operator;

import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/3/16.
 */
public interface OperationService {
    //查找用户权限
    List<Operation> findOperationByRoleId(Integer roleId);

    //查询权限数
    Integer findOperationCount(Integer roleId);

    //删除选择的权限
    Integer delete(String pks[]);

    //查询未有的权限
    List<Operation> findNoOperationByRoleId(Integer roleId);

}
