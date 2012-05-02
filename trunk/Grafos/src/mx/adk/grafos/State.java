package mx.adk.grafos;

public abstract class State {
	protected String id;
	
	public State(String id) {
		this.id = id;
	}
	
	public String getID(){
		return id;
	}
}
