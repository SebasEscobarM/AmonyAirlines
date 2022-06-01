package model;

public interface Graph<T> {
	
	public void addVertex(T nd);
	public void deleteVertex(T nd);
	public void addEdge(T from, T to, int w);
	public void deleteEdge(T from, T to, int w);
	public void path(T from, T to);
}
