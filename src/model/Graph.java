package model;

import java.util.HashMap;

public class Graph<T> {
	
	private HashMap<T,Node<T>> nds;
	
	public Graph() {
		nds=new HashMap<>();
	}
	
	public void addVertex(Node<T> nd) {
		if(nds.containsKey(nd.getItem())) {
			return;
		}
		nds.put(nd.getItem(), nd);
	}
	
	public void deleteVertex(T itm) {
		if(nds.isEmpty()) {
			return;
		}
		Node<T> nd=nds.get(itm);
		for(T i:nds.keySet()) {
			nds.get(i).getNghbr().remove(nd);
		}
		nds.remove(itm);
	}
	
	public void addEdge(T nd1, T nd2) {
		if(nds.get(nd1)!=null && nds.get(nd2)!=null) {
			nds.get(nd1).getNghbr().add(nds.get(nd2));
		}
	}
	
	public void deleteEdge(T nd1, T nd2) {
		if(nds.get(nd1)!=null && nds.get(nd2)!=null) {
			nds.get(nd1).getNghbr().remove(nds.get(nd2));
		}
	}
	
	public HashMap<T,Node<T>> getNds(){
		return nds;
	}
}
