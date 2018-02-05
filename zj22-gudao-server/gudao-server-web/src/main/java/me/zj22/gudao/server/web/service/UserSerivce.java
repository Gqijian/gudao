package me.zj22.gudao.server.web.service;

import me.zj22.gudao.server.web.pojo.dto.User;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/5.
 */
public interface UserSerivce {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
