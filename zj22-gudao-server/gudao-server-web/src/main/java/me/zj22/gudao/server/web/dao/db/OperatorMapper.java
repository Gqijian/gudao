package me.zj22.gudao.server.web.dao.db;

import me.zj22.gudao.server.web.pojo.dto.Operation;
import me.zj22.gudao.server.web.pojo.dto.Operator;
import me.zj22.gudao.server.web.pojo.vo.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperatorMapper {
    int deleteByPrimaryKey(Integer operatorId);

    int insert(Operator record);

    int insertSelective(Operator record);

    Operator selectByPrimaryKey(Integer operatorId);

    int updateByPrimaryKeySelective(Operator record);

    int updateByPrimaryKey(Operator record);

    //用户登录
    Operator findSeller(Operator operator);

    /**查询所管理者,分页*/
    List<Operator> findPageList(Page<Operator> page);

    /**查询总记录数*/
    Integer findCount(Page<Operator> page);

    //删除选择的操作者
    Integer delete(String pks[]);

    //冻结和解冻账户
    Integer freeze(Operator operator);

    Integer nofreeze(Operator operator);

}