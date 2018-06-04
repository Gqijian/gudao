package me.zj22.gudao.server.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.zj22.gudao.server.web.pojo.vo.JsonResponse;
import me.zj22.gudao.server.web.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhangchengwei
 * @since 2018-01-28
 */
@Api("Demo相关接口")
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @ApiOperation(notes = "测试获取一个用户的信息", value="测试获取一个用户的昵称", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "query", dataType = "string", required = true, value = "需要获取用户的id")
    })
    @GetMapping("/hello")
    public JsonResponse<String> hello(String id) {
        String result = demoService.sayHello(id);
        JsonResponse<String> json = new JsonResponse<>();
        json.setData(result);
        return json;
    }
}
