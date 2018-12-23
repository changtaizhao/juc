package com.chantai.juc;

/**
 * @Auther: changtai.zhao
 * @Date: 2018-12-24 06:37
 * @Description:
 */
public class TestVolatile {

    static volatile int i=0;

    public static class PlusTask implements Runnable{
        public void run() {
            for(int k=0; k<10000; k++){
                i++;
            }
        }
    }



    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for(int i=0; i<10; i++){
            threads[i] = new Thread(new PlusTask());
            threads[i].start();
        }
        for(int i=0; i<10; i++){
            threads[i].join();
        }
        System.out.println(i);
    }

}
