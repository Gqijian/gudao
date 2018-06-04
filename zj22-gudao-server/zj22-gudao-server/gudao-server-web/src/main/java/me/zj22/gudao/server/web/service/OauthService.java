package me.zj22.gudao.server.web.service;


import me.zj22.gudao.server.web.pojo.vo.SubscribeUserInfo;

import java.io.UnsupportedEncodingException;

/**
 * 微信授权
 * daogu
 * Created by 袁鹏 on 2018/2/8.
 */
public interface OauthService {

    /**
     * 微信授权消息处理
     * @return
     */
    String onMessageAchieved() throws UnsupportedEncodingException;

    /**
     * 将授权后用户信息存储到数据库
     * @param userInfo 微信用户的相信信息
     * @return 添加到数据库的用户信息的id
     */
    Integer saveWechatUserInfo(SubscribeUserInfo userInfo);


    String MessagePage(Integer scene_id) throws UnsupportedEncodingException;

    /**
     * 获取公众号用户信息, 并且保证该信息在数据库中存在
     * @param code
     * @return
     */
    SubscribeUserInfo getSubscribeUserInfoAndSaveIt(String code);

    /**
     * 通过code获取用户微信公众号信息
     * @param code 授权的code
     * @return 用户微信公众号信息
     */
    SubscribeUserInfo getSubscribeUserInfo(String code);

    String payWeiXin() throws UnsupportedEncodingException;
}
