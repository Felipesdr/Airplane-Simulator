package org.example.domain;

import org.example.util.Celula;
import org.example.util.Fila;
import org.example.util.ListaLigada;

import java.util.Comparator;
import java.util.List;

public class Pista {

    private Fila<Aviao> filaAterrisagem1 = new Fila<>();

    private Fila<Aviao> filaAterrisagem2 =  new Fila<>();

    private Fila<Aviao> filaDecolagem = new Fila<>();

    private ListaLigada<Aterrisagem> listaDeAterrisagem = new ListaLigada<>();

    private ListaLigada<Decolagem> listaDeDecolagem =  new ListaLigada<>();

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

    public Fila<Aviao> pegarMenorFilaAterrissagem(){

        if(filaAterrisagem1.pegarTamanho() < filaAterrisagem2.pegarTamanho()) {
            return filaAterrisagem1;
        } else {
            return filaAterrisagem2;
        }


    }

    public Fila<Aviao> pegarMaiorFilaAterrissagem(){

        if(filaAterrisagem1.pegarTamanho() > filaAterrisagem2.pegarTamanho()) {
            return filaAterrisagem1;
        } else {
            return filaAterrisagem2;
        }


    }

    public List<Fila<Aviao>> pegarFilasAterrissagem(){

        List<Fila<Aviao>> filasAterrissagem = List.of(filaAterrisagem1, filaAterrisagem2);


        return filasAterrissagem;
    }

    public Fila<Aviao> pegarMaiorFila() {

        List<Fila<Aviao>> filas = List.of(filaAterrisagem1, filaAterrisagem2, filaDecolagem);

        Comparator<Fila<Aviao>> comparadorTamanhoFila = Comparator.comparingInt(Fila::pegarTamanho);

        Fila<Aviao> maiorFila = filas.stream().max(comparadorTamanhoFila).get();

        return maiorFila;

    }





}
