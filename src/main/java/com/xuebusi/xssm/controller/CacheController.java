package com.xuebusi.xssm.controller;

import com.xuebusi.xssm.service.impl.CacheServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.CountDownLatch;

/**
 * Created by 学布斯 on 2017/12/18.
 */
@Controller
@RequestMapping(value = "/cache")
public class CacheController extends BaseController {

    @Autowired
    private CacheServiceImpl cachePassService;

    private static final int COUNT_DOWN_NUM = 20;

    private CountDownLatch countDownLatch = new CountDownLatch(COUNT_DOWN_NUM);

    /**
     * 测试缓存穿透
     * 模拟20个并发同时发起请求
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/cachePass/{id}")
    @ResponseBody
    public String cachePass(@PathVariable("id") final Integer id) {
        for (int i = 0; i < COUNT_DOWN_NUM; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    cachePassService.selectByPrimaryKey(id);
                }
            }).start();
            countDownLatch.countDown();
        }
        return "success";
    }
}
