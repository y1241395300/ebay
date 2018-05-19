package cn.sdcit.mapper;

import cn.sdcit.entity.EbayProduct;
import cn.sdcit.entity.EbayProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EbayProductMapper {
    int countByExample(EbayProductExample example);

    int deleteByExample(EbayProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EbayProduct record);

    int insertSelective(EbayProduct record);

    List<EbayProduct> selectByExample(EbayProductExample example);

    EbayProduct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EbayProduct record, @Param("example") EbayProductExample example);

    int updateByExample(@Param("record") EbayProduct record, @Param("example") EbayProductExample example);

    int updateByPrimaryKeySelective(EbayProduct record);

    int updateByPrimaryKey(EbayProduct record);
}