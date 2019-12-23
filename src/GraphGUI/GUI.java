package GraphGUI;
import dataStructure.*;
import utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class GUI extends JFrame  {

    LinkedList<Point3D> points = new LinkedList<Point3D>();
    public GUI()
    {
        INITGUI();
    }
    private void INITGUI(){
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void paint(Graphics g)
    {
        super.paint(g);

        Point3D prev = null;

        for (Point3D p : points)
        {
            g.setColor(Color.MAGENTA);
            g.fillOval((int)p.x(), (int)p.y(), 10, 10);

            if(prev != null)
            {
                g.setColor(Color.BLACK);
                g.drawLine((int)p.x(), (int)p.y(), (int)prev.x(), (int)prev.y());

                g.drawString("5", (int)((p.x()+prev.x())/2),(int)((p.y()+prev.y())/2));
            }
            prev = p;
        }
    }
}
