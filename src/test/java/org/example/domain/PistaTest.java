package org.example.domain;

import org.example.util.Fila;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PistaTest {

    @Test
    void pegarFilasAterrissagemSuccess() {

        Pista pista = new Pista();

        List<Fila<Aviao>> filasAterrissagem = pista.pegarFilasAterrissagem();

        assertEquals(2, filasAterrissagem.size());

        assertTrue(filasAterrissagem.get(0) instanceof Fila);
        assertTrue(filasAterrissagem.get(1) instanceof Fila);
    }
}

