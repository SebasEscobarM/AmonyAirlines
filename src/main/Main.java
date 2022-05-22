package main;

import java.util.Scanner;

import model.Graph;
import model.Node;

public class Main {
	
	public static Scanner rd=new Scanner(System.in);
	public static Graph<String> graph;

	public static void main(String[] args) {
		graph=new Graph<>();
		menu();
	}

	public static void menu() {
		boolean continu=true;
		do {
			System.out.println("Menu principal. Seleccione la opcion que desea usar:");
			System.out.println("1. Añadir un vertice.");
			System.out.println("2. Eliminar un vertice.");
			System.out.println("3. Añadir una arista.");
			System.out.println("4. Eliminar una arista.");
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
			case 9:
				continu=false;
				break;
			}
		}while(continu);
	}
	
	private static void deleteEdge() {
		System.out.println("Ingrese la ariste de la forma 'A,B' siendo A donde inicia y B a donde apunta la arista a eliminar:");		
		String in=rd.nextLine();
		String[] vls=in.split(",");
		graph.deleteEdge(vls[0], vls[1]);		
	}

	private static void addEdge() {
		System.out.println("Ingrese la ariste de la forma 'A,B' siendo A donde inicia y B a donde apunta la arista a agregar:");		
		String in=rd.nextLine();
		String[] vls=in.split(",");
		graph.addEdge(vls[0], vls[1]);
	}

	private static void deleteVertex() {
		System.out.println("Ingrese el valor del vertice a agregar:");
		String item=rd.nextLine();
		graph.deleteVertex(item);		
	}

	public static void addVertex() {
		System.out.println("Ingrese el valor del vertice a agregar:");
		String item=rd.nextLine();
		graph.addVertex(new Node<String>(item));
	}

}
