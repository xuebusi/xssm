package com.xuebusi.xssm.test.thread;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @Author: syj
 * @CreateDate: 2018/7/16 16:48
 */
public class Task2 implements Callable {
    @Override
    public String call() throws Exception {
        long start = System.currentTimeMillis();
        Thread.sleep(new Random().nextInt(5000));
        long time = (System.currentTimeMillis() - start) / 1000;
        return "任务2耗时" + time + "秒";
    }
}
