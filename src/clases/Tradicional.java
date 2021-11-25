package clases;

public class Tradicional extends Pizza {

	// MENSAJES DE ERROR ESTATICOS:
	private static final String MSG_ERROR_TIPO_MASA = "El tipo de masa no puede ser null";

	// ATRIBUTOS:
	private TipoDeMasa tipoMasa;

	// METODO QUE OBTIENE COSTO APLICANDO POLIMORFISMO
	@Override
	public float getPrecioDeCosto() {

		return getCostoDeProduccion() * tipoMasa.getPorcentaje();
	}

	// METODO QUE OBTIENE EL ENUM DE TIPO APLICANDO POLIMORFISMO
	@Override
	public TipoPizza tipo() {

		return TipoPizza.TRADICIONAL;
	}

	// CONSTRUCTOR
	public Tradicional(String nombre, float costoDeProduccion, float porcentajeGanancia, TipoDeMasa tipoMasa) {
		super(nombre, costoDeProduccion, porcentajeGanancia);
		setTipoMasa(tipoMasa);
	}

	// Metodo que aplica polimorfismo, utilizando interiormente 'mensajeGeneral()'
	// que es un metodo de la clase padre
	public void mostrar() {

		System.out.println(mensajeGeneral() + " - " + tipoMasa.getDescripcion());

	}

	// SETTERS CON MANEJO DE EXCEPCIONES
	private void setTipoMasa(TipoDeMasa tipoMasa) {

		if (tipoMasa == null) {

			throw new IllegalArgumentException(MSG_ERROR_TIPO_MASA);
		}

		this.tipoMasa = tipoMasa;
	}

}
