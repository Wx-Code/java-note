package com.iuwzx;

/*
* 计谋有了，需要锦囊
* */
public class Context {

    private IStrategy straegy;
    public Context(IStrategy strategy){
        this.straegy = strategy;
    }

    //使用计谋，看我出招
    public void operate(){
        this.straegy.operate();
    }

}
