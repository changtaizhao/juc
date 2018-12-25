package com.chantai.juc.cp;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author changtai.zhao
 * @date 2018-12-26 07:04
 */
public class TestReentrantLock implements Runnable{

    public static ReentrantLock lock = new ReentrantLock();
    public static int i=0;

    public void run() {
        for(int j=0;j<10000000; j++){
            lock.lock();
            try{
                i++;
            }finally {
                lock.unlock();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestReentrantLock test = new TestReentrantLock();
        Thread t1 = new Thread(test);
        Thread t2 = new Thread(test);
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(i);
    }

}
