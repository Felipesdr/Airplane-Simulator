package org.example.domain;

import org.example.util.Fila;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimuladorTest {
    Aeroporto aeroporto;
    Simulador simulador;
    Pista pista1;
    Pista pista2;
    PistaEmergencia pistaEmergencia;
    private Aviao aviao1;
    private Aviao aviao2;
    private Aviao aviao3;
    private Aviao aviao4;
    private Aviao aviao5;
    List<Aviao> avioes1;
    List<Aviao> avioes2;
    List<Aviao> avioes3;
    List<Aviao> avioes4;
    Fila<Aviao> filaP1Decolagem;
    Fila<Aviao> filaP2Decolagem;
    Fila<Aviao> filaPEmergencia;
    Fila<Aviao> filaP1Aterrissagem1;
    Fila<Aviao> filaP1Aterrissagem2;
    Fila<Aviao> filaP2Aterrissagem1;
    Fila<Aviao> filaP2Aterrissagem2;

    @BeforeEach
    void setUp() {
        simulador = new Simulador();
        aeroporto = simulador.iniciarAeroporto();
        pista1 = aeroporto.getPista1();
        pista2 = aeroporto.getPista2();
        pistaEmergencia = aeroporto.getPistaEmergencia();
        aviao1 = new Aviao(1L);
        aviao2 = new Aviao(2L);
        aviao3 = new Aviao(3L);
        aviao4 = new Aviao(4L);
        aviao5 = new Aviao(5L);
        avioes1 = simulador.criarAvioes();
        avioes2 = simulador.criarAvioes();
        avioes3 = simulador.criarAvioes();
        avioes4 = simulador.criarAvioes();
        filaP1Decolagem = pista1.getFilaDecolagem();
        filaP2Decolagem = pista2.getFilaDecolagem();
        filaPEmergencia = pistaEmergencia.getFilaDeDecolagem();
        filaP1Aterrissagem1 = pista1.getFilaAterrisagem1();
        filaP1Aterrissagem2 = pista1.getFilaAterrisagem2();
        filaP2Aterrissagem1 = pista2.getFilaAterrisagem1();
        filaP2Aterrissagem2 = pista2.getFilaAterrisagem2();
    }

    @Test
    void testIniciarSimulador() {
    }

    @Test
    void testCairAviao() {
    }

    @Test
    void testCalcularMediaAterrissagem() {
    }

    @Test
    void testCalcularMediaDecolagem() {
    }

    @Test
    void testEscolherProcedimento() {


    }

    @Test
    void testAvaliarPousoOuDescolagem() {

        filaP1Aterrissagem1.adicionar(aviao1);
        filaP1Aterrissagem1.adicionar(aviao2);
        filaP1Aterrissagem2.adicionar(aviao3);
        filaP1Decolagem.adicionar(aviao4);

        simulador.avaliarPousoOuDescolagem(pista1, filaP1Aterrissagem1, filaP1Aterrissagem2, filaP1Decolagem);

        assertEquals(1, filaP1Aterrissagem1.pegarTamanho());
        assertEquals(2, filaP1Aterrissagem1.pegaPrimeiro().getIdAviao());
    }

    @Test
    void testAvaliarPousoDeEmergencia() {

        aviao1.setNivelCombustivel(20);
        filaP1Aterrissagem1.adicionar(aviao1);

        assertFalse(simulador.avaliarPousoDeEmergencia(aviao1, filaP1Aterrissagem1, pista1, 0));
    }

    @Test
    void testEscolherPistaPousoEmergencia() {

        aeroporto.getPista1().getFilaAterrisagem1().adicionar(aviao1);

        simulador.escolherPistaPousoEmergencia(aviao1, aeroporto.getPista1().getFilaAterrisagem1(), aeroporto.getPista1(), 1);

        assertEquals(1, aeroporto.getPista1().getListaDeAterrisagem().pegarTotalElementos());
    }

    @Test
    void testPousoEmergencia() {

        filaP1Aterrissagem1.adicionar(aviao1);
        filaP1Aterrissagem2.adicionar(aviao2);
        filaP1Aterrissagem1.adicionar(aviao3);

        simulador.pousoEmergencia(aviao2, filaP1Aterrissagem1, pista1, aeroporto.pegarNumeroPista(pista1));

        assertEquals(2, filaP1Aterrissagem1.pegarTamanho());
        assertEquals(1, pista1.getListaDeAterrisagem().pegarTotalElementos());
        assertEquals(2, pista1.getListaDeAterrisagem().pegar(0).getAviao().getIdAviao());
        assertFalse(filaPEmergencia.contem(aviao2));
    }

    @Test
    void testPousoEmergenciaPistaEmergencia() {

        filaPEmergencia.adicionar(aviao1);
        filaPEmergencia.adicionar(aviao2);
        filaPEmergencia.adicionar(aviao3);

        simulador.pousoEmergenciaPistaEmergencia(aviao2, filaPEmergencia, pistaEmergencia);

        assertEquals(2, filaPEmergencia.pegarTamanho());
        assertEquals(1, pistaEmergencia.getListaAterrisagemEmergencia().pegarTotalElementos());
        assertEquals(2, pistaEmergencia.getListaAterrisagemEmergencia().pegar(0).getAviao().getIdAviao());
        assertFalse(filaPEmergencia.contem(aviao2));
    }

    @Test
    void testDecolar() {

        filaP1Decolagem.adicionar(aviao1);

        simulador.decolar(filaP1Decolagem, pista1, aeroporto.pegarNumeroPista(pista1));

        assertEquals(0, filaP1Decolagem.pegarTamanho());
        assertEquals(1, pista1.getListaDeDecolagem().pegarTotalElementos());
        assertEquals(1, pista1.getListaDeDecolagem().pegar(0).getAviao().getIdAviao());
    }

    @Test
    void testDecolarPistaEmergencia() {

        filaPEmergencia.adicionar(aviao1);

        simulador.decolarPistaEmergencia(pistaEmergencia, filaPEmergencia);

        assertEquals(0, filaPEmergencia.pegarTamanho());
        assertEquals(1, pistaEmergencia.getListaDecolagem().pegarTotalElementos());
        assertEquals(1, pistaEmergencia.getListaDecolagem().pegar(0).getAviao().getIdAviao());
    }

    @Test
    void testPousar() {

        filaP1Aterrissagem1.adicionar(aviao1);

        simulador.pousar(filaP1Aterrissagem1, pista1, aeroporto.pegarNumeroPista(pista1));

        assertEquals(0, filaP1Aterrissagem1.pegarTamanho());
        assertEquals(1, pista1.getListaDeAterrisagem().pegarTotalElementos());
        assertEquals(1, pista1.getListaDeAterrisagem().pegar(0).getAviao().getIdAviao());
    }

    @Test
    void testAumentarTempoNaFila() {

        int tAviao1 = aviao1.getTempoNaFila();
        int tAviao2 = aviao2.getTempoNaFila();
        int tAviao3 = aviao3.getTempoNaFila();
        int tAviao4 = aviao4.getTempoNaFila();

        simulador.aumentarTempoNaFila(List.of(aviao1, aviao2, aviao3, aviao4));

        assertEquals(tAviao1 +1, aviao1.getTempoNaFila());
        assertEquals(tAviao2 +1, aviao2.getTempoNaFila());
        assertEquals(tAviao3 +1, aviao3.getTempoNaFila());
        assertEquals(tAviao4 +1, aviao4.getTempoNaFila());
    }

    @Test
    void testDiminuirCombustivel() {

        int cAviao1 = aviao1.getNivelCombustivel();
        int cAviao2 = aviao2.getNivelCombustivel();
        int cAviao3 = aviao3.getNivelCombustivel();
        int cAviao4 = aviao4.getNivelCombustivel();

        simulador.diminuirCombustivel(List.of(aviao1, aviao2, aviao3, aviao4));

        assertEquals(cAviao1 -1, aviao1.getNivelCombustivel());
        assertEquals(cAviao2 -1, aviao2.getNivelCombustivel());
        assertEquals(cAviao3 -1, aviao3.getNivelCombustivel());
        assertEquals(cAviao4 -1, aviao4.getNivelCombustivel());
    }

    @Test
    void testAlocarAvioesAterrissagem() {

        filaP1Aterrissagem1.adicionar(aviao1);
        filaP1Aterrissagem2.adicionar(aviao2);
        filaP2Aterrissagem1.adicionar(aviao3);
        filaP2Aterrissagem1.adicionar(aviao4);
        List<Aviao> avioesChegando = List.of(aviao5);

        simulador.alocarAvioesAterrissagem(avioesChegando, aeroporto.pegarTodasAsFilasAterrissagem());

        assertEquals(1, filaP2Aterrissagem2.pegarTamanho());
    }

    @Test
    void testAlocarAvioesDecolagem() {

        filaP1Decolagem.adicionar(aviao1);
        filaP1Decolagem.adicionar(aviao2);
        filaPEmergencia.adicionar(aviao3);
        List<Aviao> avioesChegando = List.of(aviao4);

        simulador.alocarAvioesDecolagem(avioesChegando, aeroporto.pegarTodasAsFilaDescolagem());
        assertEquals(1, filaP2Decolagem.pegarTamanho());
    }

    @Test
    void testCriarAvioes() {

        for (Aviao a : avioes1) {

            assertInstanceOf(Aviao.class, a);
        }

        assertTrue(avioes1.size() >= 0 && avioes1.size() <= 3);
        assertTrue(avioes2.size() >= 0 && avioes2.size() <= 3);
        assertTrue(avioes3.size() >= 0 && avioes3.size() <= 3);
        assertTrue(avioes4.size() >= 0 && avioes4.size() <= 3);
    }

    @Test
    void testIniciarPista() {

        assertNotNull(simulador.iniciarPista());
        assertInstanceOf(Pista.class, simulador.iniciarPista());
    }

    @Test
    void testPistaEmergencia() {

        assertNotNull(simulador.iniciarPistaEmergencia());
        assertInstanceOf(PistaEmergencia.class, simulador.iniciarPistaEmergencia());
    }

    @Test
    void testIniciarAeroporto() {

        Aeroporto aeroporto = simulador.iniciarAeroporto();

        assertNotNull(aeroporto.getPista1());
        assertNotNull(aeroporto.getPista2());
        assertNotNull(aeroporto.getPistaEmergencia());
    }
}