package me.zj22.gudao.server.web.controller;

import me.zj22.gudao.server.web.pojo.dto.Operation;
import me.zj22.gudao.server.web.pojo.dto.Role;
import me.zj22.gudao.server.web.pojo.vo.Page;
import me.zj22.gudao.server.web.service.OperationService;
import me.zj22.gudao.server.web.service.RolePermissionService;
import me.zj22.gudao.server.web.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * daogu
 * Created by 袁鹏 on 2018/3/17.
 */
@Controller
@RequestMapping("/seller/role")
public class SellerRoleController {

    private static final Logger LOG = LoggerFactory.getLogger(SellerRoleController.class);


    @Autowired
    private RoleService roleService;


    /**
     * 保存角色
     * @param role
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Integer save(Role role){
        roleService.save(role);
        return roleService.save(role);
    }


}
