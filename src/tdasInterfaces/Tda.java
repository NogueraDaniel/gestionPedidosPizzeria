package tdasInterfaces;

public interface Tda extends Checkable {

	static final String ERR_TAM_ILEGAL = "Tamanio maximo definido ilegal";
	static final String ERR_ESTRUCTURA_LLENA = "%s llena";
	static final String ERR_ESTRUCTURA_VACIA = "%s vacia";

	static final int SIN_LIMITE = -1;
	static final int LIMITE_MINIMO_POSIBLE = 2;

	boolean isEmpty();

	boolean isFull();
	

}
