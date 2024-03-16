package org.example.domain;

import org.example.util.Fila;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AeroportoTest {

    private Aviao aviao1;
    private Aviao aviao2;
    private Aviao aviao3;
    private Aviao aviao4;

    private  Pista pista1;
    private Pista pista2;
    private PistaEmergencia pistaEmergencia;
    private Aeroporto aeroporto;



    @BeforeEach
    void setUp() {

        aviao1 = new Aviao(1L);
        aviao2 = new Aviao(2L);
        aviao3 = new Aviao(3L);
        aviao4 = new Aviao(4L);

        pista1 = new Pista();
        pista2 = new Pista();
        pistaEmergencia = new PistaEmergencia();

        aeroporto = new Aeroporto(pista1, pista2, pistaEmergencia);
    }

    @Test
    void pegarTodosAvioesAterrissagem() {

        pista1.getFilaAterrisagem1().adicionar(aviao1);
        pista1.getFilaAterrisagem2().adicionar(aviao2);
        pista2.getFilaAterrisagem1().adicionar(aviao3);
        pista2.getFilaAterrisagem2().adicionar(aviao4);

        List<Aviao> avioes = aeroporto.pegarTodosAvioesAterrissagem();

        assertEquals(4, avioes.size());
        assertEquals(aviao1, avioes.get(0));
        assertEquals(aviao2, avioes.get(1));
        assertEquals(aviao3, avioes.get(2));
        assertEquals(aviao3, avioes.get(2));
    }

    @Test
    void pegarTodosAvioesDecolagem() {

        pista1.getFilaDecolagem().adicionar(aviao1);
        pista2.getFilaDecolagem().adicionar(aviao2);
        pista2.getFilaDecolagem().adicionar(aviao3);
        pistaEmergencia.getFilaDeDecolagem().adicionar(aviao4);

        List<Aviao> avioes = aeroporto.pegarTodosAvioesDecolagem();

        assertEquals(4, avioes.size());
        assertEquals(aviao1, avioes.get(0));
        assertEquals(aviao2, avioes.get(1));
        assertEquals(aviao3, avioes.get(2));
        assertEquals(aviao3, avioes.get(2));
    }

    @Test
    void pegarNumeroPista() {
    }
}