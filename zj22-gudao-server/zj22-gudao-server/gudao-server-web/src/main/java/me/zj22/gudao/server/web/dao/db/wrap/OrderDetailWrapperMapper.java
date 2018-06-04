package me.zj22.gudao.server.web.dao.db.wrap;

import me.zj22.gudao.server.web.dao.db.OrderDetailMapper;
import me.zj22.gudao.server.web.pojo.dto.OrderDetail;
import me.zj22.gudao.server.web.pojo.vo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author Gqjian
 * @Create 2018/2/6 14:26
 */
@Mapper
@Repository
public interface OrderDetailWrapperMapper extends OrderDetailMapper {
    /**根据订单id查询订单详情*/
    List<OrderDetail> findByOrderId(String orderId);

    /**根据订单id查询详情*/
    List<OrderDetail> findDetailByOrderId(Page<OrderDetail> page);

    /**根据订单id查询详情数*/
    Integer findDetailCountByOrderId(Page<OrderDetail> page);

    /**通过订单号删除订单*/
    Integer deleteByOrderId(String orderId);
}
