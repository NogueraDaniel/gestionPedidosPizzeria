package clases;

import tdasImplementacion.PilaNodos;
import tdasInterfaces.Pila;

public class Pizzeria implements Mostrable {

	// MENSAJES DE ERROR ESTATICOS
	private static final String MSG_ERROR_NOMBRE = "El nombre de la pizzeria no puede ser nulo o estar vacio";
	private static final String MSG_PIZZA_TOPPINGS_NULO = "No se pudo fabricar Pizza o Topping nulo.";
	private static final String MSG_TOPPINGS = "Error de parámetros incorporando toppings";

	// MENSAJES ESTATICOS PARA UTILIZAR EN SALIDAS CON PRINTF
	private static final String MSG_TOTALES = "La venta total fue: $%8.2f\n";
	private static final String MSG_CANTIDADES = "Se han fabricado: %d Tradicionales, %d Especiales y %d Rectangulares\n";

	// ATRIBUTOS
	private String nombre;
	private int[][] cantToppings; // MATRIZ QUE INDICA LA CANTIDAD DE TOPPINGS QUE POSEEMOS POR TIPO DE PIZA
	private int[] cantTiposPizzas;
	private Pila<IllegalArgumentException> errores; // PILA DE ERRORES CON VARIABLE REFERENCIADA EN LA INTERFAZ "PILA"
	private ListaPizzas listaPizzas; // LISTA ORDENADA DE PIZZAS ORDENADAS DE MAYOR A MENOR

	// CONSTRUCTOR
	public Pizzeria(String nombre) {

		setNombre(nombre);

		// CREO LA MATRIZ DE TOPPINGS CON LOS DATOS QUE ME DEVUELVEN LOS ENUMS:
		// values().length me devuelve primero un array del enum, y luego su tamaño a
		// traves del atributo length del array.
		// Por lo que estoy creando una matriz de cantidad de tipos de pizza por
		// cantidad de tipos de toppings
		this.cantToppings = new int[TipoPizza.values().length][Topping.values().length];

		// CREO UNA INSTANCIA DE LA IMPLEMENTACION DE PILA NODOS
		this.errores = new PilaNodos<IllegalArgumentException>();

		this.listaPizzas = new ListaPizzas();

		this.cantTiposPizzas = new int[TipoPizza.values().length];

	}

	// SETTERS CON MANEJO DE EXCEPCIONES
	private void setNombre(String nombre) {

		if (nombre == null || nombre.isBlank()) {

			throw new IllegalArgumentException(MSG_ERROR_NOMBRE);
		}
		this.nombre = nombre;
	}

	// METODO QUE INCORPORA TOPPINGS EN LA MATRIZ, SEGUN EL TIPO DE PIZZA
	public void incorporarTopping(TipoPizza tipoPizza, Topping topping, int cantidad) {

		// BLOQUE DE MANEJO DE EXCEPCIONES
		try {

			if (tipoPizza == null) {

				throw new IllegalArgumentException(MSG_TOPPINGS);

			}

			if (topping == null) {

				throw new IllegalArgumentException(MSG_TOPPINGS);

			}

			if (cantidad <= 0) {

				throw new IllegalArgumentException(MSG_TOPPINGS);

			}

			// AUMENTO LA CANTIDAD DEL TOPPING PARA EL TIPO DE PIZZA, UTILIZANDO EL METODO
			// ordinal() QUE ME DEVUELVE LAS POSICIONES DE LOS ENUMS
			cantToppings[tipoPizza.ordinal()][topping.ordinal()] += cantidad;

		} catch (IllegalArgumentException e) {

			System.out.println(e.getMessage());

		}
	}

	// METODO PARA INGRESAR PEDIDOS
	public void ingresarPedido(Pizza p, Topping topping) {

		// Si el topping o la pizza vienen null, arrojo la excepcion y la guardo en mi
		// pila de excepciones que utilizare al final del test
		if (topping == null || p == null) {

			IllegalArgumentException e = new IllegalArgumentException(MSG_PIZZA_TOPPINGS_NULO);

			errores.push(e);

			throw e;
		}

		// Obtengo la posicion en la matriz a traves del posicionamiento directo
		// obtenido con el metodo ordinal()
		int posTipo = p.tipo().ordinal();

		int posTopping = topping.ordinal();

		// Si no tengo toppings para el tipo especifico de pizza, arrojo una excepcion y
		// la guardo en mi pila de excepciones
		if (cantToppings[posTipo][posTopping] <= 0) {

			IllegalArgumentException e = new IllegalArgumentException(
					"No se pudo fabricar " + p.getNombre() + " por falta de topping " + topping.getNombre());

			errores.push(e);

			throw e;

		}

		// Decremento la cantidad del topping para el tipo especifico de pizza
		cantToppings[posTipo][posTopping]--;

		// Agrego la pizza a mi lista de pizzas elaboradas
		listaPizzas.add(p);

		// Aumento el contador de tipos de pizzas a traves del posicionamiento directo
		cantTiposPizzas[posTipo]++;

	}

	// METODO QUE OBTIENE EL MONTO TOTAL DE VENTAS SEGUN EL NOMBRE DE LA PIZZA
	public float importeTotalVentasPorNombrePizza(String nombrePizza) {

		float sumatoria = 0;

		for (Pizza pizza : listaPizzas) {

			if (pizza.getNombre().equalsIgnoreCase(nombrePizza)) {

				sumatoria += pizza.getPrecioDeVenta();
			}

		}

		return sumatoria;
	}

	// METODO MOSTRAR HEREDADO DE LA INTERFAZ MOSTRABLE
	@Override
	public void mostrar() {

		TipoPizza tradicional = TipoPizza.TRADICIONAL, especial = TipoPizza.ESPECIAL,
				rectangular = TipoPizza.RECTANGULAR;

		System.out.println("Pizzeria: " + nombre);

		System.out.printf(MSG_CANTIDADES, cantTiposPizzas[tradicional.ordinal()], cantTiposPizzas[especial.ordinal()],
				cantTiposPizzas[rectangular.ordinal()]);

		System.out.printf(MSG_TOTALES, obtenerTotalVentas());

		System.out.println("--------------------------------------");

		mostrarPizzasFabricadas();

		System.out.println("--------------------------------------");

		mostrarErrores();

	}

	// METODO QUE MUESTRA LOS ERRORES DE LA PILA
	private void mostrarErrores() {

		PilaNodos<IllegalArgumentException> aux = new PilaNodos<>();
		IllegalArgumentException error;

		System.out.println("Pedidos con error:");

		// Recorro la pila mientras no este vacia, retirando sus elementos y
		// agregandolos a una lista auxiliar
		while (!errores.isEmpty()) {

			error = errores.pop();

			System.out.println(error.getMessage());

			aux.push(error);

		}

		// Recorro la pila auxiliar para dejar a la pila original en su orden original
		while (!aux.isEmpty()) {

			errores.push(aux.pop());
		}

	}

	// METODO QUE MUESTRA LAS PIZZAS FABRICADAS RECORRIENDO LA LISTA ORDENADA
	private void mostrarPizzasFabricadas() {

		System.out.println("Pizzas fabricadas por precio descendente:");

		for (Pizza pizza : listaPizzas) {

			pizza.mostrar();
		}

	}
	// METODO QUE RECORRE LAS PIZZAS FABRICADAS PARA OBTENER EL MONTO TOTAL DE VENTAS
	private float obtenerTotalVentas() {

		float sumatoria = 0;

		for (Pizza pizza : listaPizzas) {

			sumatoria += pizza.getPrecioDeVenta();

		}

		return sumatoria;
	}

}
