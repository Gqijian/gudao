package me.zj22.gudao.server.web.dao.db.wrap;

import me.zj22.gudao.server.web.dao.db.RebateMapper;
import me.zj22.gudao.server.web.pojo.dto.Rebate;
import me.zj22.gudao.server.web.pojo.vo.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/23.
 */
@Mapper
public interface RebateWrapperMapper extends RebateMapper{
    /**后台查询所有订单*/
    List<Rebate> findAllRebate(Page<Rebate> page);

    /**后台查询订单数*/
    Integer findRebateCount();

    //删除折扣
    Integer delete(String pks[]);
}
