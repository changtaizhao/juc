package com.chantai.juc.cp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author changtai.zhao
 * @date 2018-12-27 06:14
 */
public class SemaphoreDemo implements Runnable{
    final Semaphore semaphore = new Semaphore(5);

    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId() + ": done!");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        final SemaphoreDemo semaphoreDemo = new SemaphoreDemo();
        for(int i=0; i<20; i++){
            executorService.submit(semaphoreDemo);
        }
        executorService.shutdown();
    }
}
