package com.chantai.juc.pool;

import org.omg.Messaging.SyncScopeHelper;

import java.util.concurrent.*;

/**
 * @author changtai.zhao
 * @date 2018-12-31 13:43
 */
public class CustomThreadFactory {

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

    public static void main(String[] args) throws InterruptedException {
        MyTask myTask = new MyTask();
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(),
                new ThreadFactory() {
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setDaemon(true);
                        System.out.println("create " + t);
                        return t;
                    }
                });
        for(int i=0; i<5; i++){
            executorService.submit(myTask);
        }
        Thread.sleep(2000);
    }

}
