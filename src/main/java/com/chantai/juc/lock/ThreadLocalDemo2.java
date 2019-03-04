package com.chantai.juc.lock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author changtai.zhao
 * @date 2019-03-05 06:39
 */
public class ThreadLocalDemo2 {


    public static class ParseDate implements Runnable{
        static ThreadLocal<SimpleDateFormat>  tl = new ThreadLocal<>();
        int i = 0;
        public ParseDate(int i){
            this.i = i;
        }
        @Override
        public void run() {
            try {
                if(tl.get() == null){
                    tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                }
                //线程不安全
                Date date = tl.get().parse("2015-03-29 19:29:" + i%60);
                System.out.println(i + ":" + date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for(int i=0; i<1000; i++){
            es.execute(new ParseDate(i));
        }
        es.shutdown();
    }

}
