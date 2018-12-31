package com.chantai.juc.pool;

import java.util.concurrent.*;

/**
 * @author changtai.zhao
 * @date 2018-12-31 13:55
 */
public class ExtensionThreadPool {

    public static class MyTask implements Runnable{

        public String name;
        public MyTask(String name){
            this.name = name;
        }

        public void run() {
            System.out.println("正在执行: Thead ID: " + Thread.currentThread().getId() + ", Task Name=" + name);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>()){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行:" + ((MyTask)r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成:" + ((MyTask)r).name);
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };
        for(int i=0; i<5; i++){
            MyTask myTask = new MyTask("TASK-GEYM-" + i);
            executorService.execute(myTask);
            Thread.sleep(10);
        }
        executorService.shutdown();
    }
}
