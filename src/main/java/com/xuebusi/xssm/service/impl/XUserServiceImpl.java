package com.xuebusi.xssm.service.impl;

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
    private XUserMapper xUserMapper;

    @Override
    public XUser selectByExample(Integer id) {
        XUserExample example = new XUserExample();
        XUserExample.Criteria criteria = example.createCriteria();
        //添加查询条件
        criteria.andIdEqualTo(id);
        List<XUser> userList = xUserMapper.selectByExample(example);
        XUser user = null;
        if (userList != null && userList.size() > 0) {
            user = userList.get(0);
        }
        return user;
    }
}
