package me.zj22.gudao.server.web.controller;

import me.zj22.gudao.server.web.enums.ResultEnum;
import me.zj22.gudao.server.web.exception.daoGuException;
import me.zj22.gudao.server.web.form.OrderForm;
import me.zj22.gudao.server.web.pojo.vo.OrderDTO;
import me.zj22.gudao.server.web.pojo.vo.JsonResponse;
import me.zj22.gudao.server.web.pojo.vo.Page;
import me.zj22.gudao.server.web.service.BuyerService;
import me.zj22.gudao.server.web.service.OrderService;
import me.zj22.gudao.server.web.utils.OrderForm2OrderDTO;
import me.zj22.gudao.server.web.utils.TimeParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/6.
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单 OrderForm封装订单提交的字段
    @PostMapping("/create")
    @ResponseBody
    public JsonResponse<Map<String, String>> create(@Valid OrderForm orderForm,
                                                    BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            LOG.error(" [创建订单] 参数不正确， orderForm = {}",orderForm);
            throw new daoGuException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            LOG.error(" [创建订单] 购物车不能为空");
            throw new daoGuException(ResultEnum.PRODUCT_NOT_EXIT);
        }
        OrderDTO creatResult = orderService.create(orderDTO);
        JsonResponse<Map<String, String>> json = new JsonResponse<>();
        Map<String, String> map = new HashMap<>();
        map.put("orderNum", String.valueOf(creatResult.getOrderNum()));//订单返回的是订单号、
        json.setData(map);
        return json;
    }

    //订单列表
    @GetMapping("/list")
    @ResponseBody
    public JsonResponse<List<OrderDTO>> list(Page<OrderDTO> page, String openId){
        Page<OrderDTO> orderPage = orderService.findAllListOrder(page, openId);
        JsonResponse<List<OrderDTO>> json = new JsonResponse<>();
        List<OrderDTO> orderPageList = orderPage.getList();
        //日期转换
        for(OrderDTO orderDTO : orderPageList){
            String createTime = TimeParse.NUIX2Time((int) (orderDTO.getCreateTime() / 1000));
            orderDTO.setViewTimeToString(createTime);
        }
        json.setData(orderPageList);
        return json;
    }

    //订单详情
    @GetMapping("/detail")
    @ResponseBody
    public JsonResponse<OrderDTO> detail(
            @RequestParam("openId") String openId,
            @RequestParam("orderId") Long orderNum){
        OrderDTO orderDTO = buyerService.findOrderOne(openId, orderNum);
        JsonResponse<OrderDTO> json = new JsonResponse<>();
        json.setData(orderDTO);
        return json;

    }

    //取消订单
    @PostMapping("/cancel")
    @ResponseBody
    public JsonResponse cancel(
            @RequestParam("openId") String openId,
            @RequestParam("orderNum") Long orderNum){
        buyerService.cancelOrder(openId, orderNum);
        JsonResponse json = new JsonResponse();
        return json;
    }
}
