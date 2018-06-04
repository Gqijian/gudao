package me.zj22.gudao.server.web.controller;

import me.zj22.gudao.server.web.pojo.dto.User;
import me.zj22.gudao.server.web.pojo.vo.Page;
import me.zj22.gudao.server.web.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 后台用户管理
 * daogu
 * Created by 袁鹏 on 2018/2/23.
 */
@Controller
@RequestMapping("/seller/user")
public class SellerWechatUserController {


    @Autowired
    private UserSerivce userSerivce;

    /**
     * 用户查询
     * @param page
     * @param user
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(Page<User> page, User user, @RequestParam(defaultValue = "0") String flag){

        if(flag.equals("0")){
            page.setParamEntity(user);
            Page<User> p = userSerivce.findUserList(page);
            return p.getPageMap();
        }
        //按照积分降序
        page.setParamEntity(user);
        Page<User> p = userSerivce.findUserListOrder(page);
        return p.getPageMap();

    }
//    @RequestMapping("/list")
//    @ResponseBody
//    public ModelAndView list(@RequestParam(value="page", defaultValue = "1") Integer page,
//                             @RequestParam(value="size", defaultValue = "10") Integer size,
//                             @RequestParam(value = "nickname" , required = false, defaultValue = "")
//                                     String nickname,
//                             Map<String, Object> map){
//
//        Page<User> pages = new Page();
//        pages.setPage(page);
//        pages.setRows(size);
//
//        if(nickname != null && nickname != ""){
//            //异步查询条件，搜索指定昵称的用户
//            pages.setKeyWord(nickname);
//            Page<User> wechatUserPage = userSerivce.findUserByName(pages);
//            map.put("wechatUserPage",wechatUserPage);
//            map.put("currentPage", page);
//            map.put("size", size);
//            return new ModelAndView("user/list", map);
//        }
//        Page<User> wechatUserPage = userSerivce.findAllList(pages);
//        map.put("wechatUserPage",wechatUserPage);
//        map.put("currentPage", page);
//        map.put("size", size);
//        return new ModelAndView("user/list", map);
//
//    }
}
