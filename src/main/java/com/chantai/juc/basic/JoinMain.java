package com.chantai.juc.basic;

/**
 * @Auther: changtai.zhao
 * @Date: 2018-12-24 06:25
 * @Description:
 */
public class JoinMain {

    public volatile static int i = 0;

    public static class AddThread extends Thread{
        @Override
        public void run() {
            for(i=0; i<10000000; i++){

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread thread = new AddThread();
        thread.start();
        thread.join();
        System.out.println(i);
    }

}
