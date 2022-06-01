package model;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {

	private T item;
	private List<Edge<T>> edg;
	
	public Node(T item){
		this.item=item;
		this.edg=new ArrayList<>();
	}
	
	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}
	
	public List<Edge<T>> getEdg() {
		return edg;
	}
}
