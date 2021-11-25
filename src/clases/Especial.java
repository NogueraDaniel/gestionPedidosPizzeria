package clases;

public class Especial extends Pizza {

	// MENSAJES DE ERROR ESTATICOS:
	private static final String MSG_ERROR_CANT_FAINA = "La cantidad de faina no puede ser menor a 0";
	private static final String MSG_ERROR_TAMANIO = "El tamaño no puede ser nulo";
	
	//PRECIO ESTATICO:
	private static final float PRECIO_FAINA = 38;

	// ATRIBUTOS:
	private TamanioDePizza tamanio;
	private int cantFaina;

	// METODO QUE OBTIENE COSTO APLICANDO POLIMORFISMO
	@Override
	public float getPrecioDeCosto() {

		return (PRECIO_FAINA * cantFaina) + (getCostoDeProduccion() * tamanio.getPorcentaje());
	}

	// METODO QUE OBTIENE EL ENUM DE TIPO APLICANDO POLIMORFISMO
	@Override
	public TipoPizza tipo() {

		return TipoPizza.ESPECIAL;
	}

	// CONSTRUCTOR
	public Especial(String nombre, float costoDeProduccion, float porcentajeGanancia, int cantFaina,
			TamanioDePizza tamanio) {

		super(nombre, costoDeProduccion, porcentajeGanancia);

		setTamanio(tamanio);

		setCantFaina(cantFaina);
	}

	// Metodo que aplica polimorfismo, utilizando interiormente 'mensajeGeneral()'
	// que es un metodo de la clase padre
	@Override
	public void mostrar() {

		System.out.println(mensajeGeneral() + " - " + cantFaina + "  fainas - " + tamanio.getDescripcion());

	}

	// SETTERS CON MANEJO DE EXCEPCIONES
	private void setCantFaina(int cantFaina) {

		if (cantFaina < 0) {

			throw new IllegalArgumentException(MSG_ERROR_CANT_FAINA);
		}
		this.cantFaina = cantFaina;
	}

	private void setTamanio(TamanioDePizza tamanio) {

		if (tamanio == null) {

			throw new IllegalArgumentException(MSG_ERROR_TAMANIO);
		}
		this.tamanio = tamanio;
	}

}
