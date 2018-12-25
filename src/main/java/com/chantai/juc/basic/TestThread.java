package com.chantai.juc.basic;

/**
 * @Auther: changtai.zhao
 * @Date: 2018-12-17 21:13
 * @Description:
 */
public class TestThread {

    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run(){
                System.out.println("test thread");
            }
        };
        thread.start();
    }

}
