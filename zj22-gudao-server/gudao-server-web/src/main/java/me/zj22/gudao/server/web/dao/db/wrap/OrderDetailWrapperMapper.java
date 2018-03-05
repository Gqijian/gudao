package me.zj22.gudao.server.web.dao.db.wrap;

import me.zj22.gudao.server.web.pojo.dto.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/6.
 */
@Mapper
public interface OrderDetailWrapperMapper extends OrderDetailMapper {
    /**根据订单id查询订单详情*/
    List<OrderDetail> findByOrderId(String orderId);
}
