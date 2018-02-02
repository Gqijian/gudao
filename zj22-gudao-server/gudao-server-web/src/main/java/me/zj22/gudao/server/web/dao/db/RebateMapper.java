package me.zj22.gudao.server.web.dao.db;

import me.zj22.gudao.server.web.dao.model.Rebate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RebateMapper {
    int deleteByPrimaryKey(Integer rebateId);

    int insert(Rebate record);

    int insertSelective(Rebate record);

    Rebate selectByPrimaryKey(Integer rebateId);

    int updateByPrimaryKeySelective(Rebate record);

    int updateByPrimaryKey(Rebate record);
}