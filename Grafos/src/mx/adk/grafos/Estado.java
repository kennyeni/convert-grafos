package mx.adk.grafos;

class Estado extends State{

	public Estado(double weight, double capacity, String id) {
		super(id);
	}

	public String toString() {
		return "S" + id;
	}
}