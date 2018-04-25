package com.xuebusi.xssm.controller;

import com.xuebusi.xssm.common.PageResult;
import com.xuebusi.xssm.dto.UserDto;
import com.xuebusi.xssm.pojo.XUser;
import com.xuebusi.xssm.service.XUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 学布斯 on 2017/12/18.
 */
@Controller
@RequestMapping(value = "/user")
public class XUserController extends BaseController {

    @Autowired
    private XUserService userService;

    @RequestMapping(value = "/getUserList")
    @ResponseBody
    public Map<String, List<XUser>> findUserList() {
        List<XUser> userList = userService.findAll();
        Map<String, List<XUser>> map = new HashMap<>();
        map.put("data", userList);
        return map;
    }

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
    public PageResult findPage(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "2") int pageSize,
                               @RequestParam(value = "userName", required = false) String userName,
                               @RequestParam(value = "phone", required = false) String phone) {
        System.out.println(userName + ", " + phone);
        return userService.findPage(pageNum, pageSize);
    }

    /**
     * 添加用户
     * @param userDto
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestBody UserDto userDto) {
        Map<String, String> resultMap = beanValidator(userDto);
        if (resultMap != null) {
            return paramError(resultMap);
        }
        XUser xUser = new XUser();
        xUser.setName(userDto.getName());
        xUser.setAddress(userDto.getAddress());
        if (!StringUtils.isEmpty(userDto.getAge())) {
            xUser.setAge(Integer.valueOf(userDto.getAge()));
        }
        xUser.setPhone(userDto.getPhone());
        userService.insert(xUser);
        return success();
    }
}
