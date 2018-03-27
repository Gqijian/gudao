package me.zj22.gudao.server.web.controller;

import me.zj22.gudao.server.web.pojo.dto.ProductInfo;
import me.zj22.gudao.server.web.service.ProductInfoService;
import me.zj22.gudao.server.web.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description:
 * @Author Gqjian
 * @Create 2018/3/18 16:45
 */
@Controller
@RequestMapping("/product")
public class ProductInfoController {
    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 查询全部商品
     * @return
     */
    @RequestMapping("/allProducts")
    public String findAllProducts(){
        String json;
        try{
            List<ProductInfo> allProducts = productInfoService.findAllProducts();
            json = JsonUtils.objectToJson(allProducts);
            return json;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
