package model;

import java.util.HashMap;

public interface Graph<T> {
	
	public void addVertex(T nd);
	public void deleteVertex(T nd);
	public void addEdge(T from, T to, int w);
	public void deleteEdge(T from, T to, int w);
	public String path(T from, T to);
	public String checkConectivity();
	public void dfs(T nd, HashMap<T, Boolean> vis);
}
