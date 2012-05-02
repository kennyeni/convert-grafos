package mx.adk.grafos;

import java.awt.EventQueue;
import java.util.*;
import java.util.regex.Pattern;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
		
	}

}
