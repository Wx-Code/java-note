package com.iuwzx;

public class WhiteHuman implements Human {
    @Override
    public void laugh() {
        System.out.println("白种人会大笑");
    }

    @Override
    public void cry() {
        System.out.println("白种人会大哭");
    }

    @Override
    public void talk() {
        System.out.println("白种人会大声说话");
    }
}
