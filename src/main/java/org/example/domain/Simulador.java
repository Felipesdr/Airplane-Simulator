package org.example.domain;

import org.example.util.Fila;
import org.example.util.RandomPicker;

import java.util.ArrayList;
import java.util.List;

public class Simulador {

    private Aeroporto aeroporto;
    private Long idIterator = 1L;

    public Simulador(){
        this.aeroporto = iniciarAeroporto();
    }

    public void iniciarSimulador(){

        int i = 0;

        while(i <= 10){

            List<Aviao> avioesChegando = criarAvioes();
            alocarAvioes(avioesChegando);

            i++;
        }
    }

    private void alocarAvioes(List<Aviao> avioes){

        var menorFilaPista1 = aeroporto.getPista1().pegarMenorFilaAterrissagem();
        var menorFilaPista2 = aeroporto.getPista2().pegarMenorFilaAterrissagem();


        for(Aviao a: avioes) {

            if(menorFilaPista1.pegarTamanho() >= menorFilaPista2.pegarTamanho()){

                menorFilaPista2.adicionar(a);
                System.out.println("Aviao nº " + a.getIdAviao() + " está na fila para pista 2");

            } else {
                menorFilaPista1.adicionar(a);
                System.out.println("Aviao nº " + a.getIdAviao() + " está na fila para pista 1");
            }

        }

        int tamanhoFila1Pista1 = aeroporto.getPista1().getFilaAterrisagem1().pegarTamanho();
        int tamanhoFila2Pista1 = aeroporto.getPista1().getFilaAterrisagem2().pegarTamanho();
        int tamanhoFila1Pista2 = aeroporto.getPista2().getFilaAterrisagem1().pegarTamanho();
        int tamanhoFila2Pista2 = aeroporto.getPista2().getFilaAterrisagem2().pegarTamanho();

        System.out.println("Pista 1 - Fila 1: " + tamanhoFila1Pista1 + "aviões");
        System.out.println("Pista 1 - Fila 2: " + tamanhoFila2Pista1 + "aviões");
        System.out.println("Pista 2 - Fila 1: " + tamanhoFila1Pista2 + "aviões");
        System.out.println("Pista 2 - Fila 2: " + tamanhoFila2Pista2 + "aviões");


    }

    private List<Aviao> criarAvioes(){

        int i = 0;
        int numeroDeAvioesChegando = RandomPicker.getRandomNumber(3);
        List<Aviao> avioesChegando = new ArrayList<>();

        while (i <= numeroDeAvioesChegando){
            Aviao novoAviao = new Aviao(idIterator);
            avioesChegando.add(novoAviao);
            i++;
            idIterator++;
        }
        System.out.println(avioesChegando.size() + " aviões chegaram." );
        return avioesChegando;
    }

    private Pista iniciarPista(){

        return new Pista();
    }

    private PistaEmergencia iniciarPistaEmergencia(){

        return new PistaEmergencia();
    }

    private Aeroporto iniciarAeroporto(){

        return new Aeroporto(iniciarPista(), iniciarPista(), iniciarPistaEmergencia());
    }


}
