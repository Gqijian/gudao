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

    //查询权限数
    Integer findOperationCount(Integer roleId);

    //查询未有的权限
    List<Operation> findNoOperationByRoleId(Integer roleId);

    //删除选择的权限
    Integer delete(String pks[]);
}