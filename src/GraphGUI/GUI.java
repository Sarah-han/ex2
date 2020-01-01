package GraphGUI;
import algorithms.Graph_Algo;
import dataStructure.*;
import utils.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI extends JFrame implements ActionListener {
    private static graph graph;
    private JButton save;
    private JButton isconnected;
    private JButton tsp;
    private JButton shortestpathdist;
    private JButton shortestpath;
    private JButton load;



    public static void main(String[] args) {
        graph = new DGraph();
        /*graph.addNode(new node(1, new Point3D(350, 400, 350), 0));
        graph.addNode(new node(2, new Point3D(35, 325, 50), 0));
        graph.addNode(new node(3, new Point3D(250, 70, 10), 0));
        graph.addNode(new node(4, new Point3D(550, 400, 140), 0));
        graph.addNode(new node(5, new Point3D(135, 600, 250), 0));
        graph.addNode(new node(6, new Point3D(730, 250, 350), 0));
        graph.addNode(new node(7, new Point3D(630, 650, 350), 0));
        graph.addNode(new node(8, new Point3D(530, 100, 350), 0));
        graph.addNode(new node(9, new Point3D(470, 570, 370), 0));
        graph.addNode(new node(10, new Point3D(100, 100, 250), 0));
        graph.connect(1,2,8);
        graph.connect(2,1,10);
        graph.connect(2,3,1);
        graph.connect(3,2,2);
        graph.connect(3,4,2);
        graph.connect(4,5,2);
        graph.connect(5,3,15);
        graph.connect(2,6,20);
        graph.connect(1,10,30);
        graph.connect(6,7,22);
        graph.connect(6,8,60);
        graph.connect(7,3,7);
        graph.connect(8,9,7);
        graph.connect(9,2,19);
        graph.connect(9,8,8);
        graph.connect(10,8,7);
        graph.connect(10,6,2);
        graph.connect(9,5,3);
        graph.connect(2,5,21);
        */graph.addNode(new node(10, new Point3D(350, 400, 350), 0));
        graph.addNode(new node(11, new Point3D(35, 325, 50), 0));
        graph.addNode(new node(12, new Point3D(550, 400, 140), 0));
        graph.addNode(new node(13, new Point3D(630, 650, 350), 0));
        graph.addNode(new node(14, new Point3D(800, 100, 250), 0));
        graph.connect(10, 13, 4);
        graph.connect(10, 11, 4.5);
        graph.connect(10, 14, 1);
        graph.connect(11, 13, 5);
        graph.connect(12, 11, 17);
        graph.connect(13, 14, 1);
        graph.connect(13, 11, 20.5);
        graph.connect(13, 12, 1.5);
        graph.connect(14, 13, 2);
        graph.connect(11, 10, 4.5);
        graph.connect(13, 10, 8);

        GUI gui = new GUI();
        gui.setVisible(true);
    }

    public GUI() {
        INITGUI();
    }

    private void INITGUI() {
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MenuBar MB = new MenuBar();
        this.setMenuBar(MB);
        Menu File = new Menu("File");
        MB.add(File);
        Menu Algo = new Menu("Algo");
        MB.add(Algo);
        MenuItem Save = new MenuItem("Save");
        MenuItem Load = new MenuItem("Load");
        MenuItem TSP = new MenuItem("TSP");
        MenuItem ShortestPath = new MenuItem("ShortestPath");
        MenuItem isConnected = new MenuItem("isConnected");
        MenuItem ShortestPatchDist = new MenuItem("ShortestPatchDist");
        save=new JButton("save");
        load=new JButton("load");
        isconnected=new JButton("isconnected");
        tsp=new JButton("tsp");
        shortestpathdist=new JButton("shortestpathdist");
        shortestpath=new JButton("shortestpath");
        File.add(Save);
        File.add(Load);
        Algo.add(TSP);
        Algo.add(ShortestPath);
        Algo.add(isConnected);
        Algo.add(ShortestPatchDist);
        Save.addActionListener(this);
        Load.addActionListener(this);
        TSP.addActionListener(this);
        ShortestPath.addActionListener(this);
        isConnected.addActionListener(this);
        ShortestPatchDist.addActionListener(this);
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (node_data nodes : graph.getV()) {
            Point3D nodes_src = nodes.getLocation();
            g.setColor(Color.BLUE);
            g.fillOval((int) nodes_src.x() - 7, (int) nodes_src.y() - 7, 15, 15);
            g.drawString("" + nodes.getKey(), (int) nodes_src.x(), (int) (nodes_src.y() + 20));
            try {
                for (edge_data edges : graph.getE(nodes.getKey())) {
                    g.setColor(Color.RED);
                    Point3D nodes_dest = graph.getNode(edges.getDest()).getLocation();
                    g.drawLine((int) nodes_src.x(), (int) nodes_src.y(), (int) nodes_dest.x(), (int) nodes_dest.y());

                    g.setColor(Color.BLACK);
                    int directed_x = (int) (nodes_src.x() * 0.15 + nodes_dest.x() * 0.85);
                    int directed_y = (int) (nodes_src.y() * 0.15 + nodes_dest.y() * 0.85);
                    g.fillOval(directed_x - 4, directed_y - 2, 7, 7);
                    g.setColor(Color.PINK);
                    g.drawString("" + edges.getWeight(), directed_x, directed_y);
                }
            }
            catch(Exception e){
                //this node has no edges, the graph is still being initialized...
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if (str.equals("Save")) {
            JFileChooser fileChooser = new JFileChooser();
            Graph_Algo ga = new Graph_Algo();
            ga.init(graph);
            int retval = fileChooser.showSaveDialog(save);
            if (retval == JFileChooser.APPROVE_OPTION) {
                try {
                    ga.save(fileChooser.getSelectedFile()+".txt");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (str.equals("Load")) {
            JFileChooser fileChooser = new JFileChooser();
            Graph_Algo ga = new Graph_Algo();
            int retval = fileChooser.showOpenDialog(load);
            if (retval == JFileChooser.APPROVE_OPTION) {
                try {
                    File selectedFile = fileChooser.getSelectedFile();
                    ga.init(selectedFile.getAbsolutePath());
                    graph = ga.copy();
                    repaint();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (str.equals("TSP")) {
        }
        if (str.equals("isConnected")) {
            JFrame isConnected=new JFrame();
        }
        if (str.equals("ShortestPath")) {
        }
    }
}
