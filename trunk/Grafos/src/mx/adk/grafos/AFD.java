package mx.adk.grafos;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NavigableSet;
import java.util.TreeMap;

public class AFD {

	@SuppressWarnings("rawtypes")
	Automata and = null;
	@SuppressWarnings("rawtypes")
	Automata<Estado, Union> adf = new Automata<Estado, Union>();
	Object[] estadosAnd;
	TreeMap<String, Estado> estadosAdf = new TreeMap<String, Estado>();
	int size;
	Object[] lenguaje;

	public AFD(Automata<Estado, Union> and, int size, Object[] lenguaje) {
		this.and = and;
		this.size = size;
		this.lenguaje = lenguaje;
		convertir();

	}

	public Automata<Estado, Union> getAdf() {
		return adf;
	}

	public void convertir() {
		@SuppressWarnings("unchecked")
		TreeMap<String, State> estados = and.getEstados();
		NavigableSet<String> bla = estados.descendingKeySet();
		estadosAnd = bla.toArray();
		combinations("", estados.size()); // Inserta estados en el ADF
		adf.setCount(estadosAdf.size());
		adf.setEstados(estadosAdf);
	//	Estado tmp = new Estado("{}");
	//	estadosAdf.put("{}", tmp);
		agregarTransiciones();
	}

	private void agregarTransiciones() {
		TreeMap<String, Estado> estados = adf.getEstados();
		NavigableSet<String> bla = estados.descendingKeySet();
		for (String str : bla) {
			String[] estadosArray = str.split(" ");
			String[] estLenguaje = new String[getSize()];
			for (String s : estadosArray) {
				// System.out.println(s);
				@SuppressWarnings("unchecked")
				LinkedList<Union> transiciones = and.getDestinations(and
						.getEstado(s));
				for (Union u : transiciones) {
					// System.out.println("1");
					for (int i = 0; i < lenguaje.length; i++) {
						// System.out.println("1");
						System.out.println((Character) lenguaje[i]);
						System.out.println(u.decision);
						if (u.decision=='E') {
							LinkedList<Union> transEpsilon= and.getDestinations(and.getEstado(u.destination));
							System.out.println("Entré a las transiciones Epsilon...Procesar");
							continue;
						} else if (((Character) lenguaje[i]).equals(u.decision)) {
							// System.out.println("Entre a la condicion");
							if (estLenguaje[i] == null) {
								estLenguaje[i] = s + " ";
							} else if (!estLenguaje[i].contains(s)) {
								estLenguaje[i] += s + " ";
							}
							/*
							 * if(estLenguaje[i].lastIndexOf(s)==estLenguaje[i].
							 * indexOf(s)) if(s!=null) estLenguaje[i]+=s+" ";
							 * else estLenguaje[i]=s+" ";
							 * 
							 */
						}
					}
					for (int i = 0; i < estLenguaje.length; i++){
						adf.addVertex(estLenguaje[i].charAt(0), adf.getEstado(s), adf.getEstado(estadosArray[i]));
						System.out.print(estLenguaje[i]);
					}
						
					System.out.print("\n");
				}
			}
		}

	}

	public void combinations(String prefix, int length) {
		for (int i = 0; i < prefix.length(); i++) {
			for (int j = i + 1; j < prefix.length(); j++) {
				if (prefix.charAt(i) == prefix.charAt(j)
						|| prefix.charAt(i) < prefix.charAt(j))
					return;
			}
		}
		if (prefix != "" && prefix != " ") {
			String prefijo = "";
			for (int i = 0; i < prefix.length(); i++) {
				prefijo += String.valueOf(estadosAnd[prefix.charAt(i) - 48])
						+ " ";
				// String temp = prefijo.substring(0, length-1);
				// prefijo = temp;
			}
			// System.out.println(prefijo);
			Estado tmp = new Estado(prefijo);
			estadosAdf.put(prefijo, tmp);
		}
		if (prefix.length() < length)
			for (int c = 0; c < length; c++) {
				combinations(prefix + c, length);
			}
		
	}

	public int getSize() {
		return size;
	}

}
