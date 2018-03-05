package me.zj22.gudao.server.web.console.dao;

import me.zj22.gudao.server.web.pojo.dto.Operation;

import java.util.List;

/**
 * @Program:zj22-gudao-server
 * @Description:
 * @Author Gqjian
 * @Create 2018/2/22 17:37:04
 */
public interface RoleDao {

    List<Operation> selectAllOperations();
}
