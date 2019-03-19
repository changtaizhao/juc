package com.chantai.juc.mode;

/**
 * @author changtai.zhao
 * @date 2019-03-19 21:45
 */
public class Singleton1 {

    private Singleton1(){
        System.out.println("Singleton1 is create");
    }

    private static Singleton1 instance = new Singleton1();

    public static Singleton1 getInstance() {
        return instance;
    }
}
