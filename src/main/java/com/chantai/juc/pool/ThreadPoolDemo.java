package com.chantai.juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author changtai.zhao
 * @date 2018-12-30 20:51
 */
public class ThreadPoolDemo {

    public static class MyTask implements Runnable{

        public void run() {
            System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyTask task = new MyTask();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i=0; i<10; i++){
            executorService.submit(task);
        }
        executorService.shutdown();
    }

}
