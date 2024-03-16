package org.example.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AviaoTest {

    @Test
    public void testAviaoSuccess() {

        Long idAviao = 1L;
        Aviao aviao = new Aviao(idAviao);

        assertEquals(idAviao, aviao.getIdAviao());
        assertNotNull(aviao.getNivelCombustivel());
        assertTrue(aviao.getNivelCombustivel() > 0 && aviao.getNivelCombustivel() <= 20);
        assertEquals(0, aviao.getTempoNaFila());
    }

}