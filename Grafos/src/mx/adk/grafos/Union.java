package mx.adk.grafos;

public class Union extends Link {
	
	public char decision;
	public String source;
	public String destination;
	
	public Union(String id, char decision, String source, String Destination){
		super(id);
		this.decision = decision;
		this.source = source;
		this.destination = Destination;
	}
	
	public String toString(){
		return decision+"";
	}
	
	public String getID(){
		return id;
	}
	
	public String getSource(){
		return source;
	}
	
	public String getDestination(){
		return destination;
	}
	
	public char getTransicion(){
		return decision;
	}
}
