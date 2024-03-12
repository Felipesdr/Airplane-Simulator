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

        while(i < 4){

            System.out.println("====================== Rodada " + (i + 1) + "====================== ");

            List<Aviao> avioesChegando = criarAvioes();
            alocarAvioes(avioesChegando);
            imprimirConteudoFilas();

            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");

            i++;
            diminuirCombustivel();
        }
    }


    private void diminuirCombustivel() {

        List<Aviao> avioes = aeroporto.pegarTodosAvioesAterrissagem();

        avioes.stream().forEach(a-> a.setNivelCombustivel(a.getNivelCombustivel() -1));
    }

    private void imprimirConteudoFilas(){

        int tamanhoFila1Pista1 = aeroporto.getPista1().getFilaAterrisagem1().pegarTamanho();
        int tamanhoFila2Pista1 = aeroporto.getPista1().getFilaAterrisagem2().pegarTamanho();
        int tamanhoFila1Pista2 = aeroporto.getPista2().getFilaAterrisagem1().pegarTamanho();
        int tamanhoFila2Pista2 = aeroporto.getPista2().getFilaAterrisagem2().pegarTamanho();

        System.out.println("====================== Fila aterrissagem 1 Pista 1");
        System.out.println(" ");
        System.out.println("[Numero de aviões] -> " + tamanhoFila1Pista1);
        System.out.println("[Avioes]");
        int j = 0;

        if(tamanhoFila1Pista1 > 0){

            while(j < tamanhoFila1Pista1){


                Aviao temp = (Aviao) aeroporto.getPista1().getFilaAterrisagem1().pegar(j);

                System.out.println("Aviao "
                        + temp.getIdAviao()
                        + " ->"
                        + "Nivel de combustivel: "
                        + temp.getNivelCombustivel()
                        + " Tempo na fila: "
                        + temp.getTempoNaFila()
                );

                j++;
            }
        }


        System.out.println("====================== Fila aterrissagem 2 Pista 1");
        System.out.println(" ");
        System.out.println("[Numero de aviões] -> " + tamanhoFila2Pista1);
        System.out.println("[Avioes]");
        j = 0;

        if(tamanhoFila2Pista1 > 0){

            while(j < tamanhoFila2Pista1){

                Aviao temp = (Aviao) aeroporto.getPista1().getFilaAterrisagem2().pegar(j);

                System.out.println("Aviao "
                        + temp.getIdAviao()
                        + " ->"
                        + "Nivel de combustivel: "
                        + temp.getNivelCombustivel()
                        + " Tempo na fila: "
                        + temp.getTempoNaFila()
                );

                j++;
            }
        }

        System.out.println("====================== Fila aterrissagem 1 Pista 2");
        System.out.println("[Numero de aviões] -> " + tamanhoFila1Pista2);
        System.out.println("[Avioes]");
        j = 0;

        if(tamanhoFila1Pista2 > 0){

            while(j < tamanhoFila1Pista2){

                Aviao temp = (Aviao) aeroporto.getPista2().getFilaAterrisagem1().pegar(j);

                System.out.println("Aviao "
                        + temp.getIdAviao()
                        + " ->"
                        + "Nivel de combustivel: "
                        + temp.getNivelCombustivel()
                        + " Tempo na fila: "
                        + temp.getTempoNaFila()
                );

                j++;
            }
        }

        System.out.println("====================== Fila aterrissagem 2 Pista 2");
        System.out.println(" ");
        System.out.println("[Numero de aviões] -> " + tamanhoFila2Pista2);
        System.out.println("[Avioes]");
        j = 0;

        if(tamanhoFila2Pista1 > 0){

            while(j < tamanhoFila2Pista2){

                Aviao temp = (Aviao) aeroporto.getPista2().getFilaAterrisagem2().pegar(j);

                System.out.println("Aviao "
                        + temp.getIdAviao()
                        + " ->"
                        + "Nivel de combustivel: "
                        + temp.getNivelCombustivel()
                        + " Tempo na fila: "
                        + temp.getTempoNaFila()
                );

                j++;
            }
        }
    }

    private void alocarAvioes(List<Aviao> avioes) {

        for (Aviao a : avioes) {

            Fila menorFilaPista1 = aeroporto.getPista1().pegarMenorFilaAterrissagem();
            Fila menorFilaPista2 = aeroporto.getPista2().pegarMenorFilaAterrissagem();

            int tamanhoMenorFilaPista1 = aeroporto.getPista1().pegarMenorFilaAterrissagem().pegarTamanho();
            var tamanhoMenorFilaPista2 = aeroporto.getPista2().pegarMenorFilaAterrissagem().pegarTamanho();

            if (tamanhoMenorFilaPista1 >= tamanhoMenorFilaPista2) {

                menorFilaPista2.adicionar(a);

            } else {
                menorFilaPista1.adicionar(a);

            }

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
