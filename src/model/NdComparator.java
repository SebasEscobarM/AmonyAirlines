package model;

import java.util.Comparator;

public class NdComparator<T> implements Comparator<Node<T>>{

	@Override
	public int compare(Node<T> arg0, Node<T> arg1) {
		return (int) (arg0.getDst()-arg1.getDst());

	}

}
