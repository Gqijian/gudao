package me.zj22.gudao.server.web.service;

import me.zj22.gudao.server.web.pojo.dto.Points;
import me.zj22.gudao.server.web.pojo.vo.Page;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/23.
 */
public interface PointsService {

    /**查询积分列表,分页*/
    Page<Points> findAllList(Page<Points> page);
}
