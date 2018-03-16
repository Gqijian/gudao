package me.zj22.gudao.server.web.controller;

import me.zj22.gudao.server.web.pojo.vo.JsonResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/3/13.
 */
@RequestMapping("/test")
@RestController
public class TestTreeController {

//    @RequestMapping("tree")
//    public Object tree(){
//        List<Children> pareList = new ArrayList<>();
//        List<Children> userList = new ArrayList<>();
//        Children user = new Children();
//        user.setId(1);
//        user.setText("a");
//        user.setUserName("测试a");
//
//        Children user2 = new Children();
//        user2.setId(11);
//        user2.setText("b");
//        user2.setUrl("http://www.baidu.com");
//        user2.setUserName("测试b");
//
//
//
//        Children user3 = new Children();
//        user3.setId(12);
//        user3.setText("c");
//        user3.setUrl("/gudao/base/goURL/order/orderNList.action");
//        user3.setUserName("测试c");
//
//        userList.add(user2);
//        userList.add(user3);
//
//
//        user.setChildren(userList);
//        Children user4 = new Children();
//        user4.setId(2);
//        user4.setUrl("/gudao/base/goURL/product/productList.action");
//        user4.setText("d");
//        user4.setUserName("测试d");
//        pareList.add(user4);
//        pareList.add(user);
//        return pareList;
//    }
}

//class Children{
//    private Integer id;
//    private String userName;
//    private String url;
//    private String text;
//    List<Children> children;
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public List<Children> getChildren() {
//        return children;
//    }
//
//    public void setChildren(List<Children> children) {
//        this.children = children;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//}
