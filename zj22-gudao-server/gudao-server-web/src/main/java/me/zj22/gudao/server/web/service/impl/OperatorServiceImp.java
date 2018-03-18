package me.zj22.gudao.server.web.service.impl;

import me.zj22.gudao.server.web.dao.db.OperatorMapper;
import me.zj22.gudao.server.web.pojo.dto.Operation;
import me.zj22.gudao.server.web.pojo.dto.Operator;
import me.zj22.gudao.server.web.pojo.vo.Page;
import me.zj22.gudao.server.web.service.OperatorService;
import me.zj22.gudao.server.web.utils.MD5Util;
import me.zj22.gudao.server.web.utils.TimeParse;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/3/5.
 */
@Service
public class OperatorServiceImp implements OperatorService {

    @Autowired
    private OperatorMapper operatorMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public Operator findSeller(Operator operator) {
        return operatorMapper.findSeller(operator);
    }

    @Override
    public Page<Operator> findAll(Page<Operator> page) {
        page.setList(operatorMapper.findPageList(page));
        page.setTotalRecord(operatorMapper.findCount(page));
        return page;
    }

    @Override
    public Integer delete(String[] pks) {
        return operatorMapper.delete(pks);
    }

    @Override
    public Operator selectByPrimaryKey(Integer operatorId) {
        return operatorMapper.selectByPrimaryKey(operatorId);
    }

    @Override
    public Integer freeze(Operator operator) {
        return operatorMapper.freeze(operator);
    }

    @Override
    public Integer nofreeze(Operator operator) {
        return operatorMapper.nofreeze(operator);
    }

    @Override
    public int insert(Operator record) {
        Operator op =  (Operator) request.getSession().getAttribute("seller");
        //初始值填充
        record.setCreateTime(TimeParse.Time2NUIX(TimeParse.NUIX2Time(new Date())));
        record.setUpdateTime(TimeParse.Time2NUIX(TimeParse.NUIX2Time(new Date())));
        record.setAvailable(1);
        record.setPassword(MD5Util.md5(record.getPassword()));//密码加密
        record.setUpdateUser(op.getRealName());
        record.setCreatUser(op.getRealName());
        return operatorMapper.insert(record);
    }
}
