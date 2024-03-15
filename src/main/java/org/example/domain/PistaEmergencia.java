package org.example.domain;

import org.example.util.Fila;
import org.example.util.ListaLigada;

public class PistaEmergencia {

    private Fila<Aviao> filaDeDecolagem = new Fila<>();

    private ListaLigada<Aterrisagem> listaAterrisagemEmergencia =  new ListaLigada<>();

    private ListaLigada<Decolagem> listaDecolagem = new ListaLigada<>();


    public Fila getFilaDeDecolagem() {
        return filaDeDecolagem;
    }

    public void setFilaDeDecolagem(Fila filaDeDecolagem) {
        this.filaDeDecolagem = filaDeDecolagem;
    }

    public ListaLigada getListaAterrisagemEmergencia() {
        return listaAterrisagemEmergencia;
    }

    public void setListaAterrisagemEmergencia(ListaLigada listaAterrisagemEmergencia) {
        this.listaAterrisagemEmergencia = listaAterrisagemEmergencia;
    }

    public ListaLigada<Decolagem> getListaDecolagem() {
        return listaDecolagem;
    }

    public void setListaDecolagem(ListaLigada<Decolagem> listaDecolagem) {
        this.listaDecolagem = listaDecolagem;
    }
}
