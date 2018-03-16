package me.zj22.gudao.server.web.service.impl;

import me.zj22.gudao.server.web.dao.db.OperatorMapper;
import me.zj22.gudao.server.web.pojo.dto.Operation;
import me.zj22.gudao.server.web.pojo.dto.Operator;
import me.zj22.gudao.server.web.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/3/5.
 */
@Service
public class OperatorServiceImp implements OperatorService {

    @Autowired
    private OperatorMapper operatorMapper;

    @Override
    public Operator findSeller(Operator operator) {
        return operatorMapper.findSeller(operator);
    }
}
