package dataStructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class DGraph implements graph {
	private HashMap<Integer, node_data> HashNode = new HashMap<>();
	private HashMap<Integer, HashMap<Integer, edge_data>> HashEdge = new HashMap<>();
	private int MC = 0;
	private int NodeSize = 0;
	private int EdgeSize = 0;

	@Override
	public node_data getNode(int key) {
		if (HashNode.get(key) == null) {
			return null;
		}
		return this.HashNode.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		if (HashEdge.get(src).get(dest) == null) {
			return null;
		}
		return this.HashEdge.get(src).get(dest);
	}

	@Override
	public void addNode(node_data n) {
		if (!(HashNode.containsKey(n.getKey()))) {
			HashNode.put(n.getKey(), n);
			MC++;
			NodeSize++;
		} else {
			throw new RuntimeException("The Edge Id is already exist!");
		}

	}

	@Override
	public void connect(int src, int dest, double w) {
		boolean b=true;
		if (src == dest) {
			System.out.println("src and dest are the same");
			b=false;
		}
		if (b||(!(HashNode.get(src) == null) || !(HashNode.get(dest) == null))) {
			if (HashEdge.containsKey(src)) {
				if (HashEdge.get(src).get(dest) != null) {
					throw new RuntimeException("The edge is already exist!");
				} else {
					edge_data edge = new edge(src, dest, w);
					this.HashEdge.get(src).put(dest, edge);
					MC++;
					EdgeSize++;
				}
			} else {
				edge_data edge = new edge(src, dest, w);
				HashEdge.put(src, new HashMap<>());
				this.HashEdge.get(src).put(dest, edge);
				MC++;
				EdgeSize++;
			}
		} else if(b){throw new RuntimeException("src/dest does not exist!");}
	}

	@Override
	public Collection<node_data> getV() {
		return this.HashNode.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		return HashEdge.get(node_id).values();
	}

	@Override
	public node_data removeNode(int key) {
		node_data temp;
		if(HashNode.containsKey(key)){
			temp = this.HashNode.get(key);
		}
		else{return null;}
		if (HashNode.get(key) != null) {
			int x = HashEdge.get(key).size();
			HashEdge.remove(key);
			EdgeSize -= x;
		}
		for (int it: HashEdge.keySet()) {
			if (HashEdge.get(it).containsKey(key)) {
				HashEdge.get(it).remove(key);
				EdgeSize--;
			}
		}
		HashNode.remove(key);// remove the node of the key
		MC++;
		NodeSize--;
		return temp;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		edge_data e;
		if(getEdge(src,dest)!=null){
			e=getEdge(src,dest);
			HashEdge.get(src).remove(dest);
			EdgeSize--;
		}
		else{
			return  null;
		}
		return e;
	}

	@Override
	public int nodeSize() {

		return this.NodeSize;
	}

	@Override
	public int edgeSize() {

		return this.EdgeSize;
	}

	@Override
	public int getMC() {

		return this.MC;
	}
}
