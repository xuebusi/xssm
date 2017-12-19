package com.xuebusi.xssm.service.impl;

import com.xuebusi.xssm.mapper.XUserMapper;
import com.xuebusi.xssm.pojo.XUser;
import com.xuebusi.xssm.service.XUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by SYJ on 2017/12/18.
 */
@Service
public class XUserServiceImpl implements XUserService {

    @Autowired
    private XUserMapper xUserMapper;

    @Override
    public XUser selectByPrimaryKey(Integer id) {
        XUser user = xUserMapper.selectByPrimaryKey(id);
        return user;
    }
}
