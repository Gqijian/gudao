package me.zj22.gudao.server.web.dao.db;

import me.zj22.gudao.server.web.pojo.dto.Order;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Order selectOrderById(String orderId);

    Order selectOrderOne(Map<String, Object> map);
}