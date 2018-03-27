package me.zj22.gudao.server.web.service.impl;

import me.zj22.gudao.server.web.dao.db.wrap.PointsWrapperMapper;
import me.zj22.gudao.server.web.pojo.dto.Points;
import me.zj22.gudao.server.web.pojo.vo.Page;
import me.zj22.gudao.server.web.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Program:zj22-gudao-server
 * @Description:
 * @Author Gqjian
 * @Create 2018/3/12 20:08:02
 */
@Service
public class PointsServiceImpl implements PointsService{
    @Autowired
    private PointsWrapperMapper pointsWrapperMapper;

    @Override
    public Page<Points> findAllList(Page<Points> page) {
        List<Points> pointsList = pointsWrapperMapper.findAllPoints(page);
        Integer totalRecord = pointsWrapperMapper.findPointsCount();
        page.setList(pointsList);
        page.setTotalRecord(totalRecord);
        return page;
    }

    @Override
    public Points findUserPoints(String orderId) {
        Points points = pointsWrapperMapper.findByOrderId(orderId);
        return points;
    }
}
