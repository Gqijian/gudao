package me.zj22.gudao.server.web.service;


import me.zj22.gudao.server.web.pojo.dto.Role;
import me.zj22.gudao.server.web.pojo.vo.Page;

import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/3/17.
 */
public interface RoleService {
    //保存角色
    Integer save(Role role);

    /**查询所有后台管理员,分页*/
    Page<Role> findAll(Page<Role> page);

    List<Role> findRole();
}
