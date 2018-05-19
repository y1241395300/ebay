package cn.sdcit.mapper;

import cn.sdcit.entity.EbayDetail;
import cn.sdcit.entity.EbayDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EbayDetailMapper {
    int countByExample(EbayDetailExample example);

    int deleteByExample(EbayDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EbayDetail record);

    int insertSelective(EbayDetail record);

    List<EbayDetail> selectByExample(EbayDetailExample example);

    EbayDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EbayDetail record, @Param("example") EbayDetailExample example);

    int updateByExample(@Param("record") EbayDetail record, @Param("example") EbayDetailExample example);

    int updateByPrimaryKeySelective(EbayDetail record);

    int updateByPrimaryKey(EbayDetail record);
}