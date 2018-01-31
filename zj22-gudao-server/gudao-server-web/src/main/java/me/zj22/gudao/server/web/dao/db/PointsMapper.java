package me.zj22.gudao.server.web.dao.db;

import me.zj22.gudao.server.web.dao.model.Points;
import me.zj22.gudao.server.web.dao.model.PointsKey;

public interface PointsMapper {
    int deleteByPrimaryKey(PointsKey key);

    int insert(Points record);

    int insertSelective(Points record);

    Points selectByPrimaryKey(PointsKey key);

    int updateByPrimaryKeySelective(Points record);

    int updateByPrimaryKey(Points record);
}