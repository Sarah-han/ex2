package dataStructure;

public class edge implements edge_data {
    private int src;
    private int dest;
    private double weight;
    private int tag;

    public edge(edge ed) {
        this.src=ed.src;
        this.dest=ed.dest;
        this.tag =ed.tag;
        this.weight =ed.weight;
    }

    public edge(int src, int dest, double w) {
        this.src=src;
        this.dest=dest;
       this.weight=w;
       this.tag=0;
    }

    @Override
    public int getSrc() { return this.src; }
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
        return "src:"+this.src+" dest:"+this.dest+" weight:"+this.weight+" tag:"+this.tag;
    }
    public String toString() {
        return "src:"+this.src+" dest:"+this.dest+" weight:"+this.weight+" tag:"+this.tag;
    }


    @Override
    public void setInfo(String s) {
        String str = s;
        String[] splitData = str.split("[:\\ ]");
        src = Integer.parseInt(splitData[1]);
        dest = Integer.parseInt(splitData[3]);
        weight = Double.parseDouble(splitData[5]);
        tag = Integer.parseInt(splitData[7]);
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
