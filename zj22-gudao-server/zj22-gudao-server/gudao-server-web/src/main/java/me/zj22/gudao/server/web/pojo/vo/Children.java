package me.zj22.gudao.server.web.pojo.vo;

import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/3/16.
 * 封装返回easyui所支持的格式
 */
public class Children {
    private Integer id;
    private String url;
    private String text;
    List<Children> children;

    public Children(Integer id,  String url, String text) {
        this.id = id;
        this.url = url;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Children> getChildren() {
        return children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }
}
