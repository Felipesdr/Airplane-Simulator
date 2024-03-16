import static org.junit.jupiter.api.Assertions.*;

import org.example.util.Fila;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilaTest {

	Fila fila;

	@BeforeEach
	void inicializaFila() {
		fila = new Fila();
	}

	@Test
	void testAdicionarFilaVazia() {

		fila.adicionar("fernando");

		assertEquals(1, fila.pegarTamanho());
		assertTrue(fila.contem("fernando"));
	}

	@Test
	void testAdicionarFilaComElementos() {

		fila.adicionar("fernando");
		fila.adicionar("maria");
		fila.adicionar("carlos");

		assertEquals(3, fila.pegarTamanho());
		assertEquals("carlos", fila.pegaUltimo());
		assertTrue(fila.contem("carlos"));
	}

	@Test
	void testPullComFilaVazia() {
		assertNull(fila.poll());
		assertEquals(0, fila.pegarTamanho());

	}

	@Test
	void testPullComFilaComElementos() {
		fila.adicionar("fernando");
		fila.adicionar("maria");
		fila.adicionar("didi");

		assertEquals("fernando", fila.poll());
		assertFalse(fila.contem("fernando"));
		assertEquals(2, fila.pegarTamanho());

	}
}
