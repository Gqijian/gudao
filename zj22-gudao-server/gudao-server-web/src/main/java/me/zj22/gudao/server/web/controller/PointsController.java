package me.zj22.gudao.server.web.controller;

import me.zj22.gudao.server.web.pojo.dto.Points;
import me.zj22.gudao.server.web.pojo.vo.Page;
import me.zj22.gudao.server.web.service.impl.PointsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 积分
 * @Program:zj22-gudao-server
 * @Description:
 * @Author Gqjian
 * @Create 2018/3/12 00:28:01
 */
@Controller
@RequestMapping("/points")
public class PointsController {

    private static final Logger LOG = LoggerFactory.getLogger(PointsController.class);

    @Autowired
    private PointsServiceImpl pointsService;

    @RequestMapping("/mypoints")
    public Model findUserPoints(String orderId, Model model){
        try {
            Points userPoints = pointsService.findUserPoints(orderId);
            model.addAttribute("userPoints",userPoints);
        }catch (Exception e){
            e.printStackTrace();
        }
        return model;
    }

    @RequestMapping("/allPoints")
    public ModelAndView findPointsAllList(@RequestParam(value="page", defaultValue = "1") Integer page,
                                          @RequestParam(value="size", defaultValue = "10") Integer size,
                                          Map<String, Object> map){
        Page<Points> pages = new Page();
        pages.setPage(page);
        pages.setRows(size);
        Page<Points> pointsPage = pointsService.findAllList(pages);
        map.put("PointsPage",pointsPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("/allPoints", map);
    }
}
