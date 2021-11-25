package test;

import clases.AdicionalQueso;
import clases.Especial;
import clases.Pizzeria;
import clases.Rectangular;
import clases.TamanioDePizza;
import clases.TipoDeMasa;
import clases.TipoPizza;
import clases.Topping;
import clases.Tradicional;

public class Test {

	private static final String MSG_ERROR = "Error fabricando una pizza %s\n";

	public static void main(String[] args) {

		Pizzeria laPizzeria = new Pizzeria("Pizza para todos");

		cargarToppings(laPizzeria);

		pedirTradicional(laPizzeria, "Mozarella", 500, 20, TipoDeMasa.A_LA_PIEDRA, Topping.CEBOLLA);
		pedirTradicional(laPizzeria, "Margarita", 600, 20, TipoDeMasa.A_LA_PIEDRA, Topping.JAMON);
		pedirTradicional(laPizzeria, "", 600, 20, TipoDeMasa.A_LA_PIEDRA, Topping.CEBOLLA);
		pedirTradicional(laPizzeria, "Mozarella", 500, 20, TipoDeMasa.A_LA_PIEDRA, Topping.JAMON);
		pedirTradicional(laPizzeria, "Peperoni", 700, 10, TipoDeMasa.MEDIA_MASA, Topping.JAMON);
		pedirTradicional(laPizzeria, "Peperoni", -700, 10, TipoDeMasa.MEDIA_MASA, Topping.TOMATE);
		pedirTradicional(laPizzeria, "Peperoni", -700, 10, TipoDeMasa.MEDIA_MASA, null);

		pedirEspecial(laPizzeria, "4 Quesos", 800, 20, 0, TamanioDePizza.PERSONAL, Topping.MORRON);
		pedirEspecial(laPizzeria, "4 Quesos", 800, 15, 2, TamanioDePizza.MEDIANA, Topping.MORRON);
		pedirEspecial(laPizzeria, null, 800, 15, 2, TamanioDePizza.MEDIANA, Topping.SALAMIN);
		pedirEspecial(laPizzeria, "4 Quesos", 800, 15, 2, TamanioDePizza.MEDIANA, null);
		pedirEspecial(laPizzeria, "4 Quesos", 800, 15, 4, TamanioDePizza.FAMILIAR, Topping.JAMON);
		pedirEspecialNula(laPizzeria, Topping.JAMON);
		pedirEspecial(laPizzeria, "Super Fugazzeta", 1100, 25, 4, TamanioDePizza.FAMILIAR, Topping.CEBOLLA);
		pedirEspecial(laPizzeria, "Super Fugazzeta", 00, 25, 4, TamanioDePizza.FAMILIAR, Topping.TOMATE);
		pedirEspecial(laPizzeria, "Super Fugazzeta", 100, 25, 4, TamanioDePizza.FAMILIAR, Topping.TOMATE);
		pedirEspecial(laPizzeria, "Super Fugazzeta", 100, 25, 4, TamanioDePizza.FAMILIAR, Topping.SALAMIN);

		pedirRectangular(laPizzeria, "Canchera", 800, 20, 2, 3, AdicionalQueso.QUESO_SIMPLE, Topping.CEBOLLA);
		pedirRectangular(laPizzeria, "Canchera", 800, 15, 2, 4, AdicionalQueso.QUESO_DOBLE, Topping.JAMON);
		pedirRectangular(laPizzeria, "Canchera", 800, 15, -2, 4, AdicionalQueso.QUESO_DOBLE, Topping.MORRON);
		pedirRectangular(laPizzeria, "Canchera", 800, 15, 2, 5, AdicionalQueso.QUESO_TRIPLE, Topping.CEBOLLA);
		pedirRectangular(laPizzeria, "Canchera", 800, 15, 2, 4, AdicionalQueso.QUESO_DOBLE, Topping.MORRON);
		pedirRectangular(laPizzeria, "Canchera", 800, 15, 2, 4, AdicionalQueso.QUESO_DOBLE, Topping.MORRON);

		System.out.println("--------------------------------------");

		System.out.println("La sumatoria de ventas de todas las pizzas 'Canchera' es ?: "
				+ (laPizzeria.importeTotalVentasPorNombrePizza("Canchera")));

		System.out.println("--------------------------------------");

		laPizzeria.mostrar();

	}

