package me.zj22.gudao.server.web.console.dao;

import me.zj22.gudao.server.web.pojo.dto.Operator;

import java.util.List;

/**
 *
 * @Program:zj22-gudao-server
 * @Description:
 * @Author Gqjian
 * @Create 2018/2/22 17:20:46
 */
public interface OperatorDao {

    Operator login(Operator operator);

    Integer insertOperator(Operator operator);

    List<Operator> selectAllRoles(String openId);

    List<Operator> selectAll();
}
