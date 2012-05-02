package mx.adk.grafos;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.table.DefaultTableModel;

public class Procesa {
	
	Automata<Estado, Union> and;
	TreeMap<Integer, String> decoRows = new TreeMap();
	TreeMap<Integer, Character> decoCols = new TreeMap();

	public Procesa(TreeSet<String> estados, TreeSet<Character> lenguaje, DefaultTableModel transiciones, String qInicial, String[] qFinales){
		and = new Automata<Estado, Union>();
		for (String edo : estados) {
			Estado edoTmp = new Estado(edo);
			and.addState(edoTmp);
		}
		decodificaRows(transiciones);
		decodificaCols(transiciones);
		agregaTransiciones(transiciones);
	}

	private void decodificaRows(DefaultTableModel transiciones) {
		int count = transiciones.getRowCount();
		for(int i=0;i<count;i++){
			decoRows.put(i, transiciones.getValueAt(i, 0).toString());
		}
	}
	
	private void decodificaCols(DefaultTableModel transiciones) {
		int count = transiciones.getColumnCount();
		for(int i=0;i<count;i++){
			decoCols.put(i, transiciones.getColumnName(i).charAt(0));
		}
		
	}

	private void agregaTransiciones(DefaultTableModel transiciones) {
		int rowCount = transiciones.getRowCount();
		int colCount = transiciones.getColumnCount();
		
		
		
	}
	
}
