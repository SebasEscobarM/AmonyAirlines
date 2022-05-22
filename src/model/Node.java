package model;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {

	private T item;
	private List<Node<T>> nghbr;
	
	public Node(T item){
		this.item=item;
		this.nghbr=new ArrayList<>(); 
	}
	
	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}
	
	public List<Node<T>> getNghbr() {
		return nghbr;
	}
}
