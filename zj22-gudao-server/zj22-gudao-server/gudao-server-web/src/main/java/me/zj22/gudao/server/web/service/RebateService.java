package me.zj22.gudao.server.web.service;

import me.zj22.gudao.server.web.pojo.dto.Rebate;
import me.zj22.gudao.server.web.pojo.vo.Page;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/23.
 */
public interface RebateService {

    /**保存商品*/
    void save(Rebate rebate);

    Rebate selectByPrimaryKey(Integer rebateId);

    /**查询折扣列表,分页*/
    Page<Rebate> findAllList(Page<Rebate> page);

    int update(Rebate rebate);

    //删除折扣
    Integer delete(String pks[]);

    //冻结和解冻积分
    Integer freeze(Rebate rebate);

    Integer nofreeze(Rebate rebate);
}
