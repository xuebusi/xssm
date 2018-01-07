package com.xuebusi.xssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuebusi.xssm.common.XResult;
import com.xuebusi.xssm.mapper.XUserMapper;
import com.xuebusi.xssm.pojo.XUser;
import com.xuebusi.xssm.pojo.XUserExample;
import com.xuebusi.xssm.service.XUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by SYJ on 2017/12/18.
 */
@Service
public class XUserServiceImpl implements XUserService {

    @Autowired
    private XUserMapper userMapper;

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Override
    public XUser selectByPrimaryKey(Integer id) {
        XUser user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @Override
    public int insert(XUser user) {
        return userMapper.insert(user);
    }

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<XUser> findAll() {
        return userMapper.selectByExample(null);
    }

    @Override
    public XResult list(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        XUserExample example = new XUserExample();
        List<XUser> userList = userMapper.selectByExample(example);
        PageInfo<XUser> pageInfo = new PageInfo<>(userList);
        XResult result = new XResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(pageInfo.getList());
        return result;
    }
}
