package me.zj22.gudao.server.web.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.zj22.gudao.server.web.pojo.dto.User;
import me.zj22.gudao.server.web.pojo.vo.JsonResponse;
import me.zj22.gudao.server.web.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/5.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserSerivce userSerivce;

    @ApiOperation(notes = "测试获取一个用户的信息", value="测试获取一个用户", httpMethod = "Post")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", paramType = "query", dataType = "int", required = true, value = "需要获取用户的userId")
    })
    @PostMapping("/findUserById")
    @ResponseBody
    public JsonResponse<User> findUserByOpenId(Integer userId){
        User user = userSerivce.selectByPrimaryKey(userId);
        JsonResponse<User> json = new JsonResponse<>();
        json.setData(user);
        return json;
    }
}
