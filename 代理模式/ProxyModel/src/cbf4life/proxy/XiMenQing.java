package cbf4life.proxy;

public class XiMenQing {

    public static void main(String[] args) {
//        WangPo wangPo =new WangPo();
//
//        wangPo.happyWithMan();
//        wangPo.makeEyeWithMan();


            JiaShi jiaShi=new JiaShi();
            WangPo wangPo =new WangPo(jiaShi);

            wangPo.happyWithMan();
            wangPo.makeEyeWithMan();


    }
}
