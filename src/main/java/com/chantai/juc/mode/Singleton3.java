package com.chantai.juc.mode;

/**
 * @author changtai.zhao
 * @date 2019-03-19 21:48
 */
public class Singleton3 {

    private Singleton3(){
        System.out.println("Singleton3 is create");
    }

    private static class SingletonHolder{
        private static Singleton3 instance = new Singleton3();
    }

    public static Singleton3 getInstance(){
        return SingletonHolder.instance;
    }

}
