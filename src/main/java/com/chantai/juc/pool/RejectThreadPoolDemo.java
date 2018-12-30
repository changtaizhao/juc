package com.chantai.juc.pool;

import java.util.concurrent.*;

/**
 * @author changtai.zhao
 * @date 2018-12-30 21:53
 */
public class RejectThreadPoolDemo {

    public static class MyTask implements Runnable{

        public void run() {
            System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        MyTask myTask = new MyTask();
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(10),
                Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(r.toString() + " is discard");
                    }
                });

        for(int i=0; i<Integer.MAX_VALUE; i++){
            executorService.submit(myTask);
            Thread.sleep(10);
        }
    }

}
