package mx.adk.grafos;

import java.util.ArrayList;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

public class Automata<V extends State, E extends Link> extends DirectedSparseMultigraph<V, E> {
	
	private int count;
	private ArrayList<V> estados;
	private ArrayList<E> vertices;
	private V estadoInicial;
	private V estadoFinal;
	private V currNode;
	
	public Automata(){
		super();
		estados = new ArrayList<V>();
		vertices = new ArrayList<E>();
		this.count = 0;
		currNode = estadoInicial;
	}
	
	public V getEstadoInicial() {
		return estadoInicial;
	}

	public void setEstadoInicial(V estadoInicial) {
		this.estadoInicial = estadoInicial;
	}

	public V getEstadoFinal() {
		return estadoFinal;
	}

	public void setEstadoFinal(V estadoFinal) {
		this.estadoFinal = estadoFinal;
	}


	public int getNodeCount(){
		return count;
	}
	
	public void addState(V state){
		estados.add(state);
	}
	
	public void addVertex(E link, V inicio, V destino){
		super.addEdge(link, inicio, destino, EdgeType.DIRECTED);
	}

}
