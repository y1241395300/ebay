package cn.sdcit.mapper;

import cn.sdcit.entity.DreamProductDesc;
import cn.sdcit.entity.DreamProductDescExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DreamProductDescMapper {
    int countByExample(DreamProductDescExample example);

    int deleteByExample(DreamProductDescExample example);

    int deleteByPrimaryKey(Long proId);

    int insert(DreamProductDesc record);

    int insertSelective(DreamProductDesc record);

    List<DreamProductDesc> selectByExampleWithBLOBs(DreamProductDescExample example);

    List<DreamProductDesc> selectByExample(DreamProductDescExample example);

    DreamProductDesc selectByPrimaryKey(Long proId);

    int updateByExampleSelective(@Param("record") DreamProductDesc record, @Param("example") DreamProductDescExample example);

    int updateByExampleWithBLOBs(@Param("record") DreamProductDesc record, @Param("example") DreamProductDescExample example);

    int updateByExample(@Param("record") DreamProductDesc record, @Param("example") DreamProductDescExample example);

    int updateByPrimaryKeySelective(DreamProductDesc record);

    int updateByPrimaryKeyWithBLOBs(DreamProductDesc record);

    int updateByPrimaryKey(DreamProductDesc record);
}