package me.zj22.gudao.server.web.pojo.dto;

import me.zj22.gudao.server.web.utils.TimeParse;

/**
 * @Program:zj22-gudao-server
 * @Description:买家用户表
 * @Author Gqjian
 * @Create 2018/2/5 15:38:07
 */

public class User {
    private Integer userId;

    private String openid;  //

    private String nickname;    //

    private Integer sex;    //

    private String country; //

    private String province;    //

    private String city;    //

    private String headImgUrl;  //

    private Integer integral;   //

    private Integer total;  //

    private Long createTime;    //

    public String getCreateTimeToString() {
        //时间装换
        return TimeParse.NUIX2Time((int)(createTime/1000));
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl == null ? null : headImgUrl.trim();
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}