package com.chantai.juc;

/**
 * @Auther: changtai.zhao
 * @Date: 2018-12-17 22:31
 * @Description:
 */
public class TestInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true){
                    //wait sleep 线程也可以退出
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("interrupted ...");
                        break;
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println("interrupted when sleep");
                        //清除了中断标识，需要重新设置终端标识
                        Thread.currentThread().interrupt();
                    }
                    Thread.yield();
                }
            }
        };
        thread.start();

        Thread.sleep(2000);

        thread.interrupt();
    }

}
