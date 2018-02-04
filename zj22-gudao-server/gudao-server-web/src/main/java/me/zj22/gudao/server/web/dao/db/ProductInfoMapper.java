package me.zj22.gudao.server.web.dao.db;

import me.zj22.gudao.server.web.pojo.dto.ProductInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductInfoMapper {
    int deleteByPrimaryKey(Integer productId);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    ProductInfo selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKeyWithBLOBs(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);
}