package me.zj22.gudao.server.web.pojo.dto;

/**
 * @Program:zj22-gudao-server
 * @Description:商品表
 * @Author Gqjian
 * @Create 2018/2/5 15:38:07
 */

public class ProductInfo {
    private Integer productId;

    private String productName; //商品名称

    private String productIconOne;  //图片

    private String productIconTwo;     //图片

    private String productIconThree;       //图片

    private Integer productPrice;   //单价，单位分

    private Integer cost;   //成本价，单位分

    private Integer weight; //重量，单位克

    private Byte productStatus; //商品状态

    private String productAbout;    //商品简介

    private Integer productStock;   //库存

    private Long createTime;    //创建时间

    private String creatUser;   //创建人

    private Long updateTime;    //修改时间

    private String updateUser;  //修改人

    private String productDescription;  //商品详情

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductIconOne() {
        return productIconOne;
    }

    public void setProductIconOne(String productIconOne) {
        this.productIconOne = productIconOne == null ? null : productIconOne.trim();
    }

    public String getProductIconTwo() {
        return productIconTwo;
    }

    public void setProductIconTwo(String productIconTwo) {
        this.productIconTwo = productIconTwo == null ? null : productIconTwo.trim();
    }

    public String getProductIconThree() {
        return productIconThree;
    }

    public void setProductIconThree(String productIconThree) {
        this.productIconThree = productIconThree == null ? null : productIconThree.trim();
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Byte getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Byte productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductAbout() {
        return productAbout;
    }

    public void setProductAbout(String productAbout) {
        this.productAbout = productAbout == null ? null : productAbout.trim();
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getCreatUser() {
        return creatUser;
    }

    public void setCreatUser(String creatUser) {
        this.creatUser = creatUser == null ? null : creatUser.trim();
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription == null ? null : productDescription.trim();
    }
}