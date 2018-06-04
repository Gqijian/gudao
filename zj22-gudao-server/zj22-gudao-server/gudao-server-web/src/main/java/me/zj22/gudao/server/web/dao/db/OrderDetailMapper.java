package me.zj22.gudao.server.web.dao.db;

import me.zj22.gudao.server.web.pojo.dto.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderDetailMapper {
    int deleteByPrimaryKey(String detailId);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(String detailId);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);

    /**根据订单id查询订单详情*/
    List<OrderDetail> findByOrderId(String orderId);

    List<OrderDetail> selectOrderDetailsId(String orderId);
}