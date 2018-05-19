package cn.sdcit.mapper;

import cn.sdcit.entity.DreamOrderShipping;
import cn.sdcit.entity.DreamOrderShippingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DreamOrderShippingMapper {
    int countByExample(DreamOrderShippingExample example);

    int deleteByExample(DreamOrderShippingExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(DreamOrderShipping record);

    int insertSelective(DreamOrderShipping record);

    List<DreamOrderShipping> selectByExample(DreamOrderShippingExample example);

    DreamOrderShipping selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") DreamOrderShipping record, @Param("example") DreamOrderShippingExample example);

    int updateByExample(@Param("record") DreamOrderShipping record, @Param("example") DreamOrderShippingExample example);

    int updateByPrimaryKeySelective(DreamOrderShipping record);

    int updateByPrimaryKey(DreamOrderShipping record);
}