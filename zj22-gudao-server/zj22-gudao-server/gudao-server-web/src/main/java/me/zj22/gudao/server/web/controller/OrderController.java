package me.zj22.gudao.server.web.controller;

import me.zj22.gudao.server.web.enums.ResultEnum;
import me.zj22.gudao.server.web.exception.daoGuException;
import me.zj22.gudao.server.web.form.OrderForm;
import me.zj22.gudao.server.web.pojo.dto.OrderDTO;
import me.zj22.gudao.server.web.pojo.dto.OrderDetail;
import me.zj22.gudao.server.web.pojo.dto.User;
import me.zj22.gudao.server.web.pojo.vo.CartProduct;
import me.zj22.gudao.server.web.pojo.vo.JsonResponse;
import me.zj22.gudao.server.web.pojo.vo.Page;
import me.zj22.gudao.server.web.pojo.vo.SubscribeUserInfo;
import me.zj22.gudao.server.web.service.*;
import me.zj22.gudao.server.web.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单
 * @Description:change
 * @Author Gqjian
 * @Create 2018/3/01 21:35
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private CartsService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private Order2Service order2Service;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private UserSerivce userSerivce;

    @Autowired
    private HttpSession session;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private HttpServletRequest request;

    //创建订单 OrderForm封装订单提交的字段
    @PostMapping("/creates")
    @ResponseBody
    public JsonResponse<Map<String, String>> create(
            @Valid OrderForm orderForm,
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
        map.put("orderId", String.valueOf(creatResult.getOrderId()));//订单返回的是订单号、
        json.setData(map);
        return json;
    }

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    @RequestMapping(value = "/createOrder",method = RequestMethod.POST)
    @ResponseBody
    public String createOrder(@RequestBody OrderDTO orderDTO){
        try{
            //orderDTO = orderService.findOne("98765432");
            SubscribeUserInfo userInfo = (SubscribeUserInfo)session.getAttribute("userInfo");
            //String openid = CookieUtils.getCookieValue(request, "userInfo", true);
            String openid = userInfo.getOpenid();

            User user = userSerivce.selectByOpenId(openid);
            orderDTO.setUserId(user.getUserId());
            OrderDTO order = order2Service.createOrder(orderDTO);
            /*String json = JsonUtils.objectToJson(order);
            String result = "callback(" ;*/
            return JsonUtils.objectToJson(order);
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    @RequestMapping(value = "/KdMessage",method = RequestMethod.POST)
    @ResponseBody
    public String KdMessage(@RequestBody OrderDTO orderDTO){

        String num = order2Service.KdMessage(orderDTO);
        KdUtils kdUtils = new KdUtils();
        Double kdPrice = kdUtils.Kd(orderDTO.getCity(), Integer.valueOf(num));
        OrderDTO order = orderService.findOne(orderDTO.getOrderId());
        orderDTO.setPostage(new BigDecimal(kdPrice));
        orderDTO.setOrderAmount(order.getOrderAmount().add(new BigDecimal(kdPrice)));
        order2Service.updateOrder(orderDTO);

        return JsonUtils.objectToJson(orderDTO);
    }

    @RequestMapping(value = "/updateOrder",method = RequestMethod.POST)
    @ResponseBody
    public String updateOrder(@RequestBody OrderDTO orderDTO){
        try{
            String result = order2Service.updateOrder(orderDTO);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 订单补充
     * @param _
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/orderDetail",method = RequestMethod.GET)
    @ResponseBody
    public String orderDetail(@RequestParam String orderId, String callback, String _){
        try{
            //OrderDTO order = new OrderDTO();
            //order = orderService.findOne("98765432");
            OrderDTO order = orderService.findOne(orderId);
            String json = JsonUtils.objectToJson(order);
            String result = "callback(" ;
            return result+json+")";
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    @RequestMapping(value = "/myOrders")
    @ResponseBody
    public String myOrders(HttpSession session, @RequestParam(defaultValue = "2")Integer orderStatus){
        SubscribeUserInfo userInfo = (SubscribeUserInfo)session.getAttribute("userInfo");
        String openid = userInfo.getOpenid();
        User user = userSerivce.selectByOpenId(openid);
        HashMap map = new HashMap();
        map.put("userId",user.getUserId());
        map.put("orderStatus",orderStatus);
        List<OrderDTO> allList = order2Service.findUserOrdersByStatus(map);
        List<OrderDTO> results = new ArrayList<>();
        for (OrderDTO orderDTO:allList) {
            List<OrderDetail> detailList = orderDetailService.findByOrderId(orderDTO.getOrderId());
            orderDTO.setOrderDetailList(detailList);
            results.add(orderDTO);
        }
        System.out.println(JsonUtils.objectToJson(results));
        String result = "callback(" ;
        return result+JsonUtils.objectToJson(results)+")";
    }

    public String showOrderCart(HttpServletRequest request, HttpServletResponse response){
        List<CartProduct> cartProductList = cartService.getCartProductList(request, response);
        return "";
    }

    /*
    //订单列表
    @GetMapping("/orderLists")
    @ResponseBody
    public JsonResponse<List<OrderDTO>> list(Page<OrderDTO> page, Integer userId){
        Page<OrderDTO> orderPage = orderService.findAllListOrder(page, userId);
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
*/
    //订单详情
    @GetMapping("/detail")
    @ResponseBody
    public JsonResponse<OrderDTO> detail(
            @RequestParam("openId") String openId,
            @RequestParam("orderId") String orderId){
        OrderDTO orderDTO = buyerService.findOrderOne(openId, orderId);
        JsonResponse<OrderDTO> json = new JsonResponse<>();
        json.setData(orderDTO);
        return json;

    }

    //取消订单
    @PostMapping("/cancel")
    @ResponseBody
    public JsonResponse cancel(
            @RequestParam("openId") String openId,
            @RequestParam("orderId") String orderId){
        buyerService.cancelOrder(openId, orderId);
        JsonResponse json = new JsonResponse();
        return json;
    }
}
