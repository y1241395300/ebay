package cn.sdcit.mapper;

import cn.sdcit.entity.DreamOrderItem;
import cn.sdcit.entity.DreamOrderItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DreamOrderItemMapper {
    int countByExample(DreamOrderItemExample example);

    int deleteByExample(DreamOrderItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(DreamOrderItem record);

    int insertSelective(DreamOrderItem record);

    List<DreamOrderItem> selectByExample(DreamOrderItemExample example);

    DreamOrderItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DreamOrderItem record, @Param("example") DreamOrderItemExample example);

    int updateByExample(@Param("record") DreamOrderItem record, @Param("example") DreamOrderItemExample example);

    int updateByPrimaryKeySelective(DreamOrderItem record);

    int updateByPrimaryKey(DreamOrderItem record);
}