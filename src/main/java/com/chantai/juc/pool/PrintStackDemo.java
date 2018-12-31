package com.chantai.juc.pool;

import java.util.concurrent.*;

/**
 * @author changtai.zhao
 * @date 2018-12-31 14:20
 */
public class PrintStackDemo {

    public static class DivTask implements Runnable{
        int a, b;
        public DivTask(int a, int b){
            this.a = a;
            this.b = b;
        }

        public void run() {
            System.out.println(a/b);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        for(int i=0; i<5; i++){
            //1. 看不到堆栈
            //threadPoolExecutor.submit(new DivTask(100, i));

            //2. 看到部分堆栈(任务提交信息没有)
            //threadPoolExecutor.execute(new DivTask(100, i));

            //3. 看到部分堆栈(任务提交信息没有)
            Future future = threadPoolExecutor.submit(new DivTask(100, i));
            try{
                future.get();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
