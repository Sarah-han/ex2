package dataStructure;
import java.util.*;
import utils.Point3D;

public class node implements node_data {

    private int key;
    private Point3D location;
    private double weight;
    private int tag;

    public node(node n) {
        this.key = n.key;
        this.location = n.location;
        this.tag = n.tag;
        this.weight = n.weight;
    }
    public node(int key, Point3D location,double weight) {
        this.key = key;
        this.location = location;
        this.tag = 0;
        this.weight = weight;
    }


    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public Point3D getLocation() {
        if (this.location != null) return this.location;
        else return null;
    }

    @Override
    public void setLocation(Point3D p) {
        this.location = new Point3D(p);
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    @Override
    public String getInfo() {
        return "key:" + this.key + " location:" + this.location + " weight:" + this.weight + " tag:" + this.tag;
    }
    @Override
    public String toString() {
        return "key:" + this.key + " location:" + this.location + " weight:" + this.weight + " tag:" + this.tag;
    }

    @Override
    public void setInfo(String s) {
        String str = s;
        String[] splitData = str.split("[:\\ ]");
        key = Integer.parseInt(splitData[1]);
        location = new Point3D(splitData[3]);
        weight = Double.parseDouble(splitData[5]);
        tag = Integer.parseInt(splitData[7]);
    }


    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) { this.tag = t; }
}