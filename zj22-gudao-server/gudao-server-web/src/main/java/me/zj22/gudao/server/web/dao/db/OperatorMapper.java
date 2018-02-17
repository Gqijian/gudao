package me.zj22.gudao.server.web.dao.db;

import me.zj22.gudao.server.web.pojo.dto.Operator;

public interface OperatorMapper {
    int deleteByPrimaryKey(Integer operatorId);

    int insert(Operator record);

    int insertSelective(Operator record);

    Operator selectByPrimaryKey(Integer operatorId);

    int updateByPrimaryKeySelective(Operator record);

    int updateByPrimaryKey(Operator record);
}