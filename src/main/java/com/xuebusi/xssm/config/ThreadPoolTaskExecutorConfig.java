package com.xuebusi.xssm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置
 *
 * @Author: syj
 * @CreateDate: 2018/7/17 14:48
 */
@Configuration
public class ThreadPoolTaskExecutorConfig {

    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor initThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        /**
         * 队列容量
         */
        threadPoolTaskExecutor.setQueueCapacity(200);
        /**
         * 核心线程数量
         */
        threadPoolTaskExecutor.setCorePoolSize(5);
        /**
         * 最大线程数量
         */
        threadPoolTaskExecutor.setMaxPoolSize(100);
        /**
         * 允许线程空闲时间
         */
        threadPoolTaskExecutor.setKeepAliveSeconds(300);
        /**
         * 重新初始化
         */
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

}
