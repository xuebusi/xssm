package com.xuebusi.xssm.core.log;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  日志管理器
 *
 * @Author: syj
 * @CreateDate: 2018/7/17 9:38
 */
public class LogManager {
    /**
     * 记录日志延时
     */
    private final int OPERATE_DELAY_TIME = 10;

    public static LogManager logManager = new LogManager();

    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);

    public LogManager() {

    }

    public static LogManager me() {
        return logManager;
    }

    public void executor(TimerTask task) {
        executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }
}
