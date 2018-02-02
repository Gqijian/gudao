package me.zj22.gudao.server.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhangchengwei
 * @since 2018-01-28
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/test")
    public Object test(){
        return "test";
    }

}
