package org.example.domain;

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


        }
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
