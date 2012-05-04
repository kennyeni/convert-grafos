package mx.adk.grafos;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.collections15.Transformer;

import com.sun.tools.javac.util.Convert;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse.Mode;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

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
		and.setEstadoInicial(and.getEstado(qInicial));
		and.setEstadoFinal(qFinales);
		Simulacion sim = new Simulacion(and);
		sim.print(and); //Printea no determista
		AFD afd = new AFD(and, lenguaje.size(), lenguaje.toArray());
		//print(afd.getAdf());
		sim.simula();
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
		for(int row=0;row<rowCount;row++){
			for(int col=1;col<colCount;col++){
				Object obj = transiciones.getValueAt(row, col);
				String trans = null;
				if(obj==null)
					trans = "";
				else
					trans = obj.toString();
				
				String sinEspacio = trans.replaceAll(" ", "");
				String[] dividido = sinEspacio.split(",");
				Estado inicio = and.getEstado(decoRows.get(row));
				for (String str : dividido) {
					if(!(str==""||str==null)){
						Estado destino = and.getEstado(str);
						if(destino!=null)
							and.addVertex(decoCols.get(col), inicio, destino);
					}
				}
			}
		}
		
		
	}
	
}
