package com.xuebusi.xssm.controller;

import com.alibaba.fastjson.JSON;
import com.xuebusi.xssm.common.JsonResult;
import com.xuebusi.xssm.common.PageResult;
import com.xuebusi.xssm.common.Result;
import com.xuebusi.xssm.common.ViewHint;
import com.xuebusi.xssm.pojo.XUser;
import com.xuebusi.xssm.service.XUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 学布斯 on 2017/12/18.
 */
@Controller
@RequestMapping(value = "/user")
public class XUserController extends BaseController {

    @Autowired
    private XUserService userService;

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}")
    @ResponseBody
    public XUser get(@PathVariable("id") Integer id) {
        return userService.selectByPrimaryKey(id);
    }

    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping(value = "/findAll")
    @ResponseBody
    public List<XUser> findAll() {
        return userService.findAll();
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/findPage")
    @ResponseBody
    public PageResult findPage(@RequestParam("page") int pageNum, @RequestParam("rows") int pageSize) {
        return userService.findPage(pageNum, pageSize);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(XUser user) {
        String beanValidator = beanValidator(user);
        if (!StringUtils.isEmpty(beanValidator)) {
            return JsonResult.fail(ViewHint.PARAM_ERROR, beanValidator);
        }
        userService.insert(user);
        return JsonResult.success(ViewHint.SUCCESS, user);
    }

    /**
     * 添加用户
     * @return
     */
    @RequestMapping(value = "/testAdd")
    @ResponseBody
    public int testAdd() {
        XUser user = new XUser();
        user.setName("王五");
        user.setAge(25);
        user.setPhone("13910108899");
        user.setAddress("重庆");
        return userService.insert(user);
    }

}
