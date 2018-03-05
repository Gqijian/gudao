package me.zj22.gudao.server.web.service.impl;

import me.zj22.gudao.server.web.dao.db.wrap.RebateWrapperMapper;
import me.zj22.gudao.server.web.exception.SellerAuthorizeException;
import me.zj22.gudao.server.web.pojo.dto.Rebate;
import me.zj22.gudao.server.web.pojo.dto.SellerInfo;
import me.zj22.gudao.server.web.pojo.vo.Page;
import me.zj22.gudao.server.web.service.RebateService;
import me.zj22.gudao.server.web.utils.TimeParse;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/2/23.
 */
@Service
public class RebateServiceImp implements RebateService {

    @Autowired
    private RebateWrapperMapper rebateWrapperMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void save(Rebate rebate) {
        // 从session中获取修改人保存
        SellerInfo seller = (SellerInfo)request.getSession().getAttribute("seller");
        if(seller == null){
            throw new SellerAuthorizeException();
        }
        rebate.setCreateTime(TimeParse.Time2NUIX(TimeParse.NUIX2Time(new Date())));
        rebate.setUpdateTime(TimeParse.Time2NUIX(TimeParse.NUIX2Time(new Date())));
        rebate.setCreateUser(seller.getUsername());
        rebate.setUpdateUser(seller.getUsername());
        rebateWrapperMapper.insert(rebate);
    }

    @Override
    public Rebate selectByPrimaryKey(Integer rebateId) {
        return rebateWrapperMapper.selectByPrimaryKey(rebateId);
    }

    @Override
    public Page<Rebate> findAllList(Page<Rebate> page) {
        List<Rebate> rebateList = rebateWrapperMapper.findAllRebate(page);
        Integer totalRecord = rebateWrapperMapper.findRebateCount();
        page.setList(rebateList);
        page.setTotalRecord(totalRecord);
        return page;
    }
}
