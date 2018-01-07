package com.xuebusi.xssm.service;


import com.xuebusi.xssm.common.XResult;
import com.xuebusi.xssm.pojo.XUser;

import java.util.List;

/**
 * Created by SYJ on 2017/12/18.
 */
public interface XUserService {
    XUser selectByPrimaryKey(Integer id);

    int insert(XUser user);

    List<XUser> findAll();

    XResult findPage(int pageNum, int pageSize);
}
