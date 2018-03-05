package me.zj22.gudao.server.web.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.BestPayService;
import me.zj22.gudao.server.web.dao.db.UserMapper;
import me.zj22.gudao.server.web.dao.db.wrap.PointsWrapperMapper;
import me.zj22.gudao.server.web.enums.ResultEnum;
import me.zj22.gudao.server.web.exception.daoGuException;
import me.zj22.gudao.server.web.pojo.dto.OrderDTO;
import me.zj22.gudao.server.web.pojo.dto.Points;
import me.zj22.gudao.server.web.pojo.dto.User;
import me.zj22.gudao.server.web.service.OrderService;
import me.zj22.gudao.server.web.service.PayService;
import me.zj22.gudao.server.web.utils.JsonUtils;
import me.zj22.gudao.server.web.utils.MathUtil;
import me.zj22.gudao.server.web.utils.TimeParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 订单支付
 * daogu
 * Created by 袁鹏 on 2018/2/9.
 */
@Service
public class PayServiceImp implements PayService {

    private static final Logger LOG = LoggerFactory.getLogger(PayServiceImp.class);

    private static final String ORDER_NAME= "叨咕酱";

    @Autowired
    private BestPayService bestPayService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PointsWrapperMapper pointsWrapperMapper;


    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        Integer userId = orderDTO.getUserId();
        //根据userId查询用户openId,其实只用查找openId即可
        User user = userMapper.selectByPrimaryKey(userId);
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
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null){
            LOG.error("[微信支付] 异步通知，订单不存在， orderId = {}", orderId);
            throw new daoGuException(ResultEnum.ORDER_NOT_EXIT);
        }
        //3.支付金额验证
        double orderAmount = orderDTO.getOrderAmount().doubleValue();
        if (!MathUtil.equals(payResponse.getOrderAmount(), orderAmount)) {
            LOG.error("[微信支付] 异步通知，订单金额错误， orderId = {}， 微信通知金额为={}， 系统金额为={}",
                    orderId,
                    payResponse.getOrderAmount(),
                    orderAmount);
            throw new daoGuException(ResultEnum.WXPAY_NOTIFY_MONEY_ERROR);
        }


        //4.订单支付
        orderService.paid(orderDTO);

        // 将积分orderAmount存入积分表
        Points points = new Points();
        Date date = new Date();
        long nuixTime = TimeParse.Time2NUIX(TimeParse.NUIX2Time(date));
        // 时间这里为long，这里错误，为了不报错，先这样写.
        Integer integral = orderDTO.getOrderAmount().intValue();
        points.setCreateTime((int)(nuixTime/1000));
        points.setFlag((byte)1);//表示可用积分
        points.setPointsRecord(integral);
        points.setOrderId(orderDTO.getOrderId());
        points.setUserId(orderDTO.getUserId());
        pointsWrapperMapper.insert(points);

        //5.查询用户，将本次积分加到用户的总积分上
        User user = userMapper.selectByPrimaryKey(orderDTO.getUserId());
        user.setIntegral(integral+user.getIntegral());
        user.setTotal(integral+user.getIntegral());
        //更新积分
        userMapper.updateByPrimaryKey(user);
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

        //1.积分表中的积分标识为不可用
        Points points = pointsWrapperMapper.findByOrderId(orderDTO.getOrderId());
        points.setFlag((byte)0);//标记积分为不可用
        pointsWrapperMapper.updateByPrimaryKey(points);

        //2.扣除用户积分
        Integer integral = Integer.valueOf(orderDTO.getOrderAmount().toString());
        User user = userMapper.selectByPrimaryKey(orderDTO.getUserId());
        user.setIntegral(integral-user.getIntegral());
        user.setTotal(integral-user.getIntegral());
        //3.更新用户积分
        userMapper.updateByPrimaryKey(user);
        return refundResponse;
    }
}
