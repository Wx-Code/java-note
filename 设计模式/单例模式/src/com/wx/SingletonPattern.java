package com.wx;

public class SingletonPattern {

    /*

    private  static SingletonPattern singletonPattern =null;
    private SingletonPattern(){
        //限制只有一个皇帝
    }

    public SingletonPattern getInstance(){
        if (this.singletonPattern == null){
            this.singletonPattern =new SingletonPattern();
        }

        return  singletonPattern;
    }
    *
    */


    private  static SingletonPattern singletonPattern = new SingletonPattern();
    public  SingletonPattern getInstance(){
        return  singletonPattern;
    }
}
