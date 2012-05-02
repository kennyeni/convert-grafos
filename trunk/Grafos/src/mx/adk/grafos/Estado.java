package mx.adk.grafos;

class Estado extends State{

	public Estado(String id) {
		super(id);
	}

	public String toString() {
		return "S" + id;
	}
}