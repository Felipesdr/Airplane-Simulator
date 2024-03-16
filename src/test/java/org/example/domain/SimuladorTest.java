package org.example.domain;

import org.example.util.Fila;
import org.example.util.ListaLigada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class SimuladorTest {

    Simulador simulador;
    private Aviao aviao1;
    private Aviao aviao2;
    private Aviao aviao3;
    private Aviao aviao4;
    List<Aviao> avioes1;
    List<Aviao> avioes2;
    List<Aviao> avioes3;
    List<Aviao> avioes4;
    Fila<Aviao> filaP1Decolagem;
    Fila<Aviao> filaP2Decolagem;
    Fila<Aviao> filaPEmergencia;
    List<Fila<Aviao>> filas = new ArrayList<>();

    @BeforeEach
    void setUp() {
        aviao1 = new Aviao(1L);
        aviao2 = new Aviao(2L);
        aviao3 = new Aviao(3L);
        aviao4 = new Aviao(4L);
        simulador = new Simulador();
        avioes1 = simulador.criarAvioes();
        avioes2 = simulador.criarAvioes();
        avioes3 = simulador.criarAvioes();
        avioes4 = simulador.criarAvioes();
        filaP1Decolagem = simulador.iniciarPista().getFilaDecolagem();
        filaP2Decolagem = simulador.iniciarPista().getFilaDecolagem();
        filaPEmergencia = simulador.iniciarPista().getFilaDecolagem();
        filas = List.of(filaP1Decolagem, filaP2Decolagem, filaPEmergencia);
    }

    @Test
    void iniciarSimulador() {
    }

    @Test
    void cairAviao() {
    }

    @Test
    void calcularMediaAterrissagem() {
    }

    @Test
    void calcularMediaDecolagem() {
    }

    @Test
    void escolherProcedimento() {
    }

    @Test
    void avaliarPousoOuDescolagem() {
    }

    @Test
    void avaliarPousoDeEmergencia() {
    }

    @Test
    void escolherPistaPousoEmergencia() {
    }

    @Test
    void pousoEmergencia() {
    }

    @Test
    void pousoEmergenciaPistaemergencia() {
    }

    @Test
    void decolar() {
    }

    @Test
    void decolarPistaEmergencia() {
    }

    @Test
    void pousar() {
    }

    @Test
    void aumentarTempoNaFila() {
    }

    @Test
    void diminuirCombustivel() {
    }

    @Test
    void alocarAvioesAterrissagem() {


    }

    @Test
    void testAlocarAvioesDecolagem() {

        filaP1Decolagem.adicionar(aviao1);
        filaP1Decolagem.adicionar(aviao2);
        filaPEmergencia.adicionar(aviao3);
        List<Aviao> avioesChegando = List.of(aviao4);

        simulador.alocarAvioesDecolagem(avioesChegando, filas);

        System.out.println(filaP1Decolagem.pegarTamanho());
        System.out.println(filaP2Decolagem.pegarTamanho());
        System.out.println(filaPEmergencia.pegarTamanho());

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