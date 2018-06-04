package me.zj22.gudao.server.web.service;

import me.zj22.gudao.server.web.pojo.dto.User;
import me.zj22.gudao.server.web.pojo.vo.Page;

import java.util.List;

/**
 * @Description:
 * @Author Gqjian
 * @Create 2018/2/8 11:38
 */
public interface UserSerivce {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**查询用户列表,分页*/
    Page<User> findAllList(Page<User> page);

    /**根据用户昵称模糊查找查询用户*/
    Page<User> findUserByName(Page<User> page);

    /**查询所有用户,分页*/
    Page<User> findUserList(Page<User> page);

    //按照积分多少降序
    /**查询所有用户,分页*/
    Page<User> findUserListOrder(Page<User> page);

    User selectByOpenId(String openId);
}
