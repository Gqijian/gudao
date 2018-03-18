package me.zj22.gudao.server.web.service.impl;

import me.zj22.gudao.server.web.dao.db.OperationMapper;
import me.zj22.gudao.server.web.pojo.dto.Operation;
import me.zj22.gudao.server.web.pojo.dto.Operator;
import me.zj22.gudao.server.web.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/3/16.
 */
@Service
public class OperationServiceImp implements OperationService {

    @Autowired
    private OperationMapper operationMapper;

    @Override
    public List<Operation> findOperationByRoleId(Integer roleId) {
        return operationMapper.findOperationByRoleId(roleId);
    }

    @Override
    public Integer findOperationCount(Integer roleId) {
        return operationMapper.findOperationCount(roleId);
    }

    @Override
    public Integer delete(String[] pks) {
        return operationMapper.delete(pks);
    }

    @Override
    public List<Operation> findNoOperationByRoleId(Integer roleId) {
        return operationMapper.findNoOperationByRoleId(roleId);
    }

}
