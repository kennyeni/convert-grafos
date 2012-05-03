package mx.adk.grafos;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeMap;

public class AFD {
	
	Automata and = null;
	Automata adf = new Automata<State, Union>();
	Object[] estadosAnd;
	TreeMap<String, State> estadosAdf = new TreeMap<String, State>();

	public AFD(Automata<Estado, Union> and) {
		this.and = and;
		convertir();
		
	}
	
	public void convertir(){
		TreeMap<String, State> estados = and.getEstados();
		NavigableSet<String> bla = estados.descendingKeySet();
		estadosAnd = bla.toArray();
		combinations("", estados.size()); //Inserta estados en el ADF
		adf.setCount(estadosAdf.size());
	}
	
	public void combinations(String prefix, int length) {
		for(int i=0;i<prefix.length();i++){
			for(int j=i+1;j<prefix.length();j++){
				if(prefix.charAt(i)==prefix.charAt(j)||prefix.charAt(i)<prefix.charAt(j))
					return;
			 }
		}
		if(prefix!=""){
    		String prefijo = "";
    		for(int i=0;i<prefix.length();i++){
    			prefijo += String.valueOf(estadosAnd[prefix.charAt(i)-48]);
    		}
			System.out.println(prefijo);
			Estado tmp = new Estado(prefijo);
			estadosAdf.put(prefijo, tmp);
		}
	    if (prefix.length() < length)
	        for (int c = 0; c < length; c++){
	            	combinations(prefix + c, length);
			}
	}
	
	

}
