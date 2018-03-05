package me.zj22.gudao.server.web.pojo.dto;

import me.zj22.gudao.server.web.enums.OrderStatusEnum;
import me.zj22.gudao.server.web.enums.PayStatusEnum;
import me.zj22.gudao.server.web.utils.EnumUtil;
import me.zj22.gudao.server.web.utils.TimeParse;

import java.math.BigDecimal;
import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/6.
 */
public class OrderDTO extends  Order {

    private String orderId;

    private String receiverName;    //收件人姓名

    private String receiverPhone;    //收件人电话

    private String prov;    //省

    private String city;    //市

    private String county;    //区

    private String address;    //地址

    private String zipcode;    //邮编

    private BigDecimal orderAmount;    //总价（不含邮费）

    private BigDecimal postage;    //邮费

    private Byte orderStatus = OrderStatusEnum.NEW.getCode();    //订单状态

    private Byte payStatus = PayStatusEnum.PAY_WAIT.getCode(); //订单支付状态

    private Long createTime;    //创建时间

    private Long operationTime;    //最近一次操作时间

    private String operator;    //操作人

    private String remark;    //备注

    private Integer userId;    //用户id

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone == null ? null : receiverPhone.trim();
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov == null ? null : prov.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getPostage() {
        return postage;
    }

    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Byte getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Long operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    private List<OrderDetail> orderDetailList;

    private String viewTimeToString;

    /**获取订单状态值*/
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }

    /**获取支付状态值*/
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }


    public String getViewTimeToString() {
        //时间装换
        return TimeParse.NUIX2Time((int)(createTime/1000));
    }

    public void setViewTimeToString(String viewTimeToString) {
        this.viewTimeToString = viewTimeToString;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
