package com.chantai.juc.mode;

/**
 * @author changtai.zhao
 * @date 2019-03-19 21:47
 */
public class Singleton2 {

    private Singleton2(){
        System.out.println("Singleton2 is create");
    }

    private Singleton2 instance = null;

    public synchronized Singleton2 getInstance(){
        if(instance == null){
            instance = new Singleton2();
        }
        return instance;
    }

}
