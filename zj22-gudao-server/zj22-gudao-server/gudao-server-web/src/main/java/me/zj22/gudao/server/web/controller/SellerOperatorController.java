package me.zj22.gudao.server.web.controller;

import me.zj22.gudao.server.web.pojo.dto.Operation;
import me.zj22.gudao.server.web.pojo.dto.Operator;
import me.zj22.gudao.server.web.pojo.vo.Page;
import me.zj22.gudao.server.web.service.OperationService;
import me.zj22.gudao.server.web.service.OperatorService;
import me.zj22.gudao.server.web.service.RolePermissionService;
import me.zj22.gudao.server.web.utils.MD5Util;
import me.zj22.gudao.server.web.utils.TreeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/3/17.
 */
@Controller
@RequestMapping("/seller/operator")
public class SellerOperatorController {

    private static final Logger LOG = LoggerFactory.getLogger(SellerOperatorController.class);

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private OperationService operationService;

    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * 操作人员列表
     * @param page
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(Page<Operator> page){
        Page<Operator> p = operatorService.findAll(page);
        LOG.info("操作人员列表 page  :"+p);
        return p.getPageMap();
    }

//    /**
//     * 操作者权限
//     * @return
//     */
//    @RequestMapping("/detail")
//    @ResponseBody
//    public Object detail(@RequestParam("roleId") Integer  roleId){
//        Page page = new Page<>();
//        page.setKeyWord(String.valueOf(roleId));
//        List<Operation> operationList = operationService.findOperationByRoleId(roleId);
//        Integer operationCount = operationService.findOperationCount(roleId);
//        page.setList(operationList);
//        page.setTotalRecord(operationCount);
//        return page.getPageMap();
//    }

    /**
     * 删除管理员
     * @param pks
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Integer delete(String[] pks){
        int i = 0;
        try {
//            for(int j=0; j<pks.length; j++){
//                //根据pks（operatorId）查找管理者的role_id
//                Operator operator = operatorService.selectByPrimaryKey(Integer.valueOf(pks[j]));
//                //删除依赖表role_permission中的role_id
//                Integer roleId = operator.getRoleId();
//                rolePermissionService.deleteByRoleId(roleId);
//                删除后其他用户依赖的role_id就会失效，不能删除
//            }
            i = operatorService.delete(pks);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }


    /**
     * 冻结用户账户
     * @param pks
     * @return
     */
    @RequestMapping("/freeze")
    @ResponseBody
    public Integer freeze(String[] pks){
        try {
            for(int j=0; j<pks.length; j++){
                Operator operator = operatorService.selectByPrimaryKey(Integer.valueOf(pks[j]));
                operator.setAvailable(0);//冻结
                operatorService.freeze(operator);
            }
            return 1;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 解冻账户
     * @param pks
     * @return
     */
    @RequestMapping("/nofreeze")
    @ResponseBody
    public Integer nofreeze(String[] pks){
        try {
            for(int j=0; j<pks.length; j++){
                Operator operator = operatorService.selectByPrimaryKey(Integer.valueOf(pks[j]));
                operator.setAvailable(1);//解冻
                operatorService.nofreeze(operator);
            }
            return 1;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 查看权限
     * @param roleId
     * @return
     */
    @RequestMapping("/findPower")
    @ResponseBody
    public Object findPower(Integer roleId){
        List<Operation> operationList = operationService.findOperationByRoleId(roleId);
        return TreeUtil.forTree(operationList);
    }

    /**
     * 添加管理员
     * @param operator
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Integer add(Operator operator){
        try {
            operator.setPassword(MD5Util.md5("666666"));//初始化密码
            return operatorService.insert(operator);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 未有权限查询
     * @param roleId
     * @return
     */
    @RequestMapping("/addPower")
    @ResponseBody
    public Object addPower(Integer roleId){
        List<Operation> operationList = operationService.findNoOperationByRoleId(roleId);
        return TreeUtil.forTree(operationList);
    }

    /**
     *
     * @param newPassword
     * @return
     */
    @RequestMapping("/modifyPassword")
    @ResponseBody
    public Integer modifyPassword(String newPassword){
        Integer modifyCount = operatorService.modifyPassword(newPassword);
        if(modifyCount!=1){
            return 0;
        }
        return modifyCount;
    }
}
