package com.xuebusi.xssm.test.mq;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * 消息消费者
 *
 * @Author: syj
 * @CreateDate: 2018/7/17 16:33
 */
public class MessageConsumer<T> implements Runnable {

    private BlockingQueue<T> messageQueue;

    private static final int SLEEPTIME = 500;

    public MessageConsumer(BlockingQueue<T> messageQueue) {
        this.messageQueue = messageQueue;
    }

    /**
     * 从队列消费消息
     */
    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            try {
                String message = (String) messageQueue.take();
                System.out.println(Thread.currentThread().getName() + "==========消费消息:" + message);
                Thread.sleep(random.nextInt(SLEEPTIME));
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
