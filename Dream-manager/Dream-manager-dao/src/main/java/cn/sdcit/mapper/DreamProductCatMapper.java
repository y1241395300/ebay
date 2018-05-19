package cn.sdcit.mapper;

import cn.sdcit.entity.DreamProductCat;
import cn.sdcit.entity.DreamProductCatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DreamProductCatMapper {
    int countByExample(DreamProductCatExample example);

    int deleteByExample(DreamProductCatExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DreamProductCat record);

    int insertSelective(DreamProductCat record);

    List<DreamProductCat> selectByExample(DreamProductCatExample example);

    DreamProductCat selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DreamProductCat record, @Param("example") DreamProductCatExample example);

    int updateByExample(@Param("record") DreamProductCat record, @Param("example") DreamProductCatExample example);

    int updateByPrimaryKeySelective(DreamProductCat record);

    int updateByPrimaryKey(DreamProductCat record);
}