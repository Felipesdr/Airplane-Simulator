package org.example.domain;

import org.example.util.Fila;
import org.example.util.ListaLigada;
import org.example.util.RandomPicker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Simulador {

    private final Aeroporto aeroporto;

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
    private Integer contadorPousosEmergencia = 0;

    public Simulador() {
        this.aeroporto = iniciarAeroporto();
        this.pista1 = aeroporto.getPista1();
        this.pista2 = aeroporto.getPista2();
        this.pistaEmergencia = aeroporto.getPistaEmergencia();
        this.filaP1Aterrissagem1 = aeroporto.getPista1().getFilaAterrisagem1();
        this.filaP1Aterrissagem2 = aeroporto.getPista1().getFilaAterrisagem2();
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

            diminuirCombustivel();
            cairAviao();
            aumentarTempoNaFila();

            System.out.println("====================== Estaticas ====================== ");
            double mediaDecolagem = calcularMediaDecolagem();
            System.out.println("Media de tempo para decolagem: " + mediaDecolagem);
            double mediaAterrissagem = calcularMediaAterrissagem();
            System.out.println("Media de tempo para aterrissagem: " + mediaAterrissagem);
            System.out.println("Pousos de emergência: " + contadorPousosEmergencia);
            System.out.println("Queda de aviões: " + contadorAvioesCaindo);

            System.out.println("====================== Chegada de avioes ====================== ");
            List<Aviao> avioesParaAterrisar = criarAvioes();
            alocarAvioesAterrissagem(avioesParaAterrisar);
            List<Aviao> avioesParaDecolar = criarAvioes();
            alocarAvioesDecolagem(avioesParaDecolar);
            imprimirConteudoFilas();

            System.out.println(" ");
            System.out.println("====================== Decolagens e aterrissagens ====================== ");
            escolherProcedimento();
            System.out.println(" ");

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
                i--;
                contadorAvioesCaindo++;
            }
            i++;
        }

        i = 0;
        while (i < filaP1Aterrissagem2.pegarTamanho() && !filaP1Aterrissagem2.ehVazia()) {

            Aviao temp = filaP1Aterrissagem2.pegar(i);

            if (temp.getNivelCombustivel() < 0) {

                filaP1Aterrissagem2.removerPorPosicao(i);
                i--;
                contadorAvioesCaindo++;
            }
            i++;
        }

        i = 0;
        while (i < filaP2Aterrissagem1.pegarTamanho() && !filaP2Aterrissagem1.ehVazia()) {

            Aviao temp = filaP2Aterrissagem1.pegar(i);

            if (temp.getNivelCombustivel() < 0) {

                filaP2Aterrissagem1.removerPorPosicao(i);
                i--;
                contadorAvioesCaindo++;
            }
            i++;
        }

        i = 0;
        while (i < filaP2Aterrissagem2.pegarTamanho() && !filaP2Aterrissagem2.ehVazia()) {

            Aviao temp = filaP2Aterrissagem2.pegar(i);

            if (temp.getNivelCombustivel() < 0) {

                filaP2Aterrissagem2.removerPorPosicao(i);
                i--;
                contadorAvioesCaindo++;
            }
            i++;
        }
    }

    public Double calcularMediaAterrissagem() {

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
        while (i < listaAterrissagemPista2.pegarTotalElementos() && !listaAterrissagemPista2.ehVazia()) {

            Aterrisagem temp = listaAterrissagemPista2.pegar(i);

            int esperaDecolagem = temp.getSaida() - temp.getChegada();

            sum += esperaDecolagem;

            i++;
        }
        if (listaAterrissagemPista2.pegarTotalElementos() > 0) {
            mediaP2 = sum / listaAterrissagemPista2.pegarTotalElementos();
        }

        int mediaPE = 0;
        sum = 0;
        i = 0;
        while (i < listaAterrissagemPistaEmergencia.pegarTotalElementos() && !listaAterrissagemPistaEmergencia.ehVazia()) {

            Aterrisagem temp = listaAterrissagemPistaEmergencia.pegar(i);

            int esperaDecolagem = temp.getSaida() - temp.getChegada();

            sum += esperaDecolagem;

            i++;
        }
        if (listaAterrissagemPistaEmergencia.pegarTotalElementos() > 0) {
            mediaPE = sum / listaAterrissagemPistaEmergencia.pegarTotalElementos();
        }

        mediaAterrissagem = (double) ((mediaP1 + mediaP2 + mediaPE) / 3);

        return mediaAterrissagem;
    }

    public Double calcularMediaDecolagem() {

        double mediaDecolagem;
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

    public void escolherProcedimento() {

        int i = 0;
        int contadorEmergencia = 0;

        if (!filaP1Aterrissagem1.ehVazia()) {

            if (contadorEmergencia < 2) {

                while (i < filaP1Aterrissagem1.pegarTamanho()) {


                    Aviao temp = filaP1Aterrissagem1.pegar(i);
                    if (avaliarPousoDeEmergencia(temp, filaP1Aterrissagem1, pista1, contadorEmergencia)) {

                        escolherPistaPousoEmergencia(temp, filaP1Aterrissagem1, pista1, contadorEmergencia);
                        contadorEmergencia++;
                    }
                    i++;
                }
            }

        }

        i = 0;
        if (!filaP1Aterrissagem2.ehVazia()) {

            if (contadorEmergencia < 2) {

                while (i < filaP1Aterrissagem2.pegarTamanho()) {

                    Aviao temp = filaP1Aterrissagem2.pegar(i);

                    if (avaliarPousoDeEmergencia(temp, filaP1Aterrissagem2, pista1, contadorEmergencia)) {

                        escolherPistaPousoEmergencia(temp, filaP1Aterrissagem2, pista1, contadorEmergencia);
                        contadorEmergencia++;
                    }
                    i++;
                }
            }

        }
        if (contadorEmergencia <= 1) {
            avaliarPousoOuDescolagem(pista1, filaP1Aterrissagem1, filaP1Aterrissagem2);
        }

        if (contadorEmergencia > 0) contadorEmergencia = 1;

        i = 0;
        if (!filaP2Aterrissagem1.ehVazia()) {

            if (contadorEmergencia < 2) {

                while (i < filaP2Aterrissagem1.pegarTamanho()) {

                    Aviao temp = filaP2Aterrissagem1.pegar(i);
                    if (avaliarPousoDeEmergencia(temp, filaP2Aterrissagem1, pista2, contadorEmergencia)) {

                        escolherPistaPousoEmergencia(temp, filaP2Aterrissagem1, pista2, contadorEmergencia);
                        contadorEmergencia++;

                    }
                    i++;
                }

            }

        }

        i = 0;
        if(!filaP2Aterrissagem2.ehVazia()){

            if(contadorEmergencia < 2){

                while (i < filaP2Aterrissagem2.pegarTamanho()) {


                    Aviao temp = filaP2Aterrissagem2.pegar(i);

                    if (avaliarPousoDeEmergencia(temp, filaP2Aterrissagem2, pista2, contadorEmergencia)) {

                        escolherPistaPousoEmergencia(temp, filaP2Aterrissagem2, pista2, contadorEmergencia);
                        contadorEmergencia++;
                    }
                    i++;

                }
            }
        }


        if (contadorEmergencia < 2) {

            avaliarPousoOuDescolagem(pista2, filaP2Aterrissagem1, filaP2Aterrissagem2);
        }

        if (contadorEmergencia == 0) {

            decolarPistaEmergencia();
        }


    }

    public void avaliarPousoOuDescolagem(Pista pista, Fila<Aviao> filaA1, Fila filaA2) {

        int tamanhoFA1 = filaA1.pegarTamanho();
        int tamanhoFA2 = filaA2.pegarTamanho();
        int tamanhoFD = pista.getFilaDecolagem().pegarTamanho();


        if (tamanhoFA1 >= tamanhoFA2 && tamanhoFA1 > tamanhoFD) {

            pousar(filaA1, pista, aeroporto.pegarNumeroPista(pista));

        } else if (tamanhoFA2 >= tamanhoFA1 && tamanhoFA2 > tamanhoFD) {

            pousar(filaA2, pista, aeroporto.pegarNumeroPista(pista));

        } else if (tamanhoFD >= tamanhoFA1 && tamanhoFD >= tamanhoFA2) {

            decolar(pista.getFilaDecolagem(), pista, aeroporto.pegarNumeroPista(pista));
        }

    }

    public boolean avaliarPousoDeEmergencia(Aviao aviao, Fila fila, Pista pista, int contadorEmergencia) {

        return (aviao.getNivelCombustivel() == 0);
    }

    public void escolherPistaPousoEmergencia(Aviao aviao, Fila<Aviao> fila, Pista pista, int contadorEmergencia) {

        switch (contadorEmergencia) {

            case (0):
                pousoEmergenciaPistaemergencia(aviao, fila);
                break;

            case (1):
                pousoEmergencia(aviao, fila, pista, aeroporto.pegarNumeroPista(pista));
                break;
        }
    }

    public void pousoEmergencia(Aviao aviao, Fila<Aviao> fila, Pista pista, int numeroPista) {

        int chegada = contadorDeRodadas - aviao.getTempoNaFila();

        Aterrisagem atr = new Aterrisagem(chegada, contadorDeRodadas, aviao);

        pista.getListaDeAterrisagem().adicionarNoFinal(atr);

        int posicao = fila.pegarPosicao(aviao);

        contadorPousosEmergencia++;

        fila.removerPorPosicao(posicao);

        System.out.println("Aviao " + aviao.getIdAviao() + " Realizou um pouso de emergencia na pista " + numeroPista);
    }

    public void pousoEmergenciaPistaemergencia(Aviao aviao, Fila<Aviao> fila) {

        int chegada = contadorDeRodadas - aviao.getTempoNaFila();

        Aterrisagem atr = new Aterrisagem(chegada, contadorDeRodadas, aviao);

        pistaEmergencia.getListaAterrisagemEmergencia().adicionarNoFinal(atr);

        contadorPousosEmergencia++;

        int posicao = fila.pegarPosicao(aviao);

        fila.removerPorPosicao(posicao);

        System.out.println("Aviao " + aviao.getIdAviao() + " Realizou um pouso de emergencia na pista na pista de emergencia ");
    }

    public void decolar(Fila<Aviao> fila, Pista pista, int numeroPista) {

        if (!fila.ehVazia()) {

            Aviao temp = fila.pegaPrimeiro();

            int chegada = contadorDeRodadas - temp.getTempoNaFila();

            Decolagem dcl = new Decolagem(chegada, contadorDeRodadas, temp);

            pista.getListaDeDecolagem().adicionarNoFinal(dcl);

            fila.poll();

            System.out.println("Aviao " + temp.getIdAviao() + " Decolou na pista " + numeroPista);
        }
    }

    public void decolarPistaEmergencia() {

        if (!filaPEmergencia.ehVazia()) {

            Aviao temp = filaPEmergencia.pegaPrimeiro();

            int chegada = contadorDeRodadas - temp.getTempoNaFila();

            Decolagem dcl = new Decolagem(chegada, contadorDeRodadas, temp);

            pistaEmergencia.getListaDecolagem().adicionarNoFinal(dcl);

            filaPEmergencia.poll();

            System.out.println("Aviao " + temp.getIdAviao() + " Decolou na pista na pista de emergencia");
        }
    }

    public void pousar(Fila<Aviao> fila, Pista pista, int numeroPista) {

        Aviao temp = fila.pegaPrimeiro();

        int chegada = contadorDeRodadas - temp.getTempoNaFila();

        Aterrisagem atr = new Aterrisagem(chegada, contadorDeRodadas, temp);

        pista.getListaDeAterrisagem().adicionarNoFinal(atr);

        fila.poll();

        System.out.println("Aviao " + temp.getIdAviao() + " Pousou na pista " + numeroPista);
    }

    public void aumentarTempoNaFila() {

        List<Aviao> avioesAterrissagem = aeroporto.pegarTodosAvioesAterrissagem();

        List<Aviao> avioesDecolagem = aeroporto.pegarTodosAvioesDecolagem();

        avioesAterrissagem.stream().forEach(a -> a.setTempoNaFila(a.getTempoNaFila() + 1));
        avioesDecolagem.stream().forEach(a -> a.setTempoNaFila(a.getTempoNaFila() + 1));

    }

    public void diminuirCombustivel() {

        List<Aviao> avioes = aeroporto.pegarTodosAvioesAterrissagem();

        avioes.stream().forEach(a -> a.setNivelCombustivel(a.getNivelCombustivel() - 1));
    }

    public void alocarAvioesAterrissagem(List<Aviao> avioes) {

        System.out.println(avioes.size() + " aviões chegaram Para aterrissagem");
        List<Fila<Aviao>> filasAterrissagem = List.of(filaP1Aterrissagem1, filaP1Aterrissagem2, filaP2Aterrissagem1, filaP2Aterrissagem2);

        Comparator<Fila<Aviao>> cFila = Comparator.comparingInt(Fila::pegarTamanho);
        for (Aviao a : avioes) {


            Fila<Aviao> menorFilaAterrissagem = filasAterrissagem.stream().min(cFila).orElse(null);

            menorFilaAterrissagem.adicionar(a);

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

    public void alocarAvioesDecolagem(List<Aviao> avioes) {

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

    public List<Aviao> criarAvioes() {

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

    public Pista iniciarPista() {

        return new Pista();
    }

    public PistaEmergencia iniciarPistaEmergencia() {

        return new PistaEmergencia();
    }

    public Aeroporto iniciarAeroporto() {

        return new Aeroporto(iniciarPista(), iniciarPista(), iniciarPistaEmergencia());
    }

    public void imprimirConteudoFilas() {

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

                Aviao temp = filaP1Aterrissagem1.pegar(j);

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

                Aviao temp = filaP1Aterrissagem2.pegar(j);

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

                Aviao temp = filaP1Decolagem.pegar(j);

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

                Aviao temp = filaP2Aterrissagem1.pegar(j);

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

                Aviao temp = filaP2Aterrissagem2.pegar(j);

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

                Aviao temp = filaP2Decolagem.pegar(j);

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

        System.out.println();
        System.out.println("==================================== Pista de emergência");
        System.out.println("[Decolagem] -> " + tamanhoFilaEmergencia + " aviões na fila");
        j = 0;

        if (tamanhoFilaEmergencia > 0) {

            while (j < tamanhoFilaEmergencia) {

                Aviao temp = filaPEmergencia.pegar(j);

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
