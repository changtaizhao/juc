package com.chantai.juc;

/**
 * @Auther: changtai.zhao
 * @Date: 2018-12-17 21:59
 * @Description:
 */
public class TestRunnable {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                System.out.println("test runnable");
            }
        });
        thread.start();
    }

}
