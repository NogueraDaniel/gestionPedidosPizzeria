package clases;

import tdasImplementacion.ListaOrdenadaNodos;

public class ListaPizzas extends ListaOrdenadaNodos<Float, Pizza> {

	// Implementacion de metodos abstractos heredados de la clase
	// ListaOrdenadaNodos<?,?>

	@Override
	public int compare(Pizza dato1, Pizza dato2) {

		// Al pedirle a nuestros datos el precio de venta, y devolver estos un float
		// primitivo, hacemos el parse a objetos de tipo Float, para poder invocar al
		// metodo "compareTo()" que nos devolvera si un dato es mayor al otro:
		
		Float parseDato1 = dato1.getPrecioDeVenta();
		Float parseDato2 = dato2.getPrecioDeVenta();

		return parseDato2.compareTo(parseDato1);
	}

	@Override
	public int compareByKey(Float clave, Pizza elemento) {

		Float parseElemento = elemento.getPrecioDeVenta();

		return parseElemento.compareTo(clave);
	}

}
