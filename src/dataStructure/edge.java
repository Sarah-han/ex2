package dataStructure;

import utils.Point3D;

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
