package com.chantai.juc.pool;

import java.util.concurrent.*;

/**
 * @author changtai.zhao
 * @date 2018-12-31 14:38
 */
public class TraceThreadPoolExecutor extends ThreadPoolExecutor {

    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public void execute(Runnable command) {
        super.execute(wrap(command, clientTrace(), Thread.currentThread().getName()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(wrap(task, clientTrace(), Thread.currentThread().getName()));
    }

    private Exception clientTrace(){
        return new Exception("Client stack trace");
    }

    private Runnable wrap(final Runnable task, final Exception clientStack, String clientThraedName){
        return new Runnable() {
            public void run() {
               try{
                   task.run();
               }catch (Exception e){
                   clientStack.printStackTrace();
                   try {
                       throw e;
                   } catch (Exception e1) {
                       e1.printStackTrace();
                   }
               }
            }
        };
    }

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

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new TraceThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        for(int i=0; i<5; i++){
            threadPoolExecutor.execute(new DivTask(100, i));
        }
    }

}
