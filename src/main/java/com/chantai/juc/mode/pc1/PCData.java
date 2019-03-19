package com.chantai.juc.mode.pc1;

/**
 * @author changtai.zhao
 * @date 2019-03-19 21:55
 */
public final class PCData {

    private final int intData;
    public PCData(int intData){
        this.intData = intData;
    }
    public PCData(String strData){
        this.intData = Integer.valueOf(strData);
    }

    public int getData(){
        return intData;
    }

    @Override
    public String toString() {
        return "data:" + intData;
    }
}
