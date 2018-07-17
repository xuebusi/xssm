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

    public static ExecutorService executorService = Executors.newCachedThreadPool();
    public static LinkedBlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        try {
            MessageProducer<String> producer = new MessageProducer<>(messageQueue);
            MessageConsumer<String> consumer = new MessageConsumer<>(messageQueue);
            executorService.execute(producer);
            executorService.execute(consumer);

            Thread.sleep(10 * 1000);
            producer.stop();

            Thread.sleep(3000);
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
