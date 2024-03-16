package org.example.domain;

import org.example.util.Fila;
import org.example.util.ListaLigada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class SimuladorTest {

    Simulador simulador;

    @BeforeEach
    void setUp() {

        simulador = new Simulador();

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
    void alocarAvioesDecolagem() {
    }

    @Test
    void testCriarAvioes() {

        List<Aviao> avioes1 = simulador.criarAvioes();
        List<Aviao> avioes2 = simulador.criarAvioes();
        List<Aviao> avioes3 = simulador.criarAvioes();
        List<Aviao> avioes4 = simulador.criarAvioes();

        for (Aviao a : avioes1){

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