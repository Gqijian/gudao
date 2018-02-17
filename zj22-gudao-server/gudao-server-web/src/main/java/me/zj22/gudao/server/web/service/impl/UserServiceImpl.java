package me.zj22.gudao.server.web.service.impl;

import me.zj22.gudao.server.web.pojo.dto.User;
import me.zj22.gudao.server.web.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/5.
 */
@Service
public class UserServiceImpl implements UserSerivce {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Integer userId) {
        return 0;
    }

    @Override
    public int insert(User record) {
        return 0;
    }

    @Override
    public int insertSelective(User record) {
        return 0;
    }

    @Override
    public User selectByPrimaryKey(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return 0;
    }
}
