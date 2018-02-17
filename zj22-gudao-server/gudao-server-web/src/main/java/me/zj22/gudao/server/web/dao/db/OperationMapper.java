package me.zj22.gudao.server.web.dao.db;

import me.zj22.gudao.server.web.pojo.dto.Operation;

public interface OperationMapper {
    int deleteByPrimaryKey(Integer opId);

    int insert(Operation record);

    int insertSelective(Operation record);

    Operation selectByPrimaryKey(Integer opId);

    int updateByPrimaryKeySelective(Operation record);

    int updateByPrimaryKey(Operation record);
}