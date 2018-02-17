package me.zj22.gudao.server.web.service.impl;

import me.zj22.gudao.server.web.pojo.dto.User;
import me.zj22.gudao.server.web.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zhangchengwei
 * @since 2018-01-28
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String sayHello(String id) {
        if (id == null || id.trim().length() <= 0) {// TODO 封装成用于验证字符串是否非空的方法
            throw new IllegalArgumentException("参数异常, 用户id是必填项.");
        }
        Integer userId;
        try {// TODO 封装成用于验证字符串是否为数字的方法
            userId = Integer.valueOf(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("参数异常, 用户id必须是数字.");
        }
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return "没得这个人(id : " + id + ").";
        } else {
            return "找到id为" + id + "的用户 : " + user.getNickname();
        }
    }
}
