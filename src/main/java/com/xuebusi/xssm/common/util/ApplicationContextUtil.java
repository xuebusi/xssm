package com.xuebusi.xssm.common.util;

import com.xuebusi.xssm.config.ThreadPoolTaskExecutorConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 获取ApplicationContext
 *
 * @Author: syj
 * @CreateDate: 2018/7/17 15:50
 */
public class ApplicationContextUtil {

    private static AnnotationConfigApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            applicationContext = new AnnotationConfigApplicationContext();
            applicationContext.register(ThreadPoolTaskExecutorConfig.class);
            applicationContext.refresh();
        }
        return applicationContext;
    }
}
