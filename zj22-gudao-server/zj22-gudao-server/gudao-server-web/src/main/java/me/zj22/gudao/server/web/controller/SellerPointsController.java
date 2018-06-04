package me.zj22.gudao.server.web.controller;

import me.zj22.gudao.server.web.pojo.dto.Points;
import me.zj22.gudao.server.web.pojo.vo.Page;
import me.zj22.gudao.server.web.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 积分管理
 * daogu
 * Created by 袁鹏 on 2018/2/23.
 */
@Controller
@RequestMapping("/seller/point")
public class SellerPointsController {

    @Autowired
    private PointsService pointsService;

    /**
     * 积分列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value="page", defaultValue = "1") Integer page,
                             @RequestParam(value="size", defaultValue = "10") Integer size,
                             Map<String, Object> map){
        Page<Points> pages = new Page();
        pages.setPage(page);
        pages.setRows(size);
        Page<Points> PointsPage = pointsService.findAllList(pages);
        map.put("PointsPage",PointsPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("points/list", map);

    }
}
