package com.xuebusi.xssm.service;


import com.xuebusi.xssm.common.PageResult;
import com.xuebusi.xssm.pojo.XUser;

import java.util.List;

/**
 * Created by 学布斯 on 2017/12/18.
 */
public interface XUserService {
    XUser selectByPrimaryKey(Integer id);

    int insert(XUser user);

    List<XUser> findAll();

    PageResult findPage(int pageNum, int pageSize);
}
