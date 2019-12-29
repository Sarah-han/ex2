package JUnitTesting;
import dataStructure.*;
import algorithms.Graph_Algo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Point3D;

import static org.junit.jupiter.api.Assertions.*;

class Graph_AlgoTest {
    static DGraph graph = new DGraph();
    @BeforeEach
    void init(){
        graph = new DGraph();
    }
    @Test
    void initByGraph() {
    }

    @Test
    void isConnected() {
        graph.addNode(new node(10, new Point3D(100, 100, 150), 0));
        graph.addNode(new node(11, new Point3D(135, 125, 130), 0));
        graph.addNode(new node(12, new Point3D(120, 300, 200), 0));
        graph.addNode(new node(13, new Point3D(150, 200, 100), 0));
        graph.addNode(new node(14, new Point3D(75, 250, 250), 0));
        graph.connect(10, 13, 0);
        graph.connect(10, 14, 0);
        graph.connect(11, 10, 0);
        graph.connect(11, 13, 0);
        graph.connect(12, 11, 0);
        graph.connect(13, 14, 1);
        graph.connect(13, 11, 1.5);
        graph.connect(14, 13, 0);
    }

    @Test
    void shortestPathDist() {
    }

    @Test
    void shortestPath() {
    }

    @Test
    void TSP() {
    }

    @Test
    void copy() {
    }
}