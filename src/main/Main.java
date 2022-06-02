package main;

import java.util.Scanner;

import model.Edge;
import model.Graph;
import model.GraphLst;
import model.GraphMtrx;

public class Main {
	
	public static Scanner rd=new Scanner(System.in);
	public static Graph<String> graph;
	public static boolean mtrxGraph=false;

	public static void main(String[] args) {
		boolean cont=true;
		while(cont) {
			System.out.println("Seleccione el tipo de grafo con el que desea iniciar el sistema. Seleccione:");
			System.out.println("1. Listas de vecinos.");
			System.out.println("2. Matriz de adyacencia.");
			int selec=Integer.parseInt(rd.nextLine());
			if(selec==1) {
				cont=false;
				mtrxGraph=false;
				graph=new GraphLst<>();
			}else if(selec==2) {
				cont=false;
				mtrxGraph=true;
				graph=new GraphMtrx<>();
			}else {
				System.out.println("Elija una opcion valida");
			}
		}
		menu();
	}

	public static void menu() {
		boolean continu=true;
		do {
			System.out.println("Menu principal. Seleccione la opcion que desea usar:");
			System.out.println("1. Añadir una ciudad.");
			System.out.println("2. Eliminar una ciudad.");
			System.out.println("3. Añadir un taryecto directo.");
			System.out.println("4. Eliminar un taryecto directo.");
			System.out.println("5. Cambiar represetnacion del grafo.");
			System.out.println("6. Ver ruta de una ciudad a otra.");
			System.out.println("7. Ver tour completo desde una ciudad.");
			System.out.println("9. Salir.");
			int menu=Integer.parseInt(rd.nextLine());
			switch(menu) {
			case 1:
				addVertex();
				break;
			case 2:
				deleteVertex();
				break;
			case 3:
				addEdge();
				break;
			case 4:
				deleteEdge();
				break;
			case 5:
				changeGraphType();
				break;
			case 6:
				showPath();
				break;
			case 7:
				showCycle();
				break;
			case 9:
				continu=false;
				break;
			}
		}while(continu);
	}
	
	public static void showPath() {
		System.out.println("Ingrese la ariste de la forma 'A,B' siendo A la ciudad de origen y B la ciudad de destino:");		
		String in=rd.nextLine();
		String[] vls=in.split(",");
		System.out.println(graph.path(vls[0],vls[1]));
	}
	
	public static void showCycle() {
		System.out.println("Ingrese la ciudad de origen del tour:");		
		String in=rd.nextLine();
		if(mtrxGraph) {
			
		}else {
			
		}
	}
	
	private static void changeGraphType() {
		if(mtrxGraph) {
			mtrxGraph=false;
			GraphMtrx<String> grM=(GraphMtrx<String>) graph;
			Graph<String> g=new GraphLst<>();
			for(String k: grM.getMtrx().keySet()) {
				g.addVertex(k);
			}
			for(String from: grM.getMtrx().keySet()) {
				for(String to: grM.getMtrx().get(from).keySet()) {
					if(!from.equals(to) && grM.getMtrx().get(from).get(to)!=Integer.MAX_VALUE) {
						g.addEdge(from, to, grM.getMtrx().get(from).get(to));
					}
				}
			}
			graph=g;
		}else {
			mtrxGraph=true;
			GraphLst<String> grL=(GraphLst<String>) graph;
			Graph<String> g=new GraphMtrx<>();
			for(String k:grL.getNds().keySet()) {
				g.addVertex(k);
			}
			
			for(String from:grL.getNds().keySet()) {
				for(Edge<String> e:grL.getNds().get(from).getEdg()) {
					g.addEdge(from, e.getTo().getItem(), e.getWeight());
				}
			}
			graph=g;
		}
	}
	
	private static void deleteEdge() {
		System.out.println("Ingrese el trayecto directo de la forma 'A,B' siendo A de donde parte y B a donde llega el vuelo a eliminar:");		
		String in=rd.nextLine();
		String[] vls=in.split(",");
		System.out.println("Ingrese el tiempo de duracion promedio del vuelo a eliminar:");
		int w=Integer.parseInt(rd.nextLine());
		graph.deleteEdge(vls[0], vls[1], w);	
	}

	private static void addEdge() {
		System.out.println("Ingrese el trayecto directo de la forma 'A,B' siendo A de donde parte y B a donde llega el vuelo a agregar:");		
		String in=rd.nextLine();
		String[] vls=in.split(",");
		System.out.println("Ingrese el tiempo de duracion promedio del vuelo a agregar:");
		int w=Integer.parseInt(rd.nextLine());
		graph.addEdge(vls[0], vls[1], w);
	}

	private static void deleteVertex() {
		System.out.println("Ingrese el nombre de la ciudad a eliminar:");
		String item=rd.nextLine();
		graph.deleteVertex(item);		
	}

	public static void addVertex() {
		System.out.println("Ingrese el nombre de la ciudad a agregar:");
		String item=rd.nextLine();
		graph.addVertex(item);
	}

}
