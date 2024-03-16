package org.example.domain;

import org.example.util.Fila;
import org.example.util.ListaLigada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void criarAvioes() {
    }

    @Test
    void iniciarPista() {

        assertNotNull(simulador.iniciarPista());
        assertInstanceOf(Pista.class, simulador.iniciarPista());
    }

    @Test
    void iniciarPistaEmergencia() {

        assertNotNull(simulador.iniciarPistaEmergencia());
        assertInstanceOf(PistaEmergencia.class, simulador.iniciarPistaEmergencia());
    }

    @Test
    void iniciarAeroporto() {

        Aeroporto aeroporto = simulador.iniciarAeroporto();

        assertNotNull(aeroporto.getPista1());
        assertNotNull(aeroporto.getPista2());
        assertNotNull(aeroporto.getPistaEmergencia());
    }
}