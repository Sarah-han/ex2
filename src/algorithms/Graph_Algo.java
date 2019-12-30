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
private DGraph graph;
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

	@Override
	public double shortestPathDist(int src, int dest) {
		List<node_data> ans=shortestPath(src,dest);
		return ans.get(ans.size()-1).getWeight();
	}
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
