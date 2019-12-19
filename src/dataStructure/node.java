package dataStructure;
import java.util.*;
import utils.Point3D;

public class node implements node_data {

    private int key;
    private  Point3D location;
    private double weight;
    private int tag;


    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public Point3D getLocation() {
        if (this.location!= null)
        return this.location;
        else
        return null;
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
            this.weight=w;
    }

    @Override
    public String getInfo() {
        return "key:"+this.key+" location:"+this.location+" weight:"+this.weight;
    }

    @Override
    public void setInfo(String s) {
        String str = s;
        String[] splitData = str.split("[:\\ ]");
        //אופציה ליישום :
        //return "key:"+this.key+" location:"+this.location+" weight:"+this.weight;
        //         0          1        2              3          4          5


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