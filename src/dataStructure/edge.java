package dataStructure;

public class edge implements edge_data {
    private int src;
    private int dest;
    private double weight;
    private int tag;

    @Override
    public int getSrc() {

    return this.src;
}
    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String getInfo() {
        return "src:"+this.src+" dest:"+this.dest+" weight:"+this.weight;
    }

    @Override
    public void setInfo(String s) {
// ממתינה שאנשים יעשו ויהיו תשובות
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag=t;

    }
}
