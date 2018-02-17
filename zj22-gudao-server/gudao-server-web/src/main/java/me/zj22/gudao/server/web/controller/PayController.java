package me.zj22.gudao.server.web.controller;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import me.zj22.gudao.server.web.enums.ResultEnum;
import me.zj22.gudao.server.web.exception.daoGuException;
import me.zj22.gudao.server.web.pojo.vo.OrderDTO;
import me.zj22.gudao.server.web.service.OrderService;
import me.zj22.gudao.server.web.service.PayService;
import me.zj22.gudao.server.web.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/9.
 */
@Controller
@RequestMapping("/pay")
public class PayController {
    private static final Logger LOG = LoggerFactory.getLogger(PayController.class);


    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    /**
     * 创建订单
     * @param orderId
     * @param returnUrl
     * @param map
     * @return
     */
    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") Integer orderId,
                       @RequestParam("returnUrl") String returnUrl,
                       Map<String, Object> map){
        //查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null){
            throw new daoGuException(ResultEnum.ORDER_NOT_EXIT);
        }
        //2. 发起支付
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);

        return new ModelAndView("pay/create", map);
    }

    /**微信异步通知
     *
     * @param notifyData
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){
        payService.notify(notifyData);
        //返回给微信处理结果
        return new ModelAndView("pay/success");
    }

    @RequestMapping("/test")
    public ModelAndView testJsp(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("test","测试成功");
        modelAndView.setViewName("pay/test");
        LOG.info(" [测试] modelAndView", JsonUtils.objectToJson(modelAndView));
        return  modelAndView;
    }

    @RequestMapping("/test2")
    public String test2(){
        LOG.info(" [测试] create===========================");
        return  "pay/test2";
    }
}
