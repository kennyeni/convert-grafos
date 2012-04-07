package mx.adk.grafos;

public class Vertice extends Link {
	public Vertice(String id){
		super(id);
	}
	
	public String toString(){
		return "V"+id;
	}
}
