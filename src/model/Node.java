package model;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {

	private T item;
	private List<Edge<T>> edg;
	private long dst;
	private Node<T> prev;
	
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

	public long getDst() {
		return dst;
	}

	public void setDst(long dst) {
		this.dst = dst;
	}

	public Node<T> getPrev() {
		return prev;
	}

	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}
}
