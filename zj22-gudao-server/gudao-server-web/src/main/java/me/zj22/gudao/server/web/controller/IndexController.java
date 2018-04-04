package me.zj22.gudao.server.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Program:gdao
 * @Description:
 * @Author Gqjian
 * @Create 2018/3/29 00:11:51
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String showIndex(){

        return "frontPage/index";
    }
}
