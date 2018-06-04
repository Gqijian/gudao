package me.zj22.gudao.server.web.utils;

import me.zj22.gudao.server.web.pojo.dto.Operation;
import me.zj22.gudao.server.web.pojo.vo.Children;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限遍历树
 * daogu
 * Created by 袁鹏 on 2018/3/18.
 */
public class TreeUtil {

    public static List forTree(List<Operation> operationList){
        //遍历所有权限，封装在children中，父子关系注意
        List<Children> parentList = new ArrayList<>(); //父集合 menu
        List<Children> childrenList = null;//子集合 permission

        for (Operation op : operationList) {
            //判断是否是menu类型
            if(op.getOpType().equalsIgnoreCase("menu")){
                Children parent = new Children(op.getOpId(),op.getOpHref(),op.getOpName());
                parentList.add(parent);
            }
        }

        //设置父子层级
        for(int i=0; i<parentList.size(); i++){
            childrenList = new ArrayList<>();
            for (Operation op : operationList) {
                if( "permission".equalsIgnoreCase(op.getOpType()) && parentList.get(i).getId() == op.getOpPid()){
                    Children children = new Children(op.getOpId(),op.getOpHref(),op.getOpName());
                    childrenList.add(children);
                }
                parentList.get(i).setChildren(childrenList);

            }
        }
        return parentList;
    }

}
