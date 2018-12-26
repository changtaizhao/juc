package com.chantai.juc.cp;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author changtai.zhao
 * @date 2018-12-26 21:58
 */
public class FairLock implements Runnable {
    public static ReentrantLock fairLock = new ReentrantLock(true);

    public void run() {
        while (true){
            try{
                fairLock.lock();
                System.out.println(Thread.currentThread().getName() + "获得锁");
            }finally {
                fairLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLock fairLock = new FairLock();
        Thread t1 = new Thread(fairLock, "Thread_1");
        Thread t2 = new Thread(fairLock, "Thread_2");
        t1.start();t2.start();
    }

}
