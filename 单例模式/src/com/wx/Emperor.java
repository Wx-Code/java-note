package com.wx;

 // 皇帝
public class Emperor {

    private static  Emperor emperor= null;

    private Emperor(){
        //限制只有一个皇帝
    }

    public  static  Emperor getInstance(){
        // 如果没有皇帝，那就先定义一个皇帝
        if (emperor == null){
            emperor = new Emperor();
        }

        return  emperor;
    }

    public static void emperorInfo(){
        System.out.println("我就是皇帝xxxxx");
    }

}
