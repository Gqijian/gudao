package me.zj22.gudao.server.web.service;

import me.zj22.gudao.server.web.pojo.dto.Points;
import me.zj22.gudao.server.web.pojo.vo.Page;

/**
 * @Description:
 * @Author Gqjian
 * @Create 2018/3/12 19:56
 */
public interface PointsService {

    /**查询积分列表,分页*/
    Page<Points> findAllList(Page<Points> page);

    Points findUserPoints(String orderId);
}
