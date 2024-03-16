package org.example.domain;

import org.example.util.Fila;

import java.util.ArrayList;
import java.util.List;

public class Aeroporto {

    private Pista pista1;

    private Pista pista2;

    private PistaEmergencia pistaEmergencia;

    public Aeroporto(Pista pista1, Pista pista2, PistaEmergencia pistaEmergencia) {
        this.pista1 = pista1;
        this.pista2 = pista2;
        this.pistaEmergencia = pistaEmergencia;

    }

    public Pista getPista1() {
        return pista1;
    }

    public void setPista1(Pista pista1) {
        this.pista1 = pista1;
    }

    public Pista getPista2() {
        return pista2;
    }

    public void setPista2(Pista pista2) {
        this.pista2 = pista2;
    }

    public PistaEmergencia getPistaEmergencia() {
        return pistaEmergencia;
    }

    public void setPistaEmergencia(PistaEmergencia pistaEmergencia) {
        this.pistaEmergencia = pistaEmergencia;
    }

    public List<Aviao> pegarTodosAvioesAterrissagem(){

        List<Fila<Aviao>> listaAvioesAterrissagemP1 = pista1.pegarFilasAterrissagem();
        List<Fila<Aviao>> listaAvioesAterrissagemP2 = pista2.pegarFilasAterrissagem();
        List<Aviao> avioesFilaAterrissagem = new ArrayList<>();

        for(Fila<Aviao> f: listaAvioesAterrissagemP1) {

            int i = 0;
            while (i < f.pegarTamanho()){

                Aviao temp = f.pegar(i);
                avioesFilaAterrissagem.add(temp);
                i++;
            }
        }

        for(Fila<Aviao> f: listaAvioesAterrissagemP2) {

            int i = 0;
            while (i < f.pegarTamanho()){

                Aviao temp = f.pegar(i);
                avioesFilaAterrissagem.add(temp);
                i++;
            }
        }
        return avioesFilaAterrissagem;
    }

    public List<Aviao> pegarTodosAvioesDecolagem(){

        Fila<Aviao> filaDecolagemP1 = pista1.getFilaDecolagem();
        Fila<Aviao> filaDecolagemP2 = pista2.getFilaDecolagem();
        Fila<Aviao> filaEmergencia = pistaEmergencia.getFilaDeDecolagem();
        List<Aviao> avioesFilaDecolagem = new ArrayList<>();

        List<Fila<Aviao>> filasDecolagem = List.of(filaDecolagemP1, filaDecolagemP2, filaEmergencia);

        for(Fila<Aviao> f: filasDecolagem) {

            int i = 0;
            while (i < f.pegarTamanho()){

                Aviao temp = f.pegar(i);
                avioesFilaDecolagem.add(temp);
                i++;
            }
        }
        return avioesFilaDecolagem;
    }

    public int pegarNumeroPista(Pista pista){

        if (pista == pista1){
            return 1;
        }
        return 2;
    }
}
