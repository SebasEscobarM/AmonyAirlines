package model;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphLst<T> implements Graph<T>{
	
	private HashMap<T,Node<T>> nds;
	
	public GraphLst() {
		nds=new HashMap<>();
	}
	
	public void addVertex(T nd) {
		Node<T> nwNd=new Node<>(nd);
		if(nds.containsKey(nd)) {
			return;
		}
		nds.put(nd, nwNd);
	}
	
	public void deleteVertex(T itm) {
		if(nds.isEmpty()) {
			return;
		}
		Node<T> nd=nds.get(itm);
		for(T i:nds.keySet()) {
			ArrayList<Edge<T>> toDel=new ArrayList<>();
			for(Edge<T> e: nds.get(i).getEdg()) {
				if(e.getTo()==nd) {
					toDel.add(e);
				}
			}
			nds.get(i).getEdg().removeAll(toDel);
		}
		nds.remove(itm);
	}
	
	public void addEdge(T from, T to, int w) {
		if(nds.get(from)!=null && nds.get(to)!=null) {
			nds.get(from).getEdg().add(new Edge<T>(nds.get(from), nds.get(to), w));
		}
	}
	
	public void deleteEdge(T from, T nd2, int w) {
		if(nds.get(from)!=null) {
			for(Edge<T> e: nds.get(from).getEdg()) {
				if(e.getTo()==nds.get(nd2) && w==e.getWeight()) {
					nds.get(from).getEdg().remove(e);
					break;
				}
			}
		}
	}
	
	public HashMap<T,Node<T>> getNds(){
		return nds;
	}

	@Override
	public void path(T from, T to) {
		// TODO Auto-generated method stub
		
	}
}
