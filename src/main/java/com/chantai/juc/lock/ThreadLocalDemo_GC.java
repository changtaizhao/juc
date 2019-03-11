package com.chantai.juc.lock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author changtai.zhao
 * @date 2019-03-09 11:00
 */
public class ThreadLocalDemo_GC {

    static volatile ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected void finalize() throws Throwable {
            System.out.println(this.toString() + " thread local is gc");
        }
    };

    static volatile CountDownLatch cd = new CountDownLatch(10000);

    public static class ParseDate implements Runnable{

        int i = 0;
        public ParseDate(int i){
            this.i = i;
        }

        @Override
        public void run() {
            if(tl.get() == null){
                tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"){
                    @Override
                    protected void finalize() throws Throwable {
                        System.out.println(this.toString() + " simple date format is gc");
                    }
                });
                System.out.println(Thread.currentThread().getId() + ":create SimpleDateFormat");
            }
            try {
                Date t = tl.get().parse("2019-03-10 19:29:" + i%60);
            } catch (ParseException e) {
                e.printStackTrace();
            }finally {
                cd.countDown();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for(int i=0; i<10000; i++){
            es.execute(new ParseDate(i));
        }
        cd.await();
        System.out.println("mission complete");
        tl = null;
        System.gc();
        System.out.println("first gc complete");
        //清除ThreadLocalMap中无效对象
        tl = new ThreadLocal<SimpleDateFormat>();
        cd = new CountDownLatch(10000);
        for(int i=0; i<10000; i++){
            es.execute(new ParseDate(i));
        }
        cd.await();
        Thread.sleep(1000);
        tl = null;
        System.gc();
        System.out.println("second gc complete");
        //es.shutdown();
    }
}
