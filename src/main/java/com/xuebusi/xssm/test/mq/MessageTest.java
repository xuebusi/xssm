package com.xuebusi.xssm.test.mq;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 生产者和消费者测试
 *
 * @Author: syj
 * @CreateDate: 2018/7/17 16:38
 */
public class MessageTest {

    public static ExecutorService executorService = Executors.newFixedThreadPool(2);
    public static LinkedBlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        executorService.execute(new MessageProducer<>(messageQueue));
        executorService.execute(new MessageConsumer<>(messageQueue));
    }
}
