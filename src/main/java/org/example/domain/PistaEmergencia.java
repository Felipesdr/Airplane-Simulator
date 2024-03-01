package org.example.domain;

import org.example.util.Fila;
import org.example.util.ListaLigada;

public class PistaEmergencia {

    private Fila filaDeDecolagem = new Fila();

    private ListaLigada listaAterrisagemEmergencia =  new ListaLigada();


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
}