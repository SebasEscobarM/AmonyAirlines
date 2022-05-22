package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GraphTest {

	private static Graph<String> graph;
	
	public void setupStage1() {
		graph= new Graph<>();
	}
	
	@Test
	void test1() {
		//Se agregan 3 vertices y dos aristas que apuntan a este,
		//se elimina el nodo al que apuntan las dos aristas.
		//Por tanto el resultado esperado es que se elimine el vertice indicado y
		//sus referencias en la lista de vecidos de los otros dos nodos.
		setupStage1();
		graph.addVertex(new Node<String>("a"));
		graph.addVertex(new Node<String>("b"));
		graph.addVertex(new Node<String>("c"));
		graph.addEdge("a", "b");
		graph.addEdge("c", "b");
		graph.deleteVertex("b");
		
		assertEquals(graph.getNds().size(), 2);
		assertEquals(graph.getNds().get("a").getNghbr().size(), 0);
		assertEquals(graph.getNds().get("c").getNghbr().size(), 0);
	}
	
	@Test
	void test2() {
		//Se agregan 2 vertices y un a ariste del "a" al "b",
		//se elimina la arista recien creada.
		//Por tanto el resultado esperado es que se elimine la arista indicada y
		//las listas de vecinos de ambos nodos este vacia.
		setupStage1();
		graph.addVertex(new Node<String>("a"));
		graph.addVertex(new Node<String>("b"));
		graph.addEdge("a", "b");
		graph.deleteEdge("a","b");
		
		assertEquals(graph.getNds().size(), 2);
		assertEquals(graph.getNds().get("a").getNghbr().size(), 0);
		assertEquals(graph.getNds().get("b").getNghbr().size(), 0);
	}

}
