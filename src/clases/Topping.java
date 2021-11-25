package clases;

public enum Topping {

	TOMATE("Tomate"), JAMON("Jamón"), MORRON("Morrón"), CEBOLLA("Cebolla"), SALAMIN("Salamín");
	
	private String nombre;
	
	private Topping(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return this.nombre;
	}
}