	// METODO ESTATICO QUE INCORPORA TOPPINGS EN LA MATRIZ DE LA PIZZERIA
	private static void cargarToppings(Pizzeria laPizzeria) {
		laPizzeria.incorporarTopping(TipoPizza.TRADICIONAL, Topping.CEBOLLA, 2);
		laPizzeria.incorporarTopping(TipoPizza.TRADICIONAL, Topping.JAMON, 1);
		laPizzeria.incorporarTopping(TipoPizza.TRADICIONAL, Topping.JAMON, 1);
		laPizzeria.incorporarTopping(TipoPizza.TRADICIONAL, Topping.TOMATE, 2);

		laPizzeria.incorporarTopping(TipoPizza.ESPECIAL, Topping.MORRON, 2);
		laPizzeria.incorporarTopping(TipoPizza.ESPECIAL, Topping.SALAMIN, 2);
		laPizzeria.incorporarTopping(TipoPizza.ESPECIAL, Topping.JAMON, 2);
		laPizzeria.incorporarTopping(TipoPizza.ESPECIAL, Topping.CEBOLLA, 2);

		laPizzeria.incorporarTopping(TipoPizza.RECTANGULAR, Topping.MORRON, 2);
		laPizzeria.incorporarTopping(TipoPizza.RECTANGULAR, Topping.JAMON, 1);
		laPizzeria.incorporarTopping(TipoPizza.RECTANGULAR, Topping.CEBOLLA, 1);

	}

	// METODOS ESTATICOS PARA PEDIR PIZZAS SEGUN SU TIPO
	private static void pedirTradicional(Pizzeria laPizzeria, String nombre, float costoBase, float porcentajeGanancia,
			TipoDeMasa tipoDeMasa, Topping topping) {

		try {

			laPizzeria.ingresarPedido(new Tradicional(nombre, costoBase, porcentajeGanancia, tipoDeMasa), topping);

		} catch (IllegalArgumentException e) {

			System.out.printf(MSG_ERROR, e.getMessage());

		}
	}

	private static void pedirEspecial(Pizzeria laPizzeria, String nombre, float costoBase, float porcentajeGanancia,
			int cantidadFainas, TamanioDePizza tamanioDePizza, Topping topping) {
		// TODO Completar

		try {

			laPizzeria.ingresarPedido(
					new Especial(nombre, costoBase, porcentajeGanancia, cantidadFainas, tamanioDePizza), topping);

		} catch (IllegalArgumentException e) {

			System.out.printf(MSG_ERROR, e.getMessage());

		}
	}

	private static void pedirRectangular(Pizzeria laPizzeria, String nombre, float costoBase, float porcentajeGanancia,
			long largo, long ancho, AdicionalQueso adicionalQueso, Topping topping) {

		try {

			laPizzeria.ingresarPedido(
					new Rectangular(nombre, costoBase, porcentajeGanancia, largo, ancho, adicionalQueso), topping);

		} catch (IllegalArgumentException e) {

			System.out.printf(MSG_ERROR, e.getMessage());

		}
	}

	//PROBAMOS EL MANEJO DE ERRORES ENVIANDO COMO PARAMETRO UN NULL AL IGNRESAR PEDIDO
	private static void pedirEspecialNula(Pizzeria laPizzeria, Topping topping) {

		try {

			laPizzeria.ingresarPedido(null, topping);

		} catch (IllegalArgumentException e) {

			System.out.printf(MSG_ERROR, e.getMessage());
		}
	}
}
