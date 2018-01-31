package me.zj22.gudao.server.web.dao.model;

public class ProductInfo {
    private Integer productId;

    private String productName;

    private String productIconOne;

    private String productIconTwo;

    private String productIconThree;

    private Integer productPrice;

    private Integer cost;

    private Integer weight;

    private Byte productStatus;

    private String productAbout;

    private Integer productStock;

    private Long createTime;

    private String creatUser;

    private Long updateTime;

    private String updateUser;

    private String productDescription;

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