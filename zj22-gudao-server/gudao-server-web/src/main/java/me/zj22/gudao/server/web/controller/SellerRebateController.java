package me.zj22.gudao.server.web.controller;

import me.zj22.gudao.server.web.exception.daoGuException;
import me.zj22.gudao.server.web.pojo.dto.Points;
import me.zj22.gudao.server.web.pojo.dto.Rebate;
import me.zj22.gudao.server.web.pojo.vo.Page;
import me.zj22.gudao.server.web.service.PointsService;
import me.zj22.gudao.server.web.service.RebateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 折扣管理
 * daogu
 * Created by 袁鹏 on 2018/2/23.
 */
@Controller
@RequestMapping("/seller/rebate")
public class SellerRebateController {

    @Autowired
    private RebateService rebateService;

    /**
     * 折扣列表
     * @param page
     * @param size
     * @param map
     * @return
     */
//    @GetMapping("/list")
//    public ModelAndView list(@RequestParam(value="page", defaultValue = "1") Integer page,
//                             @RequestParam(value="size", defaultValue = "10") Integer size,
//                             Map<String, Object> map){
//        Page<Rebate> pages = new Page();
//        pages.setPage(page);
//        pages.setRows(size);
//        Page<Rebate> rebateInfoPage = rebateService.findAllList(pages);
//        map.put("rebateInfoPage",rebateInfoPage);
//        map.put("currentPage", page);
//        map.put("size", size);
//        return new ModelAndView("rebate/list", map);
//
//    }

    /**
     *
     * @param page
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(Page<Rebate> page){
        Page<Rebate> p = rebateService.findAllList(page);
        return p.getPageMap();

    }

    /**
     * 折扣列表
     * @param rebateId
     * @param map
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "rebateId", required = false) Integer rebateId,
                              Map<String, Object> map){
        if(rebateId != null){
            Rebate rebateInfo = rebateService.selectByPrimaryKey(rebateId);
            map.put("rebateInfo", rebateInfo);
        }
        return new ModelAndView("rebate/index",map);
    }

    /**
     * 保存方法
     * @param rebate
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public int save(Rebate rebate){

        try{
            if(rebate.getRebateId() != null){
                return rebateService.update(rebate);
            }
            rebateService.save(rebate);
        }catch (daoGuException e){
            e.printStackTrace();
            return 0;
        }
        return 1;
//        try {
//            rebateService.save(rebate);
//        }catch (daoGuException e) {
//            map.put("msg", e.getMessage());
//            map.put("url", "/gudao/seller/rebate/index");
//            return new ModelAndView("common/error", map);
//        }
//        map.put("url", "/gudao/seller/rebate/list");
//        return new ModelAndView("common/success", map);
    }

    /**
     * 删除折扣
     * @param pks
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public int deleteList(String[] pks){
        int i = 0;

        try {
            i = rebateService.delete(pks);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

}
