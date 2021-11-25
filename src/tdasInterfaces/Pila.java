package tdasInterfaces;


public interface Pila<T> extends Tda {

	void push(T element);

	T pop();

	T peek();

}