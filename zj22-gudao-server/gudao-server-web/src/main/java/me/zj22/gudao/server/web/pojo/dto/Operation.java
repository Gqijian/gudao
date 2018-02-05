package me.zj22.gudao.server.web.pojo.dto;

/**
 * @Program:zj22-gudao-server
 * @Description:权限表
 * @Author Gqjian
 * @Create 2018/2/5 15:38:07
 */

public class Operation {
    private Integer opId;

    private String opName;  //权限名

    private String opHref;  //访问url地址

    private String opCode;  //操作权限标识码

    private String opSeq;   //序列生成默认增长顺序

    private String description; //描述

    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer opId) {
        this.opId = opId;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName == null ? null : opName.trim();
    }

    public String getOpHref() {
        return opHref;
    }

    public void setOpHref(String opHref) {
        this.opHref = opHref == null ? null : opHref.trim();
    }

    public String getOpCode() {
        return opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode == null ? null : opCode.trim();
    }

    public String getOpSeq() {
        return opSeq;
    }

    public void setOpSeq(String opSeq) {
        this.opSeq = opSeq == null ? null : opSeq.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}