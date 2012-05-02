package mx.adk.grafos;

public class Union extends Link {
	
	public char decision;
	
	public Union(String id, char decision){
		super(id);
		this.decision = decision;
	}
	
	public String toString(){
		return decision+"";
	}
	
	public String getID(){
		return id;
	}
}
