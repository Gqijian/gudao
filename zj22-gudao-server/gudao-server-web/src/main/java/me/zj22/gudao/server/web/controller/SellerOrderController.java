package me.zj22.gudao.server.web.controller;

import me.zj22.gudao.server.web.enums.ResultEnum;
import me.zj22.gudao.server.web.exception.daoGuException;
import me.zj22.gudao.server.web.pojo.dto.OrderDTO;
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
     * 查询积分列表
     * @param page 当前页数
     * @param size 分页大小
     * @param map 集合
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value="page", defaultValue = "1") Integer page,
                             @RequestParam(value="size", defaultValue = "10") Integer size,
                             Map<String, Object> map){
        Page<OrderDTO> pages = new Page();
        pages.setPage(page);
        pages.setRows(size);
        //pages.setStart((page-1)*size);
        Page<OrderDTO> orderDTOPage = orderService.findAllList(pages);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("order/list", map);

    }

    /**
     * 取消订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("cancel")
    public  ModelAndView cancel(@RequestParam("orderId") String orderId,
                                Map<String, Object> map){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (daoGuException e){
            LOG.error(" [卖家取消订单] 发生异常");
            map.put("msg", e.getMessage());
            map.put("url", "/gudao/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.ORDER_CALCLE_SUCCESS.getMessage());
        map.put("url", "/gudao/seller/order/list");
        return new ModelAndView("common/success");
    }

    /**
     * 订单详情
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
        }catch (daoGuException e){
            LOG.error(" [订详情单] 发生异常");
            map.put("msg", e.getMessage());
            map.put("url", "/gudao/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("orderDTO", orderDTO);
        return new ModelAndView("order/detail", map);
    }

    @GetMapping("finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String, Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);

        }catch (daoGuException e){
            LOG.error(" [订单完结] 发生异常");
            map.put("msg", e.getMessage());
            map.put("url", "/gudao/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url", "/gudao/seller/order/list");
        return new ModelAndView("common/success");
    }
}
