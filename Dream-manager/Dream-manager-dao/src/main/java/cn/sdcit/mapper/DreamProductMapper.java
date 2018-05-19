package cn.sdcit.mapper;

import cn.sdcit.entity.DreamProduct;
import cn.sdcit.entity.DreamProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DreamProductMapper {
    int countByExample(DreamProductExample example);

    int deleteByExample(DreamProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DreamProduct record);

    int insertSelective(DreamProduct record);

    List<DreamProduct> selectByExample(DreamProductExample example);

    DreamProduct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DreamProduct record, @Param("example") DreamProductExample example);

    int updateByExample(@Param("record") DreamProduct record, @Param("example") DreamProductExample example);

    int updateByPrimaryKeySelective(DreamProduct record);

    int updateByPrimaryKey(DreamProduct record);
}