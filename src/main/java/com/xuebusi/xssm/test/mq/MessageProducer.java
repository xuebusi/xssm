package com.xuebusi.xssm.test.mq;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 消息生产者
 *
 * @Author: syj
 * @CreateDate: 2018/7/17 16:25
 */
public class MessageProducer<T> implements Runnable {

    private volatile boolean isRunning = true;

    private static AtomicInteger count = new AtomicInteger();

    private BlockingQueue<T> messageQueue;

    private static final int SLEEPTIME = 500;

    public MessageProducer(BlockingQueue<T> messageQueue) {
        this.messageQueue = messageQueue;
    }

    /**
     * 往队列中生产消息
     */
    @Override
    public void run() {
        Random random = new Random();
        while (isRunning) {
            try {
                Thread.sleep(random.nextInt(SLEEPTIME));
                String message = String.valueOf(count.incrementAndGet());
                messageQueue.put((T) message);
                System.out.println(Thread.currentThread().getName() + "==========生产消息:" + message);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        this.isRunning = false;
    }
}
