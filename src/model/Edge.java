package model;

public class Edge<T> {
	
	private Node<T> from;
	private Node<T> to;
	private int weight;
	
	public Edge(Node<T> from, Node<T> to, int weight) {
		this.from=from;
		this.to=to;
		this.weight=weight;
	}

	public Node<T> getFrom() {
		return from;
	}

	public void setFrom(Node<T> from) {
		this.from = from;
	}

	public Node<T> getTo() {
		return to;
	}

	public void setTo(Node<T> to) {
		this.to = to;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
}
