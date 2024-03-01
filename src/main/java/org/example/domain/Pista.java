package org.example.domain;

import org.example.util.Fila;
import org.example.util.ListaLigada;

public class Pista {

    private Fila filaAterrisagem1 = new Fila();

    private Fila filaAterrisagem2 =  new Fila();

    private Fila filaDecolagem = new Fila();

    private ListaLigada listaDeAterrisagem = new ListaLigada();

    private ListaLigada listaDeDecolagem =  new ListaLigada();

    public Fila getFilaAterrisagem1() {
        return filaAterrisagem1;
    }

    public Fila getFilaAterrisagem2() {
        return filaAterrisagem2;
    }

    public Fila getFilaDecolagem() {
        return filaDecolagem;
    }

    public ListaLigada getListaDeAterrisagem() {
        return listaDeAterrisagem;
    }

    public ListaLigada getListaDeDecolagem() {
        return listaDeDecolagem;
    }
}
