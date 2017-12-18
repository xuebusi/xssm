package com.xuebusi.xssm.mapper;

import com.xuebusi.xssm.pojo.XUser;
import com.xuebusi.xssm.pojo.XUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XUserMapper {
    int countByExample(XUserExample example);

    int deleteByExample(XUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XUser record);

    int insertSelective(XUser record);

    List<XUser> selectByExample(XUserExample example);

    XUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XUser record, @Param("example") XUserExample example);

    int updateByExample(@Param("record") XUser record, @Param("example") XUserExample example);

    int updateByPrimaryKeySelective(XUser record);

    int updateByPrimaryKey(XUser record);
}