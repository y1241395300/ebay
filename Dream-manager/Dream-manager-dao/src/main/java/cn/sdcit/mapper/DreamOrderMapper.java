package cn.sdcit.mapper;

import cn.sdcit.entity.DreamOrder;
import cn.sdcit.entity.DreamOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DreamOrderMapper {
    int countByExample(DreamOrderExample example);

    int deleteByExample(DreamOrderExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(DreamOrder record);

    int insertSelective(DreamOrder record);

    List<DreamOrder> selectByExample(DreamOrderExample example);

    DreamOrder selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") DreamOrder record, @Param("example") DreamOrderExample example);

    int updateByExample(@Param("record") DreamOrder record, @Param("example") DreamOrderExample example);

    int updateByPrimaryKeySelective(DreamOrder record);

    int updateByPrimaryKey(DreamOrder record);
}