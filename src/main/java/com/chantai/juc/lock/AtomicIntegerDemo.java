package com.chantai.juc.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author changtai.zhao
 * @date 2019-03-10 10:58
 */
public class AtomicIntegerDemo {

    public static AtomicInteger atomicInteger = new AtomicInteger();

    public static class AddThread implements Runnable {

        @Override
        public void run() {
            for(int k=0; k<10000; k++){
                atomicInteger.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for(int k=0; k<10; k++){
            threads[k] = new Thread(new AddThread());
        }
        for(int k=0; k<10; k++){
            threads[k].start();
        }
        for(int k=0; k<10; k++){
            threads[k].join();
        }
        System.out.println(atomicInteger);
    }

}
