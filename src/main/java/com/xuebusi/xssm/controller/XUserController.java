package com.xuebusi.xssm.controller;

import com.alibaba.fastjson.JSON;
import com.xuebusi.xssm.common.PageResult;
import com.xuebusi.xssm.common.util.ApplicationContextUtil;
import com.xuebusi.xssm.core.log.LogManager;
import com.xuebusi.xssm.core.log.LogTaskFactory;
import com.xuebusi.xssm.dto.UserDto;
import com.xuebusi.xssm.pojo.XUser;
import com.xuebusi.xssm.pojo.XUserExample;
import com.xuebusi.xssm.service.XUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

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


    @RequestMapping(value = "/selectByExample", method = RequestMethod.GET)
    @ResponseBody
    public List<XUser> selectByExample() {
        XUserExample userExample = new XUserExample();
        userExample.setOrderByClause("id asc");
        userExample.setOrderByClause("age desc");
        System.out.println("=========================" + userExample.getOrderByClause());

        XUserExample.Criteria criteria = userExample.createCriteria();
        List<Integer> idList = new ArrayList<>();
        if (!idList.isEmpty()) {
            criteria.andIdIn(idList);
        }
        List<XUser> userList = userService.selectByExample(userExample);
        return userList;
    }

    /**
     * 测试更新
     * @return
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.GET)
    public String updateUser() {
        List<XUser> userList = userService.findAll();
        UserDto userDto = new UserDto();
        userDto.setAge("100");
        for (XUser user : userList) {
            userService.update(user.getId(), userDto);
        }
        return "true";
    }

    /**
     * 测试修改（待查询条件）
     * @return
     */
    @RequestMapping(value = "updateByExampleSelective")
    @ResponseBody
    public int updateByExampleSelective() {
        XUser xUser = new XUser();
        xUser.setName("====");
        XUserExample xUserExample = new XUserExample();
        xUserExample.createCriteria().andNameEqualTo("呵呵1");
        int count = userService.updateByExampleSelective(xUser, xUserExample);
        return count;
    }

    /**
     * 测试日志
     * @return
     */
    @RequestMapping(value = "/testLog")
    public String testLog(@RequestParam(value = "userId") String userId) {
        TimerTask logTask = LogTaskFactory.testLog(Integer.valueOf(userId));
        LogManager.me().executor(logTask);
        return "success";
    }

    /**
     * 测试Spring线程池
     * @param id
     * @return
     */
    @RequestMapping(value = "testThreadPoolTaskExecutor")
    @ResponseBody
    public List<XUser> testThreadPoolTaskExecutor(@RequestParam(value = "id") String id) {
        try {
            final Integer userId = Integer.valueOf(id);
            ApplicationContext applicationContext = ApplicationContextUtil.getApplicationContext();
            ThreadPoolTaskExecutor executor = applicationContext.getBean(ThreadPoolTaskExecutor.class);
            Future<List<XUser>> submit = executor.submit(new Callable<List<XUser>>() {
                @Override
                public List<XUser> call() throws Exception {
                    XUserExample example = new XUserExample();
                    example.createCriteria().andIdEqualTo(userId);
                    List<XUser> userList = userService.selectByExample(example);
                    System.out.println(JSON.toJSONString(userList));
                    return userList;
                }
            });
            List<XUser> userList = submit.get();
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
