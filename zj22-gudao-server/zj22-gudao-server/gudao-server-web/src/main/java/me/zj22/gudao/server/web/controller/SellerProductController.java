package me.zj22.gudao.server.web.controller;

import me.zj22.gudao.server.web.exception.daoGuException;
import me.zj22.gudao.server.web.form.ProductInfoForm;
import me.zj22.gudao.server.web.pojo.dto.ProductInfo;
import me.zj22.gudao.server.web.pojo.vo.Page;
import me.zj22.gudao.server.web.service.ProductInfoService;
import me.zj22.gudao.server.web.utils.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
     * @param
     * @param
     * @return
     */
//    @GetMapping("/list")
//    public ModelAndView list(@RequestParam(value="page", defaultValue = "1") Integer page,
//                             @RequestParam(value="size", defaultValue = "10") Integer size,
//                             Map<String, Object> map){
//        Page<ProductInfo> pages = new Page();
//        pages.setPage(page);
//        pages.setRows(size);
//        Page<ProductInfo> productInfoPage = productInfoService.findAll(pages);
//        map.put("productInfoPage",productInfoPage);
//        map.put("currentPage", page);
//        map.put("size", size);
//        return new ModelAndView("product/list", map);
//
//    }

    /**
     * 商品列表
     * @param page
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(Page<ProductInfo> page){
        Page<ProductInfo> p = productInfoService.findAll(page);
        System.out.println("----page:"+p);
        return p.getPageMap();
    }

    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/on_sale")
    @ResponseBody
    public int onSale(@RequestParam("productId") Integer productId,
                                Map<String, Object> map){
        int i = 0;
        try {
            i = productInfoService.onSale(productId);
        }catch (daoGuException e){
//            map.put("msg", e.getMessage());
//            map.put("url", "/gudao/seller/product/list");
            return i;
        }
//        map.put("url", "/gudao/seller/product/list");
        return i;
    }

    /**
     * 商品下架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/off_sale")
    @ResponseBody
    public int offSale(@RequestParam("productId") Integer productId,
                               Map<String, Object> map){
        int i = 0;
        try {
            i = productInfoService.offSale(productId);
        }catch (daoGuException e){
//            map.put("msg", e.getMessage());
//            map.put("url", "/gudao/seller/product/list");
            return i;
        }
//        map.put("url", "/gudao/seller/product/list");
        return i;
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
    @ResponseBody
    public int save(@Valid ProductInfoForm productInfoForm,
                             BindingResult bindingResult,
                             MultipartFile[] files,
                             HttpServletRequest request,
                             Map<String, Object> map){
        //后台表单验证
//        if (bindingResult.hasErrors()) {
//            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
//            map.put("url", "/gudao/seller/product/index");
//            return new ModelAndView("common/error", map);
//        }

        ProductInfo productInfo = new ProductInfo();

        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                // 保存文件
                if(FileUpload.saveFile(request, file)){
                    LOG.info("==============图片上传成功!!!===============");
                }
                if(i==0)
                    productInfoForm.setProductIconOne(file.getOriginalFilename());
                else if(i==1)
                    productInfoForm.setProductIconTwo(file.getOriginalFilename());
                else if(i==2)
                    productInfoForm.setProductIconThree(file.getOriginalFilename());
            }
        }


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
            e.printStackTrace();
//            map.put("msg", e.getMessage());
//            map.put("url", "/gudao/seller/product/index");
//            return new ModelAndView("common/error", map);
        }
//        map.put("url", "/gudao/seller/product/list");
        return 1;
    }
    @RequestMapping("/delete")
    @ResponseBody
    public int deleteList(String[] pks){
        int i = 0;

        try {
            i = productInfoService.delete(pks);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

}
