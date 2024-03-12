package org.example.util;

public class Celula<T> {
	
	private T elemento;
	private Celula<T> proximo;
	private Celula<T> anterior;
	
	
	public Celula(T elemento) {
		this(elemento, null);
	}
	
	public Celula(T elemento, Celula proximo) {
		this.elemento = elemento;
		this.proximo = proximo;
	}
	
	public T getElemento() {
		return elemento;
	}

	public Celula getProximo() {
		return proximo;
	}
	public void setProximo(Celula proximo) {
		this.proximo = proximo;
	}
	public Celula getAnterior() {
		return anterior;
	}
	public void setAnterior(Celula anterior) {
		this.anterior = anterior;
	}

}
