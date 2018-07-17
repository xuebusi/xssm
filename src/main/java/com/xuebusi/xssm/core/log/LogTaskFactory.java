package com.xuebusi.xssm.core.log;

import com.alibaba.fastjson.JSON;
import com.xuebusi.xssm.controller.SpringContextHolder;
import com.xuebusi.xssm.pojo.XUser;
import com.xuebusi.xssm.pojo.XUserExample;
import com.xuebusi.xssm.service.XUserService;

import java.util.List;
import java.util.TimerTask;

/**
 * 日志操作任务创建工厂
 *
 * @Author: syj
 * @CreateDate: 2018/7/17 9:49
 */
public class LogTaskFactory {

    private static XUserService userService = SpringContextHolder.getBean(XUserService.class);

    /**
     * 测试
     * @param userId
     * @return
     */
    public static TimerTask testLog(final int userId) {
        System.out.println("userService[" + userService + "]");
        return new TimerTask() {
            @Override
            public void run() {
                XUserExample example = new XUserExample();
                example.createCriteria().andIdEqualTo(userId);
                List<XUser> list = userService.selectByExample(example);
                System.out.println(JSON.toJSONString(list));
            }
        };
    }
}
