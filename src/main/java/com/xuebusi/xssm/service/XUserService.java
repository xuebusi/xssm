package com.xuebusi.xssm.service;


import com.xuebusi.xssm.common.PageResult;
import com.xuebusi.xssm.dto.UserDto;
import com.xuebusi.xssm.pojo.XUser;
import com.xuebusi.xssm.pojo.XUserExample;

import java.util.List;

/**
 * Created by 学布斯 on 2017/12/18.
 */
public interface XUserService {
    XUser selectByPrimaryKey(Integer id);

    int insert(XUser user);

    List<XUser> findAll();

    PageResult findPage(int pageNum, int pageSize);

    PageResult findPage(int pageNum, int pageSize, String userName, String phone);

    List<XUser> selectByExample(XUserExample example);

    /**
     * 根据id修改用户信息
     * @param id
     * @return
     */
    int update(int id, UserDto userDto);

    int updateByExampleSelective(XUser record, XUserExample example);
}
