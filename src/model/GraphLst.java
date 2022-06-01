package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

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
	public String path(T from, T to) {
		dijkstra(from);
		ArrayList<T> p=new ArrayList<>();                                                            		
		boolean cont=true;
		p.add(to);
		do {
			p.add(nds.get(p.get(p.size()-1)).getPrev().getItem());
			if(nds.get(p.get(p.size()-1)).getPrev().getItem()==from) {
				p.add(from);
				cont=false;
			}
		}while(cont);
		String path="El vuelo tomara un tiempo de vuelo promedio de: "+nds.get(to).getDst()+"\nLa ruta que se debe tomar es:\n" ;
		for(int i=p.size()-1;i>=0;i--) {
			if(i!=0) {
				path+=p.get(i)+" --> ";
			}else {
				path+=p.get(i);
			}
		}
		return path;
	}
	
	public void dijkstra(T src) {
		nds.get(src).setDst(0);
		PriorityQueue<Node<T>> q=new PriorityQueue<>(new NdComparator<T>());
		for(T nd:nds.keySet()) {
			if(nd!=src) {
				nds.get(nd).setDst(Integer.MAX_VALUE);
			}
			nds.get(nd).setPrev(null);
			q.add(nds.get(nd));
		}
		
		while(!q.isEmpty()) {
			Node<T> u=q.peek();
			for(Edge<T> e:u.getEdg()) {
				if((u.getDst()+e.getWeight())<e.getTo().getDst()) {
					e.getTo().setDst(u.getDst()+e.getWeight());
					e.getTo().setPrev(u);
				}
			}
			q.remove(u);
		}
	}
}
