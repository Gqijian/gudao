package me.zj22.gudao.server.web.pojo.dto;

import me.zj22.gudao.server.web.enums.PayStatusEnum;
import me.zj22.gudao.server.web.utils.EnumUtil;
import me.zj22.gudao.server.web.utils.TimeParse;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @Author Gqjian
 * @Create 2018/3/5 14:58
 */
public class OrderDTO extends Order {

    private Long createTime;    //创建时间

    private BigDecimal postage;    //邮费

    private Byte payStatus = PayStatusEnum.PAY_WAIT.getCode(); //订单支付状态

    private List<OrderDetail> orderDetailList;

    //private String viewTimeToString;

    public OrderDTO(){

    }

    public BigDecimal getPostage() {
        return postage;
    }

    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }


    public Byte getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }

    @Override
    public Long getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }


    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

}
