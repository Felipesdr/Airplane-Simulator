package org.example.util;

import org.example.domain.Aviao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Fila<T> {
	
	private ListaLigada<T> lista = new ListaLigada<>();
	
	public void adicionar(T elemento) {
		lista.adicionarNoFinal(elemento);
	}
	
	public void remover() {
		lista.removerDoComeco();
	}
	
	public boolean ehVazia() {
		return lista.ehVazia();
	}
	
	public T poll() {
		
		if (ehVazia()) {
			return null;
		}
		else {
			T primeiro = lista.pegarPrimeiro();
			lista.removerDoComeco();
			return primeiro;
		}
	}
	
	public boolean contem(T elemento) {
		return lista.contem(elemento);
	}
	
	public T pegaPrimeiro() {
		return lista.pegarPrimeiro();
	}
	
	public T pegaUltimo() {
		return lista.pegarUltima();
	}
	
	public int pegarTamanho() {
		return lista.pegarTotalElementos();
	}

	public T pegar(int posicao){

		return lista.pegar(posicao);
	}

	public void removerPorPosicao(int posicao){

		lista.removerNaPosicao(posicao);
	}

	public int pegarPosicao(T elemento){
		return lista.pegarPosicao(elemento);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Fila<?> fila = (Fila<?>) o;
		return Objects.equals(lista, fila.lista);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lista);
	}
}
