package org.example.domain;

import org.example.util.Fila;
import org.example.util.ListaLigada;
import org.example.util.RandomPicker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Simulador {

    private Aeroporto aeroporto;

    private Pista pista1;
    private Pista pista2;
    private PistaEmergencia pistaEmergencia;
    private Fila<Aviao> filaP1Aterrissagem1;
    private Fila<Aviao> filaP1Aterrissagem2;
    private Fila<Aviao> filaP1Decolagem;
    private Fila<Aviao> filaP2Aterrissagem1;
    private Fila<Aviao> filaP2Aterrissagem2;
    private Fila<Aviao> filaP2Decolagem;
    private Fila<Aviao> filaPEmergencia;
    private ListaLigada<Decolagem> listaDecolagemPista1;
    private ListaLigada<Decolagem> listaDecolagemPista2;
    private ListaLigada<Decolagem> listaDecolagemPistaEmergencia;
    private ListaLigada<Aterrisagem> listaAterrissagemPista1;
    private ListaLigada<Aterrisagem> listaAterrissagemPista2;
    private ListaLigada<Aterrisagem> listaAterrissagemPistaEmergencia;
    private Long idIterator = 1L;
    private Scanner sc = new Scanner(System.in);
    private Integer contadorDeRodadas = 0;
    private Integer contadorAvioesCaindo = 0;

    public Simulador() {
        this.aeroporto = iniciarAeroporto();
        this.pista1 = aeroporto.getPista1();
        this.pista2 = aeroporto.getPista2();
        this.pistaEmergencia = aeroporto.getPistaEmergencia();
        this.filaP1Aterrissagem1 = aeroporto.getPista1().getFilaAterrisagem1();
        this.filaP1Aterrissagem2 = aeroporto.getPista1().getFilaAterrisagem1();
        this.filaP1Decolagem = aeroporto.getPista1().getFilaDecolagem();
        this.filaP2Aterrissagem1 = aeroporto.getPista2().getFilaAterrisagem1();
        this.filaP2Aterrissagem2 = aeroporto.getPista2().getFilaAterrisagem2();
        this.filaP2Decolagem = aeroporto.getPista2().getFilaDecolagem();
        this.filaPEmergencia = aeroporto.getPistaEmergencia().getFilaDeDecolagem();
        this.listaDecolagemPista1 = aeroporto.getPista1().getListaDeDecolagem();
        this.listaDecolagemPista2 = aeroporto.getPista2().getListaDeDecolagem();
        this.listaDecolagemPistaEmergencia = aeroporto.getPistaEmergencia().getListaDecolagem();
        this.listaAterrissagemPista1 = aeroporto.getPista1().getListaDeAterrisagem();
        this.listaAterrissagemPista2 = aeroporto.getPista2().getListaDeAterrisagem();
        this.listaAterrissagemPistaEmergencia = aeroporto.getPistaEmergencia().getListaAterrisagemEmergencia();
    }


    public void iniciarSimulador() {

        int a = 1;
        while (a != 2) {

            System.out.println("====================== Rodada " + (contadorDeRodadas + 1) + "====================== ");
            System.out.println(" ");

            System.out.println("====================== Estaticas ====================== ");
            double mediaDecolagem = calcularMediaDecolagem();
            double mediaAterrissagem = calcularMediaAterrissagem();
            System.out.println("Media de tempo para decolagem: " + mediaDecolagem);
            System.out.println("Media de tempo para aterrissagem: " + mediaAterrissagem);
            cairAviao();
            System.out.println("Queda de aviões: " + contadorAvioesCaindo);

            System.out.println("====================== Decolagens e aterrissagens ====================== ");
            escolherProcedimento();
            System.out.println("====================== Chegada de avioes ====================== ");
            List<Aviao> avioesParaAterrisar = criarAvioes();
            alocarAvioesAterrissagem(avioesParaAterrisar);

            List<Aviao> avioesParaDecolar = criarAvioes();
            alocarAvioesDecolagem(avioesParaDecolar);

            imprimirConteudoFilas();

            System.out.println(" ");


            diminuirCombustivel();
            aumentarTempoNaFila();

            System.out.println("Para continuar digite 1");
            System.out.println("Para sair digite 2");
            System.out.flush();

            a = sc.nextInt();
            contadorDeRodadas++;
        }
    }

    public void cairAviao() {

        int i = 0;

        while (i < filaP1Aterrissagem1.pegarTamanho() && !filaP1Aterrissagem1.ehVazia()) {

            Aviao temp = filaP1Aterrissagem1.pegar(i);

            if (temp.getNivelCombustivel() < 0) {

                filaP1Aterrissagem1.removerPorPosicao(i);
                contadorAvioesCaindo++;
            }

            i++;
        }

        i = 0;
        while (i < filaP1Aterrissagem2.pegarTamanho() && !filaP1Aterrissagem2.ehVazia()) {

            Aviao temp = filaP1Aterrissagem2.pegar(i);

            if (temp.getNivelCombustivel() < 0) {

                filaP1Aterrissagem2.removerPorPosicao(i);
                contadorAvioesCaindo++;
            }

            i++;
        }

        i = 0;
        while (i < filaP2Aterrissagem1.pegarTamanho() && !filaP2Aterrissagem1.ehVazia()) {

            Aviao temp = filaP2Aterrissagem1.pegar(i);

            if (temp.getNivelCombustivel() < 0) {

                filaP2Aterrissagem1.removerPorPosicao(i);
                contadorAvioesCaindo++;
            }

            i++;
        }

        i = 0;
        while (i < filaP2Aterrissagem2.pegarTamanho() && !filaP2Aterrissagem2.ehVazia()) {

            Aviao temp = filaP2Aterrissagem2.pegar(i);

            if (temp.getNivelCombustivel() < 0) {

                filaP2Aterrissagem2.removerPorPosicao(i);
                contadorAvioesCaindo++;
            }

            i++;
        }
    }

    private Double calcularMediaAterrissagem() {

        Double mediaAterrissagem;
        int mediaP1 = 0;
        int sum = 0;
        int i = 0;
        while (i < listaAterrissagemPista1.pegarTotalElementos() && !listaAterrissagemPista1.ehVazia()) {

            Aterrisagem temp = listaAterrissagemPista1.pegar(i);

            int esperaDecolagem = temp.getSaida() - temp.getChegada();

            sum += esperaDecolagem;

            i++;
        }
        if (listaAterrissagemPista1.pegarTotalElementos() > 0) {
            mediaP1 = sum / listaAterrissagemPista1.pegarTotalElementos();
        }

        int mediaP2 = 0;
        sum = 0;
        i = 0;
        while (i < listaAterrissagemPista1.pegarTotalElementos() && !listaAterrissagemPista1.ehVazia()) {

            Aterrisagem temp = listaAterrissagemPista1.pegar(i);

            int esperaDecolagem = temp.getSaida() - temp.getChegada();

            sum += esperaDecolagem;

            i++;
        }
        if (listaAterrissagemPista1.pegarTotalElementos() > 0) {
            mediaP2 = sum / listaAterrissagemPista1.pegarTotalElementos();
        }

        int mediaPE = 0;
        sum = 0;
        i = 0;
        while (i < listaAterrissagemPista1.pegarTotalElementos() && !listaAterrissagemPista1.ehVazia()) {

            Aterrisagem temp = listaAterrissagemPista1.pegar(i);

            int esperaDecolagem = temp.getSaida() - temp.getChegada();

            sum += esperaDecolagem;

            i++;
        }
        if (listaAterrissagemPista1.pegarTotalElementos() > 0) {
            mediaPE = sum / listaAterrissagemPista1.pegarTotalElementos();
        }

        mediaAterrissagem = (double) ((mediaP1 + mediaP2 + mediaPE) / 3);

        return mediaAterrissagem;
    }

    private Double calcularMediaDecolagem() {

        Double mediaDecolagem;
        int mediaP1 = 0;
        int sum = 0;
        int i = 0;
        while (i < listaDecolagemPista1.pegarTotalElementos() && !listaDecolagemPista1.ehVazia()) {

            Decolagem temp = listaDecolagemPista1.pegar(i);

            int esperaDecolagem = temp.getSaida() - temp.getChegada();

            sum += esperaDecolagem;

            i++;
        }
        if (listaDecolagemPista1.pegarTotalElementos() > 0) {
            mediaP1 = sum / listaDecolagemPista1.pegarTotalElementos();
        }

        int mediaP2 = 0;
        sum = 0;
        i = 0;
        while (i < listaDecolagemPista2.pegarTotalElementos() && !listaDecolagemPista2.ehVazia()) {

            Decolagem temp = listaDecolagemPista2.pegar(i);

            int esperaDecolagem = temp.getSaida() - temp.getChegada();

            sum += esperaDecolagem;

            i++;
        }
        if (listaDecolagemPista2.pegarTotalElementos() > 0) {
            mediaP2 = sum / listaDecolagemPista2.pegarTotalElementos();
        }

        int mediaPE = 0;
        sum = 0;
        i = 0;
        while (i < listaDecolagemPistaEmergencia.pegarTotalElementos() && !listaDecolagemPistaEmergencia.ehVazia()) {

            Decolagem temp = listaDecolagemPistaEmergencia.pegar(i);

            int esperaDecolagem = temp.getSaida() - temp.getChegada();

            sum += esperaDecolagem;

            i++;
        }
        if (listaDecolagemPistaEmergencia.pegarTotalElementos() > 0) {
            mediaPE = sum / listaDecolagemPistaEmergencia.pegarTotalElementos();
        }

        mediaDecolagem = (double) ((mediaP1 + mediaP2 + mediaPE) / 3);

        return mediaDecolagem;
    }

    private void escolherProcedimento() {

        escolherProcedimentoPista1();
        escolherProcedimentoPista2();

        if (!filaPEmergencia.ehVazia()) {
            decolarPistaDeEmergencia(filaPEmergencia);
        }
    }

    private boolean avaliarPousoDeEmergeniaP1() {

        int i = 0;
        while (i < filaP1Aterrissagem1.pegarTamanho() && !filaP1Aterrissagem1.ehVazia()) {

            Aviao temp = filaP1Aterrissagem1.pegar(i);

            if (temp.getNivelCombustivel() == 0) {
                return true;
            }

            i++;
        }

        i = 0;
        while (i < filaP1Aterrissagem2.pegarTamanho() && !filaP1Aterrissagem2.ehVazia()) {

            Aviao temp = filaP1Aterrissagem2.pegar(i);

            if (temp.getNivelCombustivel() == 0) {

                return true;
            }

            i++;
        }
        return false;
    }

    private void escolherProcedimentoPista1() {

        int tamanhoP1FA1 = filaP1Aterrissagem1.pegarTamanho();
        int tamanhoP1FA2 = filaP1Aterrissagem2.pegarTamanho();
        int tamanhoP1FD = filaP1Decolagem.pegarTamanho();

        if (!filaP1Aterrissagem1.ehVazia() && tamanhoP1FA1 >= tamanhoP1FA2 && tamanhoP1FA1 > tamanhoP1FD) {

            pousarPista1(filaP1Aterrissagem1);

        } else if (!filaP1Aterrissagem2.ehVazia() && tamanhoP1FA2 >= tamanhoP1FA1 && tamanhoP1FA2 >= tamanhoP1FD) {

            pousarPista1(filaP1Aterrissagem2);

        } else if (!filaP1Decolagem.ehVazia() && tamanhoP1FD > tamanhoP1FA1 && tamanhoP1FD >= tamanhoP1FA2) {

            decolarPista1(filaP1Decolagem);

        }

    }

    private void escolherProcedimentoPista2() {

        int tamanhoP2FA1 = filaP2Aterrissagem1.pegarTamanho();
        int tamanhoP2FA2 = filaP2Aterrissagem2.pegarTamanho();
        int tamanhoP2FD = filaP2Decolagem.pegarTamanho();

        if (!filaP2Aterrissagem1.ehVazia() && tamanhoP2FA1 >= tamanhoP2FA2 && tamanhoP2FA1 > tamanhoP2FD) {

            pousarPista2(filaP2Aterrissagem1);

        } else if (!filaP2Aterrissagem2.ehVazia() && tamanhoP2FA2 >= tamanhoP2FA1 && tamanhoP2FA2 >= tamanhoP2FD) {

            pousarPista2(filaP2Aterrissagem2);

        } else if (!filaP2Decolagem.ehVazia() && tamanhoP2FD > tamanhoP2FA1 && tamanhoP2FD >= tamanhoP2FA2) {

            decolarPista2(filaP2Decolagem);
        }

    }


    private void decolarPista1(Fila<Aviao> fila) {

        Aviao temp = fila.pegaPrimeiro();

        int chegada = contadorDeRodadas - temp.getTempoNaFila();

        Decolagem dcl = new Decolagem(chegada, contadorDeRodadas, temp);

        pista1.getListaDeDecolagem().adicionarNoFinal(dcl);

        fila.poll();

        System.out.println("Aviao " + temp.getIdAviao() + " Decolou na pista 1");

    }

    private void decolarPista2(Fila<Aviao> fila) {

        Aviao temp = fila.pegaPrimeiro();

        int chegada = contadorDeRodadas - temp.getTempoNaFila();

        Aterrisagem dcl = new Aterrisagem(chegada, contadorDeRodadas, temp);

        pista2.getListaDeDecolagem().adicionarNoFinal(dcl);

        fila.poll();

        System.out.println("Aviao " + temp.getIdAviao() + " Decolou na pista 2");

    }

    private void decolarPistaDeEmergencia(Fila<Aviao> fila) {

        Aviao temp = fila.pegaPrimeiro();

        int chegada = contadorDeRodadas - temp.getTempoNaFila();

        Decolagem atr = new Decolagem(chegada, contadorDeRodadas, temp);

        pistaEmergencia.getListaDecolagem().adicionarNoFinal(atr);

        fila.poll();

        System.out.println("Aviao " + temp.getIdAviao() + " Decolou na pista de emergência");
    }

    private void pousarPista1(Fila<Aviao> filaAterrissagem) {

        Aviao temp = filaAterrissagem.pegaPrimeiro();

        int chegada = contadorDeRodadas - temp.getTempoNaFila();

        Aterrisagem atr = new Aterrisagem(chegada, contadorDeRodadas, temp);

        pista1.getListaDeAterrisagem().adicionarNoFinal(atr);

        filaAterrissagem.poll();

        System.out.println("Aviao " + temp.getIdAviao() + " Pousou na pista 1");

    }

    private void pousarPista2(Fila<Aviao> filaAterrissagem) {

        Aviao temp = filaAterrissagem.pegaPrimeiro();

        int chegada = contadorDeRodadas - temp.getTempoNaFila();

        Aterrisagem atr = new Aterrisagem(chegada, contadorDeRodadas, temp);

        pista2.getListaDeAterrisagem().adicionarNoFinal(atr);

        filaAterrissagem.poll();

        System.out.println("Aviao " + temp.getIdAviao() + " Pousou na pista 2");

    }

    private void aumentarTempoNaFila() {

        List<Aviao> avioesAterrissagem = aeroporto.pegarTodosAvioesAterrissagem();

        List<Aviao> avioesDecolagem = aeroporto.pegarTodosAvioesDecolagem();

        avioesAterrissagem.stream().forEach(a -> a.setTempoNaFila(a.getTempoNaFila() + 1));
        avioesDecolagem.stream().forEach(a -> a.setTempoNaFila(a.getTempoNaFila() + 1));

    }

    private void diminuirCombustivel() {

        List<Aviao> avioes = aeroporto.pegarTodosAvioesAterrissagem();

        avioes.stream().forEach(a -> a.setNivelCombustivel(a.getNivelCombustivel() - 1));
    }

    private void alocarAvioesAterrissagem(List<Aviao> avioes) {

        System.out.println(avioes.size() + " aviões chegaram Para aterrissagem");

        for (Aviao a : avioes) {

            Fila<Aviao> menorFilaPista1 = aeroporto.getPista1().pegarMenorFilaAterrissagem();
            Fila<Aviao> menorFilaPista2 = aeroporto.getPista2().pegarMenorFilaAterrissagem();

            int tamanhoMenorFilaPista1 = aeroporto.getPista1().pegarMenorFilaAterrissagem().pegarTamanho();
            var tamanhoMenorFilaPista2 = aeroporto.getPista2().pegarMenorFilaAterrissagem().pegarTamanho();

            if (tamanhoMenorFilaPista1 >= tamanhoMenorFilaPista2) {

                menorFilaPista2.adicionar(a);

            } else {

                menorFilaPista1.adicionar(a);
            }

            if (filaP1Aterrissagem1.contem(a)) {
                System.out.println("- Aviao " + a.getIdAviao() + " foi alocado na Fila de aterrissagem 1 da Pista 1");
            }
            if (filaP1Aterrissagem2.contem(a)) {
                System.out.println("- Aviao " + a.getIdAviao() + " foi alocado na Fila de aterrissagem 2 da Pista 1");
            }
            if (filaP2Aterrissagem1.contem(a)) {
                System.out.println("- Aviao " + a.getIdAviao() + " foi alocado na Fila de aterrissagem 1 da Pista 2");
            }
            if (filaP2Aterrissagem2.contem(a)) {
                System.out.println("- Aviao " + a.getIdAviao() + " foi alocado na Fila de aterrissagem 2 da Pista 2");
            }
        }


    }

    private void alocarAvioesDecolagem(List<Aviao> avioes) {

        System.out.println(avioes.size() + " aviões chegaram para decolagem");

        for (Aviao a : avioes) {

            List<Fila<Aviao>> filas = List.of(filaP1Decolagem, filaP2Decolagem, filaPEmergencia);

            Fila<Aviao> menorFila = filas.stream()
                    .min(Comparator.comparingInt(Fila::pegarTamanho))
                    .orElse(null);

            menorFila.adicionar(a);

            if (filaP1Decolagem.contem(a)) {
                System.out.println("- Aviao " + a.getIdAviao() + " foi alocado para decolagem na Pista 1");
            }
            if (filaP2Decolagem.contem(a)) {
                System.out.println("- Aviao " + a.getIdAviao() + " foi alocado para decolagem na Pista 2");
            }
            if (filaPEmergencia.contem(a)) {
                System.out.println("- Aviao " + a.getIdAviao() + " foi alocado para decolagem na Pista de emergência");
            }
        }

    }


    private List<Aviao> criarAvioes() {

        int i = 0;
        int numeroDeAvioesChegando = RandomPicker.getRandomNumber(3);
        List<Aviao> avioesChegando = new ArrayList<>();

        while (i <= numeroDeAvioesChegando) {
            Aviao novoAviao = new Aviao(idIterator);
            avioesChegando.add(novoAviao);
            i++;
            idIterator++;
        }
        return avioesChegando;
    }

    private Pista iniciarPista() {

        return new Pista();
    }

    private PistaEmergencia iniciarPistaEmergencia() {

        return new PistaEmergencia();
    }

    private Aeroporto iniciarAeroporto() {

        return new Aeroporto(iniciarPista(), iniciarPista(), iniciarPistaEmergencia());
    }

    private void imprimirConteudoFilas() {

        int tamanhoP1Aterrissagem1 = filaP1Aterrissagem1.pegarTamanho();
        int tamanhoP1Aterrissagem2 = filaP1Aterrissagem2.pegarTamanho();
        int tamanhoP1Decolagem = filaP1Decolagem.pegarTamanho();
        int tamanhoP2Aterrissagem1 = filaP2Aterrissagem1.pegarTamanho();
        int tamanhoP2Aterrissagem2 = filaP2Aterrissagem2.pegarTamanho();
        int tamanhoP2Decolagem = filaP2Decolagem.pegarTamanho();
        int tamanhoFilaEmergencia = filaPEmergencia.pegarTamanho();


        System.out.println("================================================================ Pista1");
        System.out.println(" ");
        System.out.println("[Aterrissagem fila 1] -> " + tamanhoP1Aterrissagem1 + " aviões na fila");
        int j = 0;

        if (tamanhoP1Aterrissagem1 > 0) {

            while (j < tamanhoP1Aterrissagem1) {

                Aviao temp = (Aviao) filaP1Aterrissagem1.pegar(j);

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

        System.out.println(" ");
        System.out.println("[Aterrissagem fila 2] -> " + tamanhoP1Aterrissagem2 + " aviões na fila");
        j = 0;

        if (tamanhoP1Aterrissagem2 > 0) {

            while (j < tamanhoP1Aterrissagem2) {

                Aviao temp = (Aviao) filaP1Aterrissagem2.pegar(j);

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

        System.out.println(" ");
        System.out.println("[Decolagem] -> " + tamanhoP1Decolagem + " aviões na fila");
        j = 0;

        if (tamanhoP1Decolagem > 0) {

            while (j < tamanhoP1Decolagem) {

                Aviao temp = (Aviao) filaP1Decolagem.pegar(j);

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

        System.out.println(" ");
        System.out.println(" ");
        System.out.println("================================================================  Pista2");
        System.out.println("[Aterrissagem fila 1] -> " + tamanhoP2Aterrissagem1 + " aviões na fila");
        j = 0;

        if (tamanhoP2Aterrissagem1 > 0) {

            while (j < tamanhoP2Aterrissagem1) {

                Aviao temp = (Aviao) filaP2Aterrissagem1.pegar(j);

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

        System.out.println(" ");
        System.out.println("[Aterrissagem fila 2] -> " + tamanhoP2Aterrissagem2 + " aviões na fila");
        j = 0;

        if (tamanhoP2Aterrissagem2 > 0) {

            while (j < tamanhoP2Aterrissagem2) {

                Aviao temp = (Aviao) filaP2Aterrissagem2.pegar(j);

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

        System.out.println(" ");
        System.out.println("[Decolagem] -> " + tamanhoP2Decolagem + " aviões na fila");
        j = 0;

        if (tamanhoP2Decolagem > 0) {

            while (j < tamanhoP2Decolagem) {

                Aviao temp = (Aviao) filaP2Decolagem.pegar(j);

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

        System.out.println("");
        System.out.println("==================================== Pista de emergência");
        System.out.println("[Decolagem] -> " + tamanhoFilaEmergencia + " aviões na fila");
        j = 0;

        if (tamanhoFilaEmergencia > 0) {

            while (j < tamanhoFilaEmergencia) {

                Aviao temp = (Aviao) filaPEmergencia.pegar(j);

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


}
