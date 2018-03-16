package me.zj22.gudao.server.web.dao.db;

import me.zj22.gudao.server.web.pojo.dto.Operation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperationMapper {
    int deleteByPrimaryKey(Integer opId);

    int insert(Operation record);

    int insertSelective(Operation record);

    Operation selectByPrimaryKey(Integer opId);

    int updateByPrimaryKeySelective(Operation record);

    int updateByPrimaryKey(Operation record);

    //查找用户权限
    List<Operation> findOperationByRoleId(Integer roleId);
}