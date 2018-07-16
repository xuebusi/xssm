package com.xuebusi.xssm.test.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池
 *
 * @Author: syj
 * @CreateDate: 2018/7/16 16:46
 */
public class ThreadPoolTest {

    private static ExecutorService threadPool = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Future future1 = threadPool.submit(new Task1());
        Future future2 = threadPool.submit(new Task2());
        Future future3 = threadPool.submit(new Task3());

        try {
            System.out.println(future1.get());
            System.out.println(future2.get());
            System.out.println(future3.get());
            long diffTime = (System.currentTimeMillis() - startTime)/1000;
            System.out.println("耗时" + diffTime + "秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

}
