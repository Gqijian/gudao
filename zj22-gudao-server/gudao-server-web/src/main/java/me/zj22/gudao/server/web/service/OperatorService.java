package me.zj22.gudao.server.web.service;

import me.zj22.gudao.server.web.pojo.dto.Operation;
import me.zj22.gudao.server.web.pojo.dto.Operator;
import me.zj22.gudao.server.web.pojo.vo.Page;

import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/3/5.
 */
public interface OperatorService {

    Operator findSeller(Operator operator);

    /**查询所有后台管理员,分页*/
    Page<Operator> findAll(Page<Operator> page);

    //删除选择的操作者
    Integer delete(String pks[]);

    Operator selectByPrimaryKey(Integer operatorId);

    //冻结和解冻账户
    Integer freeze(Operator operator);

    Integer nofreeze(Operator operator);

    //插入管理员
    int insert(Operator record);

}
