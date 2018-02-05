package me.zj22.gudao.server.web.controller;

import me.zj22.gudao.server.web.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/5.
 */
@Controller
@RequestMapping("/product")
public class productInfoController {
    @Autowired
    private ProductInfoService productInfoService;
}
