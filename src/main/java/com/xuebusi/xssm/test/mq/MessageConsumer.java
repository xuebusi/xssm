package com.xuebusi.xssm.test.mq;

import java.util.concurrent.BlockingQueue;

/**
 * 消息消费者
 *
 * @Author: syj
 * @CreateDate: 2018/7/17 16:33
 */
public class MessageConsumer<T> implements Runnable {

    private BlockingQueue<T> messageQueue;

    public MessageConsumer(BlockingQueue<T> messageQueue) {
        this.messageQueue = messageQueue;
    }

    /**
     * 从队列消费消息
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
                String message = (String) messageQueue.take();
                System.out.println(Thread.currentThread().getName() + "==========消费消息:" + message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
