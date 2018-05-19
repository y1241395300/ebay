package cn.sdcit.mapper;

import cn.sdcit.entity.DreamUser;
import cn.sdcit.entity.DreamUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DreamUserMapper {
    int countByExample(DreamUserExample example);

    int deleteByExample(DreamUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DreamUser record);

    int insertSelective(DreamUser record);

    List<DreamUser> selectByExample(DreamUserExample example);

    DreamUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DreamUser record, @Param("example") DreamUserExample example);

    int updateByExample(@Param("record") DreamUser record, @Param("example") DreamUserExample example);

    int updateByPrimaryKeySelective(DreamUser record);

    int updateByPrimaryKey(DreamUser record);
}