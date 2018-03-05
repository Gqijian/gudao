package me.zj22.gudao.server.web.dao.db.wrap;

import me.zj22.gudao.server.web.dao.db.UserMapper;
import me.zj22.gudao.server.web.pojo.dto.User;
import me.zj22.gudao.server.web.pojo.vo.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/23.
 */
@Mapper
public interface UserWrapperMapper extends UserMapper {
    //根据openid查找用户
    User selectByOpenId(String openid);

    /**根据用户昵称模糊查找查询用户 page中的keyword设置昵称*/
    List<User> findUserByName(Page<User> page);

    /**根据用户昵称模糊查找查询用户总数*/
    Integer findNicknameCount(Page<User> page);

    /**后台查询所有用户*/
    List<User> findAllWechatUser(Page<User> page);

    /**后台查询用户数*/
    Integer findWechatUserCount();

}
