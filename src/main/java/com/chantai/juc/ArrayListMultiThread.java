package com.chantai.juc;

import java.util.ArrayList;

/**
 * @author changtai.zhao
 * @date 2018-12-26 06:35
 */
public class ArrayListMultiThread {

    static ArrayList<Integer> list = new ArrayList<Integer>(10);

    public static class AddThread implements Runnable{

        public void run() {
            for(int i=0; i<1000000; i++){
                list.add(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AddThread());
        Thread t2 = new Thread(new AddThread());

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(list.size());
    }


}
