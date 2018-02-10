package me.zj22.gudao.server.web.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.BestPayService;
import me.zj22.gudao.server.web.enums.ResultEnum;
import me.zj22.gudao.server.web.exception.daoGuException;
import me.zj22.gudao.server.web.pojo.dto.OrderDTO;
import me.zj22.gudao.server.web.pojo.dto.User;
import me.zj22.gudao.server.web.service.OrderService;
import me.zj22.gudao.server.web.service.PayService;
import me.zj22.gudao.server.web.service.UserSerivce;
import me.zj22.gudao.server.web.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单支付
 * daogu
 * Created by 袁鹏 on 2018/2/9.
 */
@Service
public class PayServiceImpl implements PayService {

    private static final Logger LOG = LoggerFactory.getLogger(PayServiceImpl.class);

    private static final String ORDER_NAME= "叨咕酱";

    @Autowired
    private BestPayService bestPayService;

    @Autowired
    private UserSerivce userSerivce;

    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        Integer userId = orderDTO.getUserId();
        //根据userId查询用户openId,其实只用查找openId即可
        User user = userSerivce.selectByPrimaryKey(userId);
        payRequest.setOpenid(user.getOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId().toString());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        LOG.info(" [微信支付] 发起支付， payRequest = {}", JsonUtils.objectToJson(payRequest));
        //支付
        PayResponse payResponse = bestPayService.pay(payRequest);
        LOG.info(" [微信支付] 发起支付， payResponse = {}",JsonUtils.objectToJson(payResponse));
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        // 1.验证签名 2.验证支付状态
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        LOG.info(" [微信支付] 异步通知， payResponse = {}",JsonUtils.objectToJson(payResponse));
        String orderId = payResponse.getOrderId();
        //查询订单
        OrderDTO orderDTO = orderService.findOne(Integer.valueOf(orderId));
        if(orderDTO == null){
            LOG.error("[微信支付] 异步通知，订单不存在， orderId = {}", orderId);
            throw new daoGuException(ResultEnum.ORDER_NOT_EXIT);
        }
        //3.支付金额验证
        Integer orderAmount = orderDTO.getOrderAmount();
        if(payResponse.getOrderAmount() != orderAmount.doubleValue()){
            LOG.error("[微信支付] 异步通知，订单金额错误， orderId = {}， 微信通知金额为={}， 系统金额为={}",
                    orderId,
                    payResponse.getOrderAmount(),
                    orderAmount);
            throw new daoGuException(ResultEnum.WXPAY_NOTIFY_MONEY_ERROR);
        }


        //订单支付
        orderService.paid(orderDTO);
        return payResponse;
    }

    /**
     * 微信退款
     * @param orderDTO
     * @return
     */
    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(String.valueOf(orderDTO.getOrderId()));
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        LOG.info(" [微信退款] refundRequest = {}", JsonUtils.objectToJson(refundRequest));
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        LOG.info(" [微信退款] refundResponse = {}", JsonUtils.objectToJson(refundResponse));

        return refundResponse;
    }
}
