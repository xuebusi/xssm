package com.xuebusi.xssm.test.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 测试Spring注解配置Bean
 *
 * @Author: syj
 * @CreateDate: 2018/7/17 14:36
 */
@Configuration
public class SpringBeanConfig {

    @Bean(name = "springBean")
    public SpringBean getSpringBean() {
        SpringBean springBean = new SpringBean();
        springBean.setName("xuebusi");
        return springBean;
    }
}
