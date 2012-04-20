package mx.adk.grafos;

import java.awt.EventQueue;
import java.util.*;
import java.util.regex.Pattern;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Automata<Estado, Vertice> g = new Automata<Estado, Vertice>();
		try{
			//leerGrafo(g);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						GUI frame = new GUI();
						frame.setVisible(true);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			});
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("Se inserto un grafo incorrecto.");
			return;
		}
		//Print.printGrafo(g);
	}

	private static void leerGrafo(Automata<Estado, Vertice> g) throws Exception {
		System.out.println("Introduzca la def formal del grafo: ({estados}, {symbols}, q1, qf):");
		Scanner scan = new Scanner(System.in);
		String defFormal = scan.nextLine();
		String separador = (Pattern.compile("\\{|\\}\\,")).pattern();
		String pedazos[] = defFormal.split(separador);
		if(pedazos.length<4)
			throw new Exception();
		separador = (Pattern.compile("{|}")).pattern();
		String estados[] = pedazos[0].split(separador);
		String simbolos[] = pedazos[1].split(separador);
	}

}
