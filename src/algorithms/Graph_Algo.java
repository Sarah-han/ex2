package algorithms;
import java.io.*;
import java.util.*;

import dataStructure.*;

import javax.swing.text.html.HTMLDocument;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{
	/**
	 * private data types of the class
	 * DGraph graph
	 */
private DGraph graph;
	/**
	 * Compute a deep copy of this graph.
	 * @return
	 */
	@Override
	public void init(graph g) {
		graph=new DGraph();
		for(node_data nodes : g.getV()){
			graph.addNode(nodes);
			try {
				for (edge_data edges : g.getE(nodes.getKey())) {
					graph.connect(edges.getSrc(), edges.getDest(), edges.getWeight());
				}
			}
			catch (Exception e){
				//this node has no edges, the graph is still being initialized...
			}
		}
	}
	/**
	 * Init a graph from file
	 * @param file_name
	 */
	@Override
	public void init(String file_name) {
		graph=new DGraph();
		try
		{
			FileInputStream file = new FileInputStream(file_name);
			ObjectInputStream in = new ObjectInputStream(file);
			graph = (DGraph) in.readObject();
			in.close();
			file.close();
			System.out.println("Graph has been deserialized");
		}
		catch(IOException ex)
		{
			System.out.println("IOException is caught");
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("ClassNotFoundException is caught");
		}

	}
	/** Saves the graph to a file.
	 *
	 * @param file_name
	 */
	@Override
	public void save(String file_name) {
		try
		{
			FileOutputStream file = new FileOutputStream(file_name);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(graph);
			out.close();
			file.close();
			System.out.println("Graph has been serialized");
		}
		catch(IOException ex)
		{
			System.out.println("IOException is caught");
		}
	}
	/**
	 * Returns true if and only if (iff) there is a valid path from EVREY node to each
	 * other node.
	 * NOTE: assume directional graph - a valid path (a-->b) does NOT imply a valid path (b-->a).
	 * the algorithm the function is based on is https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
	 * take the start node and enters him and his neighbor to a Linked List.
	 * remove the peek node and enters the neighbor of the first in the list.
	 * the purpose is to close a circle with the first node (we save his data).
	 * if a circle i complete the graph is connected.
	 * else not.
	 * @return
	 *
	 */
	@Override
	public boolean isConnected() {
		int conutTag = 0;
		boolean NullSrcNode = true;
		for (node_data nd : graph.getV()) {
			nd.setTag(0);
		}
		Queue<Integer> q = new LinkedList<>();
		node_data first = graph.getV().iterator().next();
		q.add(first.getKey());
		while (!q.isEmpty()) {
			int peek = q.peek();
			try {
				for (edge_data e : graph.getE(peek)) {
					if (graph.getNode(e.getDest()).getTag() == 0) {
						graph.getNode(e.getDest()).setTag(1);
						conutTag++;
						q.add(e.getDest());
					}
				}
				q.poll();
			} catch (Exception e) {
				//this node has no edges
				NullSrcNode = false;
			}
		}
		if (conutTag == graph.nodeSize() && NullSrcNode) {
			return true;
		}
		return false;
	}
	/**
	 * returns the length of the shortest path between src to dest
	 * @param src - start node
	 * @param dest - end (target) node
	 * use the shortestPath function and compute the weight.
	 * if not found return -1.
	 * @return
	 */
	@Override
	public double shortestPathDist(int src, int dest) {
		List<node_data> ans=shortestPath(src,dest);
		return ans.get(ans.size()-1).getWeight();
	}

	/**
	 * returns the the shortest path between src to dest - as an ordered List of nodes:
	 * src--> n1-->n2-->...dest
	 * see: https://en.wikipedia.org/wiki/Shortest_path_problem
	 * @param src - start node
	 * @param dest - end (target) node
	 * we use the same idea of the isConnected() function for visiting all nodes in the graph.
	 * while visiting each node we update the weight and save which node gave us the weight.
	 * after visiting all the nodes we go to the dest, enter the arr who gave him the weight.
	 * go to this node and enter the arr who gave him the weight and so on until the src.
	 * we revers the order to return the list in the right order.
	 * if way is not found return null.
	 * @return
	 */
	@Override
	public List<node_data> shortestPath(int src, int dest) {
		List<node_data> Path=new LinkedList<>();
		Queue<Integer> q=new LinkedList<>();
		for (node_data nd:graph.getV()) {
			nd.setTag(0);
			nd.setWeight(Integer.MAX_VALUE);
		}
		graph.getNode(src).setTag(src);
		graph.getNode(src).setWeight(0);
		q.add(src);
		while(!q.isEmpty()){
			int peek=q.peek();
			try {
				for (edge_data e : graph.getE(peek)) {
					if (graph.getNode(e.getDest()).getWeight() > e.getWeight() + graph.getNode(e.getSrc()).getWeight()) { //check if edge+node we came from weight is less then our node
						graph.getNode(e.getDest()).setTag(peek); //changing tag to the node we came from
						double RecentWeight = graph.getNode(e.getSrc()).getWeight(); //recent node weight
						graph.getNode(e.getDest()).setWeight(RecentWeight + e.getWeight()); //changing node weight to recent node weight+edge weight
						q.add(e.getDest());
					}
				}
				q.poll();
			}
			catch(Exception e) { q.poll(); }
		}
		if(graph.getNode(dest).getTag()==0&&graph.getNode(dest).getWeight()==Integer.MAX_VALUE){
			return null;
		}
		Path.add(graph.getNode(dest));
		int tempKey=graph.getNode(dest).getTag();
		while(tempKey!=src) {
			Path.add(graph.getNode(tempKey));
			tempKey=graph.getNode(tempKey).getTag();
		}
		Path.add(graph.getNode(src));
		LinkedList<node_data> ans=new LinkedList<>();
		for (int i = Path.size()-1; i >= 0; i--) {
			ans.add(Path.get(i));
		}
		return ans;
	}
//******************************************************************************
	@Override
	public List<node_data> TSP(List<Integer> targets) {
		List<node_data> ans=new LinkedList<>();
		node_data temp=graph.getNode(targets.get(0));
		ans.add(temp);
		if(isConnected()){
			while(ans.size()!=targets.size()){
				double MinLengthNodeLength=Integer.MAX_VALUE;
				int MinLengthNode=0;
				for (node_data nd:graph.getV()) {
					if (shortestPathDist(temp.getKey(), nd.getKey()) < MinLengthNodeLength&&nd.getKey()!=temp.getKey()&&targets.contains(nd.getKey())&&!ans.contains(nd)) {
						MinLengthNode = nd.getKey();
						MinLengthNodeLength = nd.getWeight();
					}
				}
				ans.add(graph.getNode(MinLengthNode));
				temp=graph.getNode(MinLengthNode);
			}
			/*for (int i = 0; i < ans.size()-1; i++) {
				if (ans.get(i).getKey() == ans.get(i+1).getKey()) {
					ans.remove(i + 1);
				}
			}*/
		}
		return ans;
	}
	/**
	 * Compute a deep copy of this graph.
	 * @return
	 */
	@Override
	public graph copy() {
		DGraph graphcopy=new DGraph();
		Collection<node_data> nodescopy=graph.getV();
		for (node_data ND:nodescopy) {
			node_data temp=new node((node)ND);
			graphcopy.addNode(temp);
		}
		for (node_data ND:nodescopy) {
			Collection<edge_data> edgescopy = graph.getE(ND.getKey());
			try {
				for (edge_data ED : edgescopy) {
					graphcopy.connect(ED.getSrc(), ED.getDest(), ED.getWeight());
				}
			}
			catch (Exception e){
				//this node has no edges, the graph is still being initialized...
			}
		}
		return graphcopy;
	}
}
