package clases;

public class Rectangular extends Pizza {

	// MENSAJES DE ERROR ESTATICOS:
	private static final String MSG_ERROR_LARGO = "El largo no puede ser menor o igual a 0";
	private static final String MSG_ERROR_ANCHO = "El ancho no puede ser menor o igual a 0";
	private static final String MSG_ERROR_ADICIONAL = "El adicional de queso no puede ser null";

	// PRECIO ESTATICO:
	private static final float PRECIO_PORCION = 52;

	// ATRIBUTOS:
	private long largo;
	private long ancho;
	private AdicionalQueso adicional;

	// METODO QUE OBTIENE COSTO APLICANDO POLIMORFISMO
	@Override
	public float getPrecioDeCosto() {

		float costoTotal = getCostoDeProduccion() + (getPorcion() * PRECIO_PORCION);

		costoTotal = costoTotal * adicional.getMultiplicadorQueso();

		return costoTotal;
	}

	// METODO QUE OBTIENE EL ENUM DE TIPO APLICANDO POLIMORFISMO
	@Override
	public TipoPizza tipo() {

		return TipoPizza.RECTANGULAR;
	}

	//CONSTRUCTOR
	public Rectangular(String nombre, float costoDeProduccion, float porcentajeGanancia, long largo, long ancho,
			AdicionalQueso adicional) {

		super(nombre, costoDeProduccion, porcentajeGanancia);

		setLargo(largo);

		setAncho(ancho);

		setAdicional(adicional);
	}

	// Metodo que aplica polimorfismo, utilizando interiormente 'mensajeGeneral()'
	// que es un metodo de la clase padre
	@Override
	public void mostrar() {

		System.out.println(mensajeGeneral() + " - " + getPorcion() + " porciones - " + adicional.getDescripcion());

	}

	// METODOS PROPIOS
	private float getPorcion() {

		return largo * ancho;
	}

	// SETTERS CON MANEJO DE EXCEPCIONES
	private void setAdicional(AdicionalQueso adicional) {

		if (adicional == null) {

			throw new IllegalArgumentException(MSG_ERROR_ADICIONAL);
		}

		this.adicional = adicional;
	}

	private void setAncho(long ancho) {

		if (ancho <= 0) {

			throw new IllegalArgumentException(MSG_ERROR_ANCHO);
		}

		this.ancho = ancho;
	}

	private void setLargo(long largo) {

		if (largo <= 0) {

			throw new IllegalArgumentException(MSG_ERROR_LARGO);
		}
		this.largo = largo;
	}

}
