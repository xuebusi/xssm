package com.xuebusi.xssm.test.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 使用AnnotationConfigApplicationContext
 * 从Spring容器中获取Bean
 *
 * @Author: syj
 * @CreateDate: 2018/7/17 14:38
 */
public class SpringBeanConfigMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringBeanConfig.class);
        SpringBean springBean = (SpringBean) applicationContext.getBean("springBean");
        System.out.println(springBean.getName());
    }
}
