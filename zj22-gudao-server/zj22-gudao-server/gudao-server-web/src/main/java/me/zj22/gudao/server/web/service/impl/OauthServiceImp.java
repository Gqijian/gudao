package me.zj22.gudao.server.web.service.impl;


import me.zj22.gudao.server.web.config.WeChatConfig;
import me.zj22.gudao.server.web.constants.Constants;
import me.zj22.gudao.server.web.dao.db.wrap.UserWrapperMapper;
import me.zj22.gudao.server.web.pojo.dto.User;
import me.zj22.gudao.server.web.pojo.vo.OauthAccessToken;
import me.zj22.gudao.server.web.pojo.vo.SubscribeUserInfo;
import me.zj22.gudao.server.web.service.OauthService;
import me.zj22.gudao.server.web.service.UserInfoService;
import me.zj22.gudao.server.web.utils.TimeParse;
import me.zj22.gudao.server.web.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * 通过微信跳转过来的用户需要先通过openId实现登录等逻辑,然后再继续访问后面的页面
 * daogu
 * Created by 袁鹏 on 2018/2/8.
 */
@Service
public class OauthServiceImp implements OauthService {

    private static final Logger LOG = LoggerFactory.getLogger(OauthServiceImp.class);

    @Autowired
    private UserWrapperMapper userWrapperMapper;

    @Override
    public String onMessageAchieved() throws UnsupportedEncodingException {
        String backUrl = WeChatConfig.getWechatUrl()+"/weixin/wxCallBack";
        String state = null;

        String url = new String(Constants.OAUTH_AUTHORIZE_URL)
                .replace("APPID", WeChatConfig.getWechatAppId())
                .replace("REDIRECT_URI", URLEncoder.encode(backUrl,"UTF-8"))
                .replace("SCOPE","snsapi_userinfo");
        if (state != null) {
            url = url.replace("STATE", state);
        }
        return url;
    }


    @Override
    public String MessagePage(Integer scene_id) throws UnsupportedEncodingException {
        String backUrl = WeChatConfig.getWechatUrl()+"/weixin/getInfoPage";
        String state = String.valueOf(scene_id);

        String url = new String(Constants.OAUTH_AUTHORIZE_URL)
                .replace("APPID", WeChatConfig.getWechatAppId())
                .replace("REDIRECT_URI", URLEncoder.encode(backUrl,"UTF-8"))
                .replace("SCOPE","snsapi_userinfo");
        if (state != null) {
            url = url.replace("STATE", state);
        }
        return url;
    }

    @Override
    @Transactional
    public Integer saveWechatUserInfo(SubscribeUserInfo userInfo) {
        //安全验证
        if(userInfo != null) {
            User wechatUser = new User();
            wechatUser.setCity(userInfo.getCity());
            wechatUser.setCountry(userInfo.getCountry());
            wechatUser.setHeadImgUrl(userInfo.getHeadimgurl());
            wechatUser.setNickname(userInfo.getNickname());
            wechatUser.setOpenid(userInfo.getOpenid());
            wechatUser.setProvince(userInfo.getProvince());
            wechatUser.setSex(userInfo.getSex());
            wechatUser.setIntegral(0);//开始设置为0
            wechatUser.setTotal(0);//总积分0
            wechatUser.setCreateTime(TimeParse.Time2NUIX(TimeParse.NUIX2Time(new Date())));
            userWrapperMapper.insert(wechatUser);//返回自增主键

            return wechatUser.getUserId();
        }
        LOG.error("[保存用户] 用户信息为空, userInfo = {}", userInfo);
        return 0;
    }

    @Override
    @Transactional
    public SubscribeUserInfo getSubscribeUserInfoAndSaveIt(String code) {
         try {
            // 获取微信用户信息
            if(!"authdeny".equals(code)){
                SubscribeUserInfo userInfo = getSubscribeUserInfo(code);
                //如果用户早已经被存入数据库怎么办?这个地方需要先验证，
                //  目的:保证存微信用户信息这件事只发生一次
                String openId = userInfo.getOpenid();//根据用户opeanId查找是否已存入数据库
                LOG.info("[保存用户 ], openId = {}", openId);
                User wechatUser = userWrapperMapper.selectByOpenId(openId);
                if(wechatUser == null){
                    saveWechatUserInfo(userInfo); //将用户信息存入数据库
                }
                LOG.info(" [保存用户]=================================="+userInfo);
                return userInfo;
            }else{
                LOG.error("请求参数异常, code = {}", code);
                return null;
            }

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public SubscribeUserInfo getSubscribeUserInfo(String code) {
        try {
            // 获取权限信息
            OauthAccessToken accessToken = TokenUtil.getOauthAccessToken(
                    WeChatConfig.getWechatAppId(),
                    WeChatConfig.getWechatAppSecret(),
                    code);
            LOG.info(" [微信授权], accessToken = {}", accessToken);
            // 获取微信用户信息
            SubscribeUserInfo userInfo = UserInfoService.getSubscribeUserInfo(
                    accessToken.getAccess_token(), accessToken.getOpenid(), null);
            LOG.info(" [微信授权], userInfo = {}", userInfo);
            return userInfo;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public String payWeiXin() throws UnsupportedEncodingException {
        String backUrl = WeChatConfig.getWechatUrl()+"/wechat/topayCallBack";
        String state = null;

        String url = new String(Constants.OAUTH_AUTHORIZE_URL)
                .replace("APPID", WeChatConfig.getWechatAppId())
                .replace("REDIRECT_URI", URLEncoder.encode(backUrl,"UTF-8"))
                .replace("SCOPE","snsapi_userinfo");
        if (state != null) {
            url = url.replace("STATE", state);
        }
        return url;
    }
}
