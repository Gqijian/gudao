package me.zj22.gudao.server.web.dao.db.wrap;

import me.zj22.gudao.server.web.dao.db.PointsMapper;
import me.zj22.gudao.server.web.pojo.dto.Points;
import me.zj22.gudao.server.web.pojo.dto.PointsKey;
import me.zj22.gudao.server.web.pojo.vo.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PointsWrapperMapper extends PointsMapper {
    //根据orderId查询Points
    Points findByOrderId(String orderId);

    /**后台查询所有订单*/
    List<Points> findAllPoints(Page<Points> page);

    /**后台查询订单数*/
    Integer findPointsCount();
}