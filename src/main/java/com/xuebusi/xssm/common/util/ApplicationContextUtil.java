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

    public static <T> T getBean(String beanName) {
        assertApplicationContext();
        return (T) applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> clazz) {
        assertApplicationContext();
        return applicationContext.getBean(clazz);
    }

    private static void assertApplicationContext() {
        if (ApplicationContextUtil.applicationContext == null) {
            throw new RuntimeException("applicaitonContext属性为null!");
        }
    }
}
