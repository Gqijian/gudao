package me.zj22.gudao.server.web.dao.db;

import me.zj22.gudao.server.web.pojo.dto.Operator;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperatorMapper {
    int deleteByPrimaryKey(Integer operatorId);

    int insert(Operator record);

    int insertSelective(Operator record);

    Operator selectByPrimaryKey(Integer operatorId);

    int updateByPrimaryKeySelective(Operator record);

    int updateByPrimaryKey(Operator record);

    Operator findSeller(Operator operator);
}