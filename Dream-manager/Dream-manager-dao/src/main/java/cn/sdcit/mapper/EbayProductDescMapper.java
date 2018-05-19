package cn.sdcit.mapper;

import cn.sdcit.entity.EbayProductDesc;
import cn.sdcit.entity.EbayProductDescExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EbayProductDescMapper {
    int countByExample(EbayProductDescExample example);

    int deleteByExample(EbayProductDescExample example);

    int deleteByPrimaryKey(Long proId);

    int insert(EbayProductDesc record);

    int insertSelective(EbayProductDesc record);

    List<EbayProductDesc> selectByExampleWithBLOBs(EbayProductDescExample example);

    List<EbayProductDesc> selectByExample(EbayProductDescExample example);

    EbayProductDesc selectByPrimaryKey(Long proId);

    int updateByExampleSelective(@Param("record") EbayProductDesc record, @Param("example") EbayProductDescExample example);

    int updateByExampleWithBLOBs(@Param("record") EbayProductDesc record, @Param("example") EbayProductDescExample example);

    int updateByExample(@Param("record") EbayProductDesc record, @Param("example") EbayProductDescExample example);

    int updateByPrimaryKeySelective(EbayProductDesc record);

    int updateByPrimaryKeyWithBLOBs(EbayProductDesc record);

    int updateByPrimaryKey(EbayProductDesc record);
}