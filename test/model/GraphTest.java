package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GraphTest {

	private static Graph<String> graph;
	
	public void setupStage1() {
		graph= new GraphLst<>();
	}
	
	public void setupStage2() {
		graph= new GraphMtrx<>();
	}
	
//	@Test
//	void test1() {
//		setupStage1();
//		graph.addVertex("a");
//		graph.addVertex("b");
//		graph.addVertex("c");
//		graph.addVertex("d");
//		graph.addEdge("a", "b", 5);
//		graph.addEdge("b", "d", 2);
//		graph.addEdge("a", "c", 1);
//		graph.addEdge("c", "d", 6);
//		graph.addEdge("c", "b", 4);
//		graph.addEdge("d", "a", 7);
//		
//		System.out.println(graph.path("b","c"));
//		
//		assertEquals(graph.path("b","c"));
//	}
	
	@Test
	void test2() {
		//Grafo Lista de vecinos
		//Se agregan 2 vertices y un a ariste del "a" al "b",
		//se elimina la arista recien creada.
		//Por tanto el resultado esperado es que se elimine la arista indicada y
		//las listas de vecinos de ambos nodos este vacia.
		setupStage1();
		graph.addVertex("a");
		graph.addVertex("b");
		graph.addVertex("c");
		graph.addEdge("a", "b", 10);
		graph.deleteEdge("a","b", 10);
		graph.deleteVertex("c");
		
		assertEquals(((GraphLst<String>) graph).getNds().size(), 2);
		assertEquals(((GraphLst<String>) graph).getNds().get("a").getEdg().size(), 0);
		assertEquals(((GraphLst<String>) graph).getNds().get("b").getEdg().size(), 0);
	}
	
	@Test
	void test3() {
		//Grafo Matriz
		//Se agregan 2 vertices y un a ariste del "a" al "b",
		//se elimina la arista recien creada.
		//Por tanto el resultado esperado es que se elimine la arista indicada y
		//las listas de vecinos de ambos nodos este vacia.
		setupStage2();
		graph.addVertex("a");
		graph.addVertex("b");
		graph.addVertex("c");
		graph.addEdge("a", "b", 10);
		graph.deleteEdge("a","b", 10);
		graph.deleteVertex("c");
		
		assertEquals(((GraphMtrx<String>) graph).getMtrx().size(), 2);
		assertEquals(((GraphMtrx<String>) graph).getMtrx().get("a").get("b"), Integer.MAX_VALUE);
		assertEquals(((GraphMtrx<String>) graph).getMtrx().get("b").get("a"), Integer.MAX_VALUE);
	}

}
