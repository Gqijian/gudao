package me.zj22.gudao.server.web.dao.db;

import me.zj22.gudao.server.web.pojo.dto.SellerInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SellerInfoMapper {

    int deleteByPrimaryKey(String id);

    int insert(SellerInfo record);

    int insertSelective(SellerInfo record);

    SellerInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SellerInfo record);

    int updateByPrimaryKey(SellerInfo record);

    SellerInfo findSeller(SellerInfo sellerInfo);
}