package me.zj22.gudao.server.web.service.impl;

import me.zj22.gudao.server.web.dao.db.UserMapper;
import me.zj22.gudao.server.web.dao.db.wrap.UserWrapperMapper;
import me.zj22.gudao.server.web.pojo.dto.User;
import me.zj22.gudao.server.web.pojo.vo.Page;
import me.zj22.gudao.server.web.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/5.
 */
@Service
public class UserServiceImp implements UserSerivce {

    @Autowired
    private UserWrapperMapper userWrapperMapper;

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
        return userWrapperMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return 0;
    }

    @Override
    public Page<User> findAllList(Page<User> page) {
        List<User> wechatUserList = userWrapperMapper.findAllWechatUser(page);
        Integer totalRecord = userWrapperMapper.findWechatUserCount();
        page.setList(wechatUserList);
        page.setTotalRecord(totalRecord);
        return page;
    }

    @Override
    public Page<User> findUserByName(Page<User> page) {
        page.setTotalRecord(userWrapperMapper.findNicknameCount(page));
        page.setList(userWrapperMapper.findUserByName(page));
        return page;
    }


}
