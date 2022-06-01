package model;

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
		for(T k:mtrx.keySet()) {
			if(k.equals(nd)) {
				for(T j:mtrx.keySet()) {
					if(j.equals(k)) {
						//0 de la diagonal principal
						mtrx.get(k).put(j, 0);
					}else {
						//Se pone infinito porque la arista no existe
						mtrx.get(k).put(j, Integer.MAX_VALUE);
					}
				}
			}else {
				//Se pone infinito porque la arista no existe
				mtrx.get(k).put(nd, Integer.MAX_VALUE);
			}
		}
		
		wMtrx.put(nd, new HashMap<T, Integer>());
		for(T k:wMtrx.keySet()) {
			if(k.equals(nd)) {
				for(T j:wMtrx.keySet()) {
					if(j.equals(k)) {
						//0 de la diagonal principal
						wMtrx.get(k).put(j, 0);
					}else {
						//Se pone infinito porque la arista no existe
						wMtrx.get(k).put(j, Integer.MAX_VALUE);
					}
				}
			}else {
				//Se pone infinito porque la arista no existe
				mtrx.get(k).put(nd, Integer.MAX_VALUE);
			}
		}
		
		ant.put(nd, new HashMap<T, T>());
	}
	
	public void deleteVertex(T nd) {
		mtrx.remove(nd);
		for(T k:mtrx.keySet()) {
			mtrx.get(k).remove(nd);
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
	
	
}
