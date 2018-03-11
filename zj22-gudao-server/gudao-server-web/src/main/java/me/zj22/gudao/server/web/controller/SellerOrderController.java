package me.zj22.gudao.server.web.controller;

import me.zj22.gudao.server.web.enums.ResultEnum;
import me.zj22.gudao.server.web.exception.daoGuException;
import me.zj22.gudao.server.web.pojo.dto.Order;
import me.zj22.gudao.server.web.pojo.dto.OrderDTO;
import me.zj22.gudao.server.web.pojo.dto.OrderDetail;
import me.zj22.gudao.server.web.pojo.vo.Page;
import me.zj22.gudao.server.web.service.OrderService;
import me.zj22.gudao.server.web.service.ProductInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 卖家端订单
 * daogu
 * Created by 袁鹏 on 2018/2/19.
 */
@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {
    private static final Logger LOG = LoggerFactory.getLogger(SellerOrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 查询新订单
     * @param page
     * @param map
     * @return
     */
    @RequestMapping("/nlist")
    @ResponseBody
    public Object nlist(Page<OrderDTO> page,
                             Map<String, Object> map){
        Page<OrderDTO> p = orderService.findAllNList(page);
        return p.getPageMap();

    }
    @RequestMapping("/flist")
    @ResponseBody
    public Object flist(Page<OrderDTO> page,
                       Map<String, Object> map){
        Page<OrderDTO> p = orderService.findAllFList(page);
        return p.getPageMap();

    }
    @RequestMapping("/clist")
    @ResponseBody
    public Object clist(Page<OrderDTO> page,
                       Map<String, Object> map){
        Page<OrderDTO> p = orderService.findAllCList(page);
        return p.getPageMap();

    }
    /**
     * 取消订单
     * @param
     * @param map
     * @return
     */
    //@RequestParam("orderId") String orderId,
    @RequestMapping("/cancel")
    @ResponseBody
    public  int cancel(String pks[],
                                Map<String, Object> map){
        String orderId = null;
        try {
            for(int i=0; i<pks.length; i++){
                orderId = pks[i];
                OrderDTO orderDTO = orderService.findOne(orderId);
                orderService.cancel(orderDTO);
            }
        }catch (daoGuException e){
            LOG.error(" [卖家取消订单] 发生异常");
//            map.put("msg", e.getMessage());
//            map.put("url", "/gudao/seller/order/list");
            return 0;
        }
        return pks.length;
//        map.put("msg", ResultEnum.ORDER_CALCLE_SUCCESS.getMessage());
//        map.put("url", "/gudao/seller/order/list");
//        return new ModelAndView("common/success");
    }

    /**
     * 订单详情
     * @param orderId
     * @param map
     * @return
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Object detail(@RequestParam("orderId") String orderId,
                         Map<String, Object> map){
        Page page = new Page();
        page.setKeyWord(orderId);
        Page<OrderDetail> p = orderService.findAllDetailByOrderId(page);
        return p.getPageMap();
//        OrderDTO orderDTO = new OrderDTO();
//        try {
//            orderDTO = orderService.findOne(orderId);
//        }catch (daoGuException e){
//            LOG.error(" [订详情单] 发生异常");
//            map.put("msg", e.getMessage());
//            map.put("url", "/gudao/seller/order/list");
//            return new ModelAndView("common/error", map);
//        }
//        map.put("orderDTO", orderDTO);
//        return new ModelAndView("order/detail", map);
    }

    @RequestMapping("/ndetail")
    public Object ndetail(@RequestParam("orderId") String orderId){

        return null;

    }

    @RequestMapping("/finish")
    @ResponseBody
    public int finish(String pks[],
                               Map<String, Object> map){
        String orderId = null;
        try {
            for(int i=0; i<pks.length; i++){
                orderId = pks[i];
                OrderDTO orderDTO = orderService.findOne(orderId);
                orderService.finish(orderDTO);
            }
        }catch (daoGuException e){
            LOG.error(" [订单完结] 发生异常");
//            map.put("msg", e.getMessage());
//            map.put("url", "/gudao/seller/order/list");
            return 0;
        }
//        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
//        map.put("url", "/gudao/seller/order/list");
        return pks.length;
    }

}
