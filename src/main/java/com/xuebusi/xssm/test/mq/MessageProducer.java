package com.xuebusi.xssm.test.mq;

import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 消息生产者
 *
 * @Author: syj
 * @CreateDate: 2018/7/17 16:25
 */
public class MessageProducer<T> implements Runnable {

    private LinkedBlockingQueue<T> messageQueue;

    public MessageProducer(LinkedBlockingQueue<T> messageQueue) {
        this.messageQueue = messageQueue;
    }

    /**
     * 往队列中生产消息
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
                String message = UUID.randomUUID().toString();
                messageQueue.put((T) message);
                System.out.println(Thread.currentThread().getName() + "==========生产消息:" + message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
