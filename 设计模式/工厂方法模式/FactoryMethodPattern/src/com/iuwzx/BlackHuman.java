package com.iuwzx;

public class BlackHuman implements Human {
    @Override
    public void laugh() {
        System.out.println("黑种人会大笑");
    }

    @Override
    public void cry() {
        System.out.println("黑种人会大哭");
    }

    @Override
    public void talk() {
        System.out.println("黑种人会大声说话");
    }
}
