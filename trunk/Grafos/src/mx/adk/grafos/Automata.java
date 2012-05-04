package mx.adk.grafos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NavigableMap;
import java.util.TreeMap;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

public class Automata<V extends State, E extends Union> {
	
	private double count;
	private TreeMap<String, V> estados;
	private TreeMap<String, Union> links;
	private V estadoInicial;
	private LinkedList<V> estadosFinales;
	private V currNode;
	private DirectedSparseMultigraph<V, Union> grafo = new DirectedSparseMultigraph<V, Union>();
	private int unionCounter = 0 ;
	
	public Automata(){
		super();
		estados = new TreeMap<String, V>();
		links = new TreeMap<String, Union>();
		this.count = 0;
	}
	
	public double getCount(){
		return count;
	}
	
	public boolean isDestination(V estado){
		return grafo.inDegree(estado) > 0 ? true : false;
	}
	
	public TreeMap<String, V> getEstados() {
		return estados;
	}

	public void setCount(int c){
		count = (double)c;
	}

	public TreeMap<String, Union> getLinks() {
		return links;
	}

	public V getCurrNode(){
		return currNode;
	}


	public V getEstadoInicial() {
		return estadoInicial;
	}

	public void setEstadoInicial(V estadoInicial) {
		this.estadoInicial = estadoInicial;
		currNode = estadoInicial;
	}

	public LinkedList<V> getEstadoFinal() {
		return estadosFinales;
	}

	public void setEstadoFinal(String[] estadoFinal) {
		estadosFinales = new LinkedList();
		for(int i=0;i<estadoFinal.length;i++){
			estadosFinales.add(this.getEstado(estadoFinal[i]));
		}
	}
	
	public V getEstado(String id){
		return estados.get(id);
	}
	
	public void setEstados(TreeMap<String, V> e){
		estados = e;
		NavigableMap<String, V> bla = e.descendingMap();
		LinkedList<V> list = new LinkedList<V>(e.values());
		for(V v:list){
			addState(v);
		}
	}
	
	public void addState(V state){
		estados.put(state.getID(), state);
		grafo.addVertex(state);
		count++;
	}
	
	public DirectedSparseMultigraph<V, Union> getGraph(){
		return grafo;
	}
	
	public LinkedList<Union> getDestinations(V estado){
		LinkedList<Union> lista = new LinkedList<Union>(grafo.getEdges());
		LinkedList<Union> listaReal = new LinkedList<Union>();
		for(Union un : lista){
			if(un.getSource().equals(estado.getID())){
				listaReal.add(un);
			}
		}
		return listaReal;
	}
	
	public void addVertex(char character, V inicio, V destino){
		State bla;
		Union tmpU = new Union(String.valueOf(unionCounter++), character, inicio.getID(), destino.getID());
		links.put(tmpU.getID(), tmpU);
		grafo.addEdge(tmpU, inicio, destino, EdgeType.DIRECTED);
	}
	
	public boolean terminar(){
		return estadosFinales.contains(currNode);
	}

	public V next(char letra) {
		LinkedList<Union> opciones = getDestinations(currNode);
		for(Union opcion: opciones){
			if(opcion.getTransicion()==letra){
				currNode = this.getEstado(opcion.getDestination());
				break;
			}
		}
		return currNode;
	}

}
