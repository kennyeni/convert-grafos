package mx.adk.grafos;

import java.util.ArrayList;
import java.util.TreeMap;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

public class Automata<V extends State, E extends Union> {
	
	private int count;
	private TreeMap<String, V> estados;
	private TreeMap<String, Union> links;
	private V estadoInicial;
	private V estadoFinal;
	private V currNode;
	private DirectedSparseMultigraph<V, Union> grafo = new DirectedSparseMultigraph<V, Union>();
	private int unionCounter = 0 ;
	
	public Automata(){
		super();
		estados = new TreeMap<String, V>();
		links = new TreeMap<String, Union>();
		this.count = 0;
	}
	
	public V getEstadoInicial() {
		return estadoInicial;
	}

	public void setEstadoInicial(V estadoInicial) {
		this.estadoInicial = estadoInicial;
		currNode = estadoInicial;
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
	
	public V getEstado(String id){
		return estados.get(id);
	}
	
	public void addState(V state){
		estados.put(state.getID(), state);
		grafo.addVertex(state);
		count++;
	}
	
	public DirectedSparseMultigraph<V, Union> getGraph(){
		return grafo;
	}
	
	public void addVertex(char character, V inicio, V destino){
		State bla;
		Union tmpU = new Union(String.valueOf(unionCounter++), character);
		links.put(tmpU.getID(), tmpU);
		grafo.addEdge(tmpU, inicio, destino, EdgeType.DIRECTED);
	}

}
