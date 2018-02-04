package me.zj22.gudao.server.web.pojo.dto;

public class Operation {
    private Integer opId;

    private String opName;

    private String opHref;

    private String opCode;

    private String opSeq;

    private String description;

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