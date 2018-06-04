package me.zj22.gudao.server.web.controller;

import me.zj22.gudao.server.web.pojo.dto.Operation;
import me.zj22.gudao.server.web.pojo.dto.Operator;
import me.zj22.gudao.server.web.pojo.dto.Role;
import me.zj22.gudao.server.web.pojo.vo.Page;
import me.zj22.gudao.server.web.service.OperationService;
import me.zj22.gudao.server.web.service.RolePermissionService;
import me.zj22.gudao.server.web.service.RoleService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    @Autowired
    private HttpServletRequest request;


    /**
     * 保存角色
     * @param role
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Integer save(Role role){
//        Operator seller = (Operator) request.getSession().getAttribute("seller");
//        role.setCreateUser();
//        role.setUpdateUser();
        roleService.save(role);
        return roleService.save(role);
    }

    @RequestMapping("/findRole")
    public ModelAndView findRole(Model model,Map map) {
        List<Role> role = roleService.findRole();
        map.put("role",role);
        return new ModelAndView("operator/insert",map);
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object list(Page<Role> page){
        Page<Role> p = roleService.findAll(page);
        LOG.info("角色列表 page  :"+p);
        return p.getPageMap();
    }



}
