package cbf4life.proxy;

public class WangPo implements KindWomen {

    private KindWomen kindWomen;

    public WangPo(){
        this.kindWomen = new PanJinLian();
    }

    public WangPo(KindWomen kindWomen) {
        this.kindWomen = kindWomen;
    }

    public void happyWithMan() {
        this.kindWomen.happyWithMan(); //王婆老了，整不了了，让年轻的人代替来干吧
    }

    public void makeEyeWithMan() {
        this.kindWomen.makeEyeWithMan(); //王婆年龄大了，没有男人愿意看
    }
}
