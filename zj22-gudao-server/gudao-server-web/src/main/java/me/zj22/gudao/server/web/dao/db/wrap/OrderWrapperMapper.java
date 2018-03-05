package me.zj22.gudao.server.web.dao.db.wrap;

import me.zj22.gudao.server.web.dao.db.OrderMapper;
import me.zj22.gudao.server.web.pojo.dto.OrderDTO;
import me.zj22.gudao.server.web.pojo.vo.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/6.
 */
@Mapper
public interface OrderWrapperMapper extends OrderMapper{

    /**根据用户openid查询所有订单，在service层分页操作*/
    List<OrderDTO> findAllOrderByUserId(Page<OrderDTO> page);

    /**根据用户的openid查询订单数*/
    Integer findAllOrderCount(Integer userId);

    /**后台查询所有订单*/
    List<OrderDTO> findAllOrder(Page<OrderDTO> page);

    /**后台查询订单数*/
    Integer findOrderCount();
}
