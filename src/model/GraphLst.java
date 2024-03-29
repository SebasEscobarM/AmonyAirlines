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
		//Comprobar si hay camino
		ArrayList<T> p=new ArrayList<>();                                                            		
		boolean cont=true;
		p.add(to);
		do {
			if(nds.get(p.get(p.size()-1)).getPrev()!=null) {
				p.add(nds.get(p.get(p.size()-1)).getPrev().getItem());
			}
			if(nds.get(p.get(p.size()-1)).getPrev()==null) {
				cont=false;
			}
			if(nds.get(p.get(p.size()-1)).getPrev()!=null && nds.get(p.get(p.size()-1)).getPrev().getItem()==from) {
				p.add(from);
				cont=false;
			}
		}while(cont);
		String path="";
		if(!p.isEmpty() && p.get(p.size()-1)==from) {
			path="El vuelo tomara un tiempo de vuelo promedio de: "+nds.get(to).getDst()+"\nLa ruta que se debe tomar es:\n" ;
			for(int i=p.size()-1;i>=0;i--) {
				if(i!=0) {
					path+=p.get(i)+" --> ";
				}else {
					path+=p.get(i);
				}
			}
		}else {
			path="No existe actualmente una ruta posible entre "+ from+" y "+to+".";
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

	@Override
	public String checkConectivity() {
		String ans="Los vuelos actualmente registrados son suficientes para conectar cualquier par de ciudades.";
		HashMap<T, Boolean> vis=new HashMap<>();
		for(T k:nds.keySet()) {
			vis.put(k, false);
		}
		for(T nd: nds.keySet()) {
			vis.replaceAll((ke,itm)-> itm=false);
			dfs(nd, vis);
			for(T k: vis.keySet()) {
				if(!vis.get(k)) {
					ans="Los vuelos actualmente registrados no logran conectar cualquier par de ciudades elegido.";
					return ans;
				}
			}
		}
		return ans;
	}
	
	public void dfs(T nd, HashMap<T, Boolean> vis) {
		if(vis.get(nd)) {
			return;
		}
		vis.replace(nd, true);
		for(Edge<T> e:nds.get(nd).getEdg()) {
			dfs(e.getTo().getItem(),vis);
		}
	}
}
