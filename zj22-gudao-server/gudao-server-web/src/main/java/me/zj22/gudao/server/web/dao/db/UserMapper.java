package me.zj22.gudao.server.web.dao.db;

import me.zj22.gudao.server.web.pojo.dto.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //根据openId查询用户
    User selectUserByOpenId(String openId);
}