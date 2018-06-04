package me.zj22.gudao.server.web.controller;

import me.zj22.gudao.server.web.pojo.dto.Operation;
import me.zj22.gudao.server.web.pojo.dto.Operator;
import me.zj22.gudao.server.web.pojo.dto.RolePermission;
import me.zj22.gudao.server.web.pojo.vo.Page;
import me.zj22.gudao.server.web.service.OperationService;
import me.zj22.gudao.server.web.service.OperatorService;
import me.zj22.gudao.server.web.service.RolePermissionService;
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
@RequestMapping("/seller/operation")
public class SellerOperationController {

    private static final Logger LOG = LoggerFactory.getLogger(SellerOperationController.class);


    @Autowired
    private OperationService operationService;

    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * 操作者权限
     * @return
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Object detail(@RequestParam("roleId") Integer  roleId){
        Page page = new Page<>();
        page.setKeyWord(String.valueOf(roleId));
        List<Operation> operationList = operationService.findOperationByRoleId(roleId);
        Integer operationCount = operationService.findOperationCount(roleId);
        page.setList(operationList);
        page.setTotalRecord(operationCount);
        return page.getPageMap();
    }

    /**
     * 取消用户选择的权限
     * @param pks
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Integer detail(String[] pks, Integer roleId){
        Map<String, Object> map = new HashMap<>();
        map.put("pks", pks);
        map.put("roleId", roleId);
        int i = 0;
       try {
           i = rolePermissionService.delete(map);
       }catch (Exception e) {
           e.printStackTrace();
       }
        return i;
    }

    @RequestMapping("/addRolePermission")
    @ResponseBody
    public Integer addRolePermission(String[] pks, Integer roleId){
        try {
            //向role_permission表中插入role_id=roleId op_id in (pks)
            for(int i=0; i<pks.length; i++){
                RolePermission rolePermission = new RolePermission();
                rolePermission.setOpId(Integer.valueOf(pks[i]));
                rolePermission.setRoleId(roleId);
                rolePermissionService.insert(rolePermission);
            }
            return 1;
        }catch (Exception e) {
            e.printStackTrace();
        }
      return 0;
    }
}
