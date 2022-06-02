package model;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphMtrx<T> implements Graph<T>{
	
	private HashMap<T,HashMap<T, Integer>> mtrx;
	private HashMap<T,HashMap<T, Integer>> wMtrx;
	private HashMap<T,HashMap<T, T>> ant;
	
	public GraphMtrx() {
		mtrx=new HashMap<>();
		wMtrx=new HashMap<>();
		ant=new HashMap<>();
	}
	
	public void addVertex(T nd) {
		mtrx.put(nd, new HashMap<T, Integer>());
		wMtrx.put(nd, new HashMap<T, Integer>());
		ant.put(nd, new HashMap<T, T>());
		
		for(T k:mtrx.keySet()) {
			if(k.equals(nd)) {
				for(T j:mtrx.keySet()) {
					ant.get(k).put(j, k);
					if(j.equals(k)) {
						//0 de la diagonal principal
						mtrx.get(k).put(j, 0);
						wMtrx.get(k).put(j, 0);
					}else {
						//Se pone infinito porque la arista no existe
						mtrx.get(k).put(j, Integer.MAX_VALUE);
						wMtrx.get(k).put(j, Integer.MAX_VALUE);
					}
				}
			}else {
				//Se pone infinito porque la arista no existe
				mtrx.get(k).put(nd, Integer.MAX_VALUE);
				wMtrx.get(k).put(nd, Integer.MAX_VALUE);
				ant.get(k).put(nd, k);
			}
		}
		
		//Se envia a hacer floyd warshall por decidir donde se hace
	}
	
	public void deleteVertex(T nd) {
		mtrx.remove(nd);
		wMtrx.remove(nd);
		ant.remove(nd);
		for(T k:mtrx.keySet()) {
			mtrx.get(k).remove(nd);
			wMtrx.get(k).remove(nd);
			ant.get(k).remove(nd);
		}
	}
	
	public void addEdge(T from, T to, int w) {
		mtrx.get(from).replace(to, w);
	}
	
	public void deleteEdge(T from, T to, int w) {
		if(mtrx.get(from).get(to)==w) {
			mtrx.get(from).replace(to, Integer.MAX_VALUE);
		}
	}

	public HashMap<T, HashMap<T, Integer>> getMtrx() {
		return mtrx;
	}

	public void setMtrx(HashMap<T, HashMap<T, Integer>> mtrx) {
		this.mtrx = mtrx;
	}

	@Override
	public String path(T from, T to) {
		floydWarshall();
		String path="";
		if(wMtrx.get(from).get(to)==Integer.MAX_VALUE) {
			path="No existe actualmente una ruta posible entre "+ from+" y "+to+".";
		}else {
			ArrayList<T> p=new ArrayList<>();                                                            		
			p.add(to);
			boolean cont=true;
//			Leer y sacar el camino
			do{
				p.add(ant.get(from).get(p.get(p.size()-1)));
				if(ant.get(from).get(p.get(p.size()-1))==from) {
					if(p.get(p.size()-1)!=from) {
						p.add(from);
					}
					cont=false;
				}
			}while(cont);
			path="El vuelo tomara un tiempo de vuelo promedio de: "+wMtrx.get(from).get(to)+"\nLa ruta que se debe tomar es:\n" ;
			for(int i=p.size()-1;i>=0;i--) {
				if(i!=0) {
					path+=p.get(i)+" --> ";
				}else {
					path+=p.get(i);
				}
			}
		}
		return path;

	}
	
	public void floydWarshall() {
		wMtrx=mtrx;
		for(T k:ant.keySet()) {
			for(T j: ant.keySet()) {
				ant.get(k).replace(j, k);
			}
		}
		
		for(T k:ant.keySet()) {
			for(T i:ant.keySet()) {
				for(T j:ant.keySet()) {
					if(wMtrx.get(i).get(k)!=Integer.MAX_VALUE && wMtrx.get(k).get(j)!=Integer.MAX_VALUE) {
						if(wMtrx.get(i).get(k)+wMtrx.get(k).get(j)<wMtrx.get(i).get(j)) {
							wMtrx.get(i).replace(j, wMtrx.get(i).get(k)+wMtrx.get(k).get(j));
							ant.get(i).replace(j, ant.get(k).get(j));
						}
					}
				}
			}
		}
	}
	
	
}
