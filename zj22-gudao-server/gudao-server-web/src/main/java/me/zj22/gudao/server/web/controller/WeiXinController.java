package me.zj22.gudao.server.web.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.zj22.gudao.server.web.pojo.dto.User;
import me.zj22.gudao.server.web.pojo.vo.JsonResponse;
import me.zj22.gudao.server.web.pojo.vo.SubscribeUserInfo;
import me.zj22.gudao.server.web.service.OauthService;
import me.zj22.gudao.server.web.service.UserSerivce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * 微信相关
 * daogu
 * Created by 袁鹏 on 2018/2/8.
 */
@Controller
@RequestMapping("/weixin")
public class WeiXinController {

    private static final Logger LOG = LoggerFactory.getLogger(WeiXinController.class);

    @Autowired
    private OauthService oauthService;

    @Autowired
    private UserSerivce userSerivce;


    @ApiOperation(notes = "微信授权接口", value="微信授权接口", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", paramType = "query", dataType = "string", required = true, value = "微信服务器传入的code"),
            @ApiImplicitParam(name = "state", paramType = "query", dataType = "string", required = true, value = "设置的自带参数")
    })
    @RequestMapping(path="/auth", method = {RequestMethod.POST,RequestMethod.GET})// @GetMapping是@RequestMapping(method = RequestMethod.GET)的缩写
    //点击收藏时接入的地址
    public String wechatOauth() {
        LOG.info("进入到授权......");
        String viewName = null;
        try {
            viewName = oauthService.onMessageAchieved();//viewName即返回的url
            LOG.info("viewName = {}", viewName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "redirect:"+viewName; //页面重定向
    }


    /**
     * 回调地址 之后跳转到用户收藏页面 并将用户信息存储到session中 并存入数据库
     * @param code
     */
    @GetMapping(path="/wxCallBack")
    @ResponseBody
    public JsonResponse callBack(@RequestParam(value ="code") String code){
        LOG.info("进入到授权 code = {}", code);
        //也可以通过设置参数的方式来获取code
//        User wechatUser=null;
        SubscribeUserInfo userInfo = oauthService.getSubscribeUserInfoAndSaveIt(code);

        //根据用户opeanId查找是否已存入数据库
//        wechatUser = userSerivce.selectByPrimaryKey(Integer.valueOf(userInfo.getOpenid()));
        LOG.debug("\n\n\n用户授权返回数据:" + userInfo + "\n\n\n");
        JsonResponse json = new JsonResponse();
        json.setData("success");
        json.setMessage("微信授权成功");
        return json;
//        return "forward:/user/showCollectionList.action?id=" + wechatUser.getUserId();
    }
}
