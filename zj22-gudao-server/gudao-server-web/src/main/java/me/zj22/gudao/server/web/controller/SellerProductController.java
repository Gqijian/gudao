package me.zj22.gudao.server.web.controller;

import me.zj22.gudao.server.web.exception.daoGuException;
import me.zj22.gudao.server.web.form.ProductInfoForm;
import me.zj22.gudao.server.web.pojo.dto.OrderDTO;
import me.zj22.gudao.server.web.pojo.dto.ProductInfo;
import me.zj22.gudao.server.web.pojo.dto.SellerInfo;
import me.zj22.gudao.server.web.pojo.vo.Page;
import me.zj22.gudao.server.web.service.ProductInfoService;
import me.zj22.gudao.server.web.utils.TimeParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

/**
 * 卖家端商品
 * daogu
 * Created by 袁鹏 on 2018/2/20.
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    private static final Logger LOG = LoggerFactory.getLogger(SellerOrderController.class);

    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 商品列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value="page", defaultValue = "1") Integer page,
                             @RequestParam(value="size", defaultValue = "10") Integer size,
                             Map<String, Object> map){
        Page<ProductInfo> pages = new Page();
        pages.setPage(page);
        pages.setRows(size);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(pages);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);

    }

    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") Integer productId,
                                Map<String, Object> map){
        try {
            productInfoService.onSale(productId);
        }catch (daoGuException e){
            map.put("msg", e.getMessage());
            map.put("url", "/gudao/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url", "/gudao/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    /**
     * 商品下架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") Integer productId,
                               Map<String, Object> map){
        try {
            productInfoService.offSale(productId);
        }catch (daoGuException e){
            map.put("msg", e.getMessage());
            map.put("url", "/gudao/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url", "/gudao/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    /**
     * 查询和修改商品信息
     * @param productId 非必要，可不传，即查询
     * @param map
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) Integer productId,
                                Map<String, Object> map){
        if(productId != null){
            ProductInfo productInfo = productInfoService.selectByPrimaryKey(productId);
            map.put("productInfo", productInfo);
        }

        return new ModelAndView("product/index",map);
    }

    /**
     * 更新和保存操作都在这个方法
     * @param productInfoForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductInfoForm productInfoForm,
                             BindingResult bindingResult,
                             HttpServletRequest request,
                             Map<String, Object> map){
        //后台表单验证
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/gudao/seller/product/index");
            return new ModelAndView("common/error", map);
        }
        ProductInfo productInfo = new ProductInfo();
        try {
            //productId为空则是保存
            if(productInfoForm.getProductId() == null){
                BeanUtils.copyProperties(productInfoForm, productInfo);
                productInfoService.save(productInfo);
            }else{
                productInfo = productInfoService.selectByPrimaryKey(productInfoForm.getProductId());
                BeanUtils.copyProperties(productInfoForm, productInfo);
                productInfoService.update(productInfo);
            }
        } catch (daoGuException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/gudao/seller/product/index");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/gudao/seller/product/list");
        return new ModelAndView("common/success", map);
    }
}
