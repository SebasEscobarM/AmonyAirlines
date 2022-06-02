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
	
	@Test
	void test1() {
		//Se prueba el Dijkstra en el caso del grafo como lista de vecinos
		setupStage1();
		graph.addVertex("a");
		graph.addVertex("b");
		graph.addVertex("c");
		graph.addEdge("a", "b", 5);
		graph.addEdge("b", "c", 1);
		
		graph.path("a","c");
		
		assertEquals(((GraphLst<String>) graph).getNds().get("c").getDst(),6);
		assertEquals(((GraphLst<String>) graph).getNds().get("c").getPrev().getItem(),"b");
		assertEquals(((GraphLst<String>) graph).getNds().get("b").getPrev().getItem(),"a");
	}
	
	@Test
	void test2() {
		//Se prueba el floyd warshall en el caso de la matriz de adyacencia
		setupStage2();
		graph.addVertex("a");
		graph.addVertex("b");
		graph.addVertex("c");
		graph.addEdge("a", "b", 5);
		graph.addEdge("b", "c", 1);
		
		graph.path("a","c");
		
		assertEquals(((GraphMtrx<String>) graph).getWMtrx().get("a").get("c"),6);
		assertEquals(((GraphMtrx<String>) graph).getAnt().get("a").get("c"),"b");
		assertEquals(((GraphMtrx<String>) graph).getAnt().get("a").get("b"),"a");
	}
	@Test
	void test3() {
		//Grafo Lista de vecinos
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
	void test4() {
		//Grafo Matriz
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
