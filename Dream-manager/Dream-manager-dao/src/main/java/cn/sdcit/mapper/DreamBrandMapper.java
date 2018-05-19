package cn.sdcit.mapper;

import cn.sdcit.entity.DreamBrand;
import cn.sdcit.entity.DreamBrandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DreamBrandMapper {
    int countByExample(DreamBrandExample example);

    int deleteByExample(DreamBrandExample example);

    int deleteByPrimaryKey(Long brandId);

    int insert(DreamBrand record);

    int insertSelective(DreamBrand record);

    List<DreamBrand> selectByExample(DreamBrandExample example);

    DreamBrand selectByPrimaryKey(Long brandId);

    int updateByExampleSelective(@Param("record") DreamBrand record, @Param("example") DreamBrandExample example);

    int updateByExample(@Param("record") DreamBrand record, @Param("example") DreamBrandExample example);

    int updateByPrimaryKeySelective(DreamBrand record);

    int updateByPrimaryKey(DreamBrand record);
}