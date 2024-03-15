package org.example.util;

import org.example.domain.Aviao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListaLigada <T> {

	private Celula<T> primeiraCelula = null;
	private Celula<T> ultimaCelula = null;
	private int totalDeElementos = 0;

	private static final String MSG_ERRO_AO_REMOVER_CELULA_POSICAO_INVALIDA = "Erro ao remover celula: posição inválida.";
	private static final String MSG_ERRO_REMOCAO_INVALIDA_LISTA_ESTA_VAZIA = "Erro ao tentar remover: nao contem elementos.";
	private static final String MSG_ERRO_AO_RECUPERAR_CELULA_POSICAO_INVALIDA = "Erro ao recuperar celula: posicao inválida.";
	private static final String MSG_ERRO_AO_RECUPERAR_PRIMEIRA_POSICAO_ESTA_VAZIA = "Erro ao recuperar: nao contem elementos.";
	private static final String MSG_ERRO_AO_INSERIR_POSICAO_INVALIDA = "Erro ao tentar inserir: posicao invalida.";

	public void adicionarNoComeco(T novoElemento) {

		Celula<T> nova;
		if (ehVazia()) {
			nova = new Celula(novoElemento);
			ultimaCelula = nova;
		} else {
			nova = new Celula(novoElemento, primeiraCelula);
			primeiraCelula.setAnterior(nova);
		}
		primeiraCelula = nova;
		totalDeElementos++;
	}

	public void adicionarNoFinal(T novoElemento) {

		if (ehVazia()) {
			adicionarNoComeco(novoElemento);
		} else {
			Celula<T> nova = new Celula<T>(novoElemento);
			ultimaCelula.setProximo(nova);
			nova.setAnterior(ultimaCelula);
			ultimaCelula = nova;
			totalDeElementos++;
		}

	}

	private boolean validarPosicaoInsercao(int posicao) {
		return (posicao >= 0) && (posicao <= totalDeElementos);
	}

	public void adicinarNaPosicao(T novoElemento, int posicao) {

		if (!validarPosicaoInsercao(posicao)) {
			throw new IllegalArgumentException(MSG_ERRO_AO_INSERIR_POSICAO_INVALIDA);
		}

		if (posicao == 0) {
			adicionarNoComeco(novoElemento);
		} else if (posicao == totalDeElementos) {
			adicionarNoFinal(novoElemento);
		} else {
			Celula anterior = pegarCelula(posicao - 1);
			Celula proxima = anterior.getProximo();

			Celula nova = new Celula(novoElemento, proxima);
			nova.setAnterior(anterior);
			anterior.setProximo(nova);
			proxima.setAnterior(nova);
			totalDeElementos++;
		}

	}

	public boolean ehVazia() {
		return totalDeElementos == 0;

	}

	public int pegarTotalElementos() {
		return totalDeElementos;
	}

	public T pegarPrimeiro() {
		if (primeiraCelula == null)
			throw new RuntimeException(MSG_ERRO_AO_RECUPERAR_PRIMEIRA_POSICAO_ESTA_VAZIA);

		return primeiraCelula.getElemento();
	}

	public T pegarUltima() {
		if (ultimaCelula == null)
			throw new RuntimeException(MSG_ERRO_AO_RECUPERAR_PRIMEIRA_POSICAO_ESTA_VAZIA);

		return ultimaCelula.getElemento();
	}

	private boolean validarPosicaoRecuperacao(int posicao) {
		return (posicao >= 0) && (posicao < totalDeElementos);
	}

	private Celula<T> pegarCelula(int posicao) {

		if (!validarPosicaoRecuperacao(posicao)) {
			throw new IllegalArgumentException(MSG_ERRO_AO_RECUPERAR_CELULA_POSICAO_INVALIDA);
		}

		Celula atual = primeiraCelula;

		for (int i = 0; i < posicao; i++) {
			atual = atual.getProximo();
		}

		return atual;

	}

	public T pegar(int posicao) {

		T elemento = pegarCelula(posicao).getElemento();
		return elemento;

	}

	public boolean contem(T elementoBuscado) {

		Celula atual = primeiraCelula;

		while (atual != null) {

			if (atual.getElemento().equals(elementoBuscado)) {
				return true;
			}
			atual = atual.getProximo();
		}

		return false;
	}

	public int pegarPosicao(T elementoBuscado){

		Celula atual = primeiraCelula;
		int posicao = 0;

		while (atual != null) {

			if (atual.getElemento().equals(elementoBuscado)) {
				return posicao;
			}
			atual = atual.getProximo();
			posicao++;
		}

		return -1;
	}

	public void removerDoComeco() {

		if (ehVazia()) {
			throw new RuntimeException(MSG_ERRO_REMOCAO_INVALIDA_LISTA_ESTA_VAZIA);
		}
		primeiraCelula = primeiraCelula.getProximo();

		if (totalDeElementos > 1) {
			primeiraCelula.setAnterior(null);
		}
		else {
			ultimaCelula = null;
		}
		totalDeElementos--;


	}

	public void removerDoFinal() {

		if (ehVazia()) {
			throw new RuntimeException(MSG_ERRO_REMOCAO_INVALIDA_LISTA_ESTA_VAZIA);
		}
		if (totalDeElementos == 1) {
			removerDoComeco();
		} else {
			Celula penultima = ultimaCelula.getAnterior();
			penultima.setProximo(null);
			ultimaCelula = penultima;
			totalDeElementos--;
		}

	}

	public void removerNaPosicao(int posicao) {

		if (!validarPosicaoRecuperacao(posicao)) {
			throw new IllegalArgumentException(MSG_ERRO_AO_REMOVER_CELULA_POSICAO_INVALIDA);
		}

		if (posicao == 0) {
			removerDoComeco();
		} 
		else if (posicao == totalDeElementos) {
			removerDoFinal();
		} else { 
			Celula anterior = pegarCelula(posicao - 1);
			Celula atual = anterior.getProximo();
			Celula proxima = atual.getProximo();
			
			anterior.setProximo(proxima);
			proxima.setAnterior(anterior);
			
			totalDeElementos--;
		}
	}


	public boolean primeiroEhNulo() {
		return primeiraCelula == null;
	}

	public boolean ultimoEhNulo() {
		return ultimaCelula == null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ListaLigada<?> that = (ListaLigada<?>) o;
		return totalDeElementos == that.totalDeElementos && Objects.equals(primeiraCelula, that.primeiraCelula) && Objects.equals(ultimaCelula, that.ultimaCelula);
	}

	@Override
	public int hashCode() {
		return Objects.hash(primeiraCelula, ultimaCelula, totalDeElementos);
	}
}