package clases;

public abstract class Pizza implements Mostrable {

	// MENSAJES DE ERROR ESTATICOS:
	private static final String MSG_GANANCIA_INVALIDA = "Ganancia inválida";
	private static final String MSG_COSTO_INVALIDO = "Costo inválido";
	private static final String MSG_NOMBRE_INVALIDO = "Nombre inválido";

	// ATRIBUTOS
	private String nombre;
	private float costoDeProduccion;
	private float porcentajeGanancia;

	// CONSTRUCTOR
	public Pizza(String nombre, float costoDeProduccion, float porcentajeGanancia) {

		this.setNombre(nombre);
		this.setCostoDeProduccion(costoDeProduccion);
		this.setPorcentajeGanancia(porcentajeGanancia);
	}

	// METODO QUE RETORNA UN MENSAJE COMUN PARA UTILIZAR EN TODAS LAS SUBCLASES
	public String mensajeGeneral() {

		return tipo() + " - " + getNombre() + " - Precio de venta: $ " + getPrecioDeVenta();

	}

	// METODO ABSTRACTO PARA CALCULAR COSTO
	public abstract float getPrecioDeCosto();

	// METODO ABSTRACTO QUE DEVUELVE EL ENUM DE TIPO
	public abstract TipoPizza tipo();

	// SETTERS CON MANEJO DE EXCEPCIONES
	private void setNombre(String nombre) {

		if (nombre == null || nombre.isBlank()) {
			throw new IllegalArgumentException(MSG_NOMBRE_INVALIDO);
		}

		this.nombre = nombre;
	}

	private void setCostoDeProduccion(float costoDeProduccion) {

		if (costoDeProduccion <= 0) {
			throw new IllegalArgumentException(MSG_COSTO_INVALIDO);
		}

		this.costoDeProduccion = costoDeProduccion;
	}

	private void setPorcentajeGanancia(float porcentajeGanancia) {

		if (porcentajeGanancia < 0) {
			throw new IllegalArgumentException(MSG_GANANCIA_INVALIDA);
		}

		this.porcentajeGanancia = porcentajeGanancia;
	}

	// PRECIO DE VENTA
	public float getPrecioDeVenta() {

		float costo = this.getPrecioDeCosto();

		return costo + (this.porcentajeGanancia * costo / 100);
	}

	// COSTO DE PRODUCCION
	protected float getCostoDeProduccion() {

		return costoDeProduccion;

	}

	// GETTERS
	public String getNombre() {
		return nombre;
	}

}
