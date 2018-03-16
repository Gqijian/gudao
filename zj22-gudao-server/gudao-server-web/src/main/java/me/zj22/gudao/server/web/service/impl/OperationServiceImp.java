package me.zj22.gudao.server.web.service.impl;

import me.zj22.gudao.server.web.dao.db.OperationMapper;
import me.zj22.gudao.server.web.pojo.dto.Operation;
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
}
