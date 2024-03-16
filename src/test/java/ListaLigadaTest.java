import static org.junit.jupiter.api.Assertions.*;

import org.example.util.ListaLigada;
import org.junit.jupiter.api.Test;

class ListaLigadaTest {

	@Test
	void testAdicionarNoComecoListaVazia() {

		ListaLigada lista = new ListaLigada();

		lista.adicionarNoComeco("Fernando");

		assertEquals(1, lista.pegarTotalElementos());
		assertEquals("Fernando", lista.pegarPrimeiro());
		assertEquals("Fernando", lista.pegarUltima());
	}

	@Test
	void testAdicionarNoComecoComElementos() {

		ListaLigada lista = new ListaLigada();

		lista.adicionarNoComeco("Fernando");
		lista.adicionarNoComeco("Maria");
		lista.adicionarNoComeco("Carlos");

		assertEquals(3, lista.pegarTotalElementos());
		assertEquals("Carlos", lista.pegarPrimeiro());
		assertEquals("Fernando", lista.pegarUltima());

	}

	@Test
	void testAdicionarNoFinalListaVazia() {
		
		ListaLigada lista = new ListaLigada();

		lista.adicionarNoFinal("Fernando");

		assertEquals(1, lista.pegarTotalElementos());
		assertEquals("Fernando", lista.pegarPrimeiro());
		assertEquals("Fernando", lista.pegarUltima());


	}

	@Test
	void testAdicionarNoFinalComElementos() {

		ListaLigada lista = new ListaLigada();

		lista.adicionarNoFinal("Fernando");
		lista.adicionarNoFinal("Maria");
		lista.adicionarNoFinal("Carlos");

		assertEquals(3, lista.pegarTotalElementos());
		assertEquals("Fernando", lista.pegarPrimeiro());
		assertEquals("Carlos", lista.pegarUltima());

	}
	
	@Test
	void testPegarNaPosicaoInvalidaNegativa() {
		
		ListaLigada lista = new ListaLigada();

		lista.adicionarNoFinal("Fernando");
		
		assertThrows(IllegalArgumentException.class, ()->lista.pegar(-1));
		
	}
	
	@Test
	void testPegarNaPosicaoInvalidaQuantidadeElementos() {
	
		ListaLigada lista = new ListaLigada();

		lista.adicionarNoFinal("Fernando");
		
		assertThrows(IllegalArgumentException.class, ()->lista.pegar(6));
	}
	
	@Test
	void testPegarNaPosicaoValida() {
		
		ListaLigada lista = new ListaLigada();

		lista.adicionarNoFinal("Fernando");
		lista.adicionarNoFinal("Maria");
		lista.adicionarNoFinal("Carlos");

		assertEquals("Fernando", lista.pegar(0));
		assertEquals("Maria", lista.pegar(1));
		assertEquals("Carlos", lista.pegar(2));
		
		
	}
	
	@Test
	void testAdicionarNaPosicaoInvalidaNegativa() {
		
		ListaLigada lista = new ListaLigada();
		
		lista.adicionarNoFinal("Carlos");
		
		assertThrows(IllegalArgumentException.class, ()->lista.adicinarNaPosicao("Fernando", -1));
		
	}
	
	@Test
	void testAdicionarNaPosicaoInvalidaQuantidadeElementos() {
	
		ListaLigada lista = new ListaLigada();

		lista.adicionarNoFinal("Carlos");
		
		assertThrows(IllegalArgumentException.class, ()->lista.adicinarNaPosicao("Fernando", 2));
		
	}
	
	@Test
	void testAdicionarNaPosicaoValida() {
		
		ListaLigada lista = new ListaLigada();

		lista.adicionarNoFinal("Fernando");
		lista.adicionarNoFinal("Maria");
		lista.adicionarNoFinal("Carlos");

		lista.adicinarNaPosicao("Rose", 1);
		
		assertEquals("Fernando", lista.pegar(0));
		assertEquals("Rose", lista.pegar(1));
		assertEquals("Maria", lista.pegar(2));
		assertEquals("Carlos", lista.pegar(3));
		
	}
	
	@Test
	void testContemListaVazia() {
		
		ListaLigada lista = new ListaLigada();
		
		assertFalse(lista.contem("Mauro"));
		
	}
	
	@Test
	void testContemNaoAcharLista() {
		
		ListaLigada lista = new ListaLigada();

		lista.adicionarNoFinal("Fernando");
		lista.adicionarNoFinal("Maria");
		lista.adicionarNoFinal("Carlos");
		
		assertFalse(lista.contem("Mauro"));
		
	}
	
	@Test
	void testContemAcharLista() {
		
		ListaLigada lista = new ListaLigada();

		lista.adicionarNoFinal("Fernando");
		lista.adicionarNoFinal("Maria");
		lista.adicionarNoFinal("Carlos");
		
		assertTrue(lista.contem("Maria"));
		
		
	}
	
	@Test
	void testRemoveDoComecoListaVazia() {

		ListaLigada lista = new ListaLigada();

		assertThrows(RuntimeException.class, ()-> lista.removerDoComeco());
		
	}
	
	@Test
	void testRemoveDoComecoComElementos() {

		ListaLigada lista = new ListaLigada();

		lista.adicionarNoComeco("Fernando");
		lista.adicionarNoComeco("Maria");
		lista.adicionarNoComeco("Carlos");
		
		lista.removerDoComeco();
		
		assertEquals(2, lista.pegarTotalElementos());
		assertEquals("Maria", lista.pegarPrimeiro());
		assertEquals("Fernando", lista.pegarUltima());

	}
	
	@Test
	void testRemoveDoComecoComApenasUmElemento() {

		ListaLigada lista = new ListaLigada();

		lista.adicionarNoComeco("Fernando");
		
		
		lista.removerDoComeco();
		
		assertEquals(0, lista.pegarTotalElementos());
		assertTrue(lista.primeiroEhNulo());
		assertTrue(lista.ultimoEhNulo());

	}
	
	@Test
	void testRemoveDoFinalListaVazia() {

		ListaLigada lista = new ListaLigada();

		assertThrows(RuntimeException.class, ()-> lista.removerDoFinal());
		
	}
	
	@Test
	void testRemoveDoFinalComElementos() {

		ListaLigada lista = new ListaLigada();

		lista.adicionarNoFinal("Fernando");
		lista.adicionarNoFinal("Maria");
		lista.adicionarNoFinal("Carlos");
		
		lista.removerDoFinal();
		
		assertEquals(2, lista.pegarTotalElementos());
		assertEquals("Fernando", lista.pegarPrimeiro());
		assertEquals("Maria", lista.pegarUltima());

	}
	
	@Test
	void testRemoveDoFinalComApenasUmElemento() {

		ListaLigada lista = new ListaLigada();

		lista.adicionarNoFinal("Fernando");
		
		lista.removerDoFinal();
		
		assertEquals(0, lista.pegarTotalElementos());
		assertTrue(lista.primeiroEhNulo());
		assertTrue(lista.ultimoEhNulo());

	}
	
	@Test
	void testRemoverNaPosicaoInvalidaNegativa() {
		
		ListaLigada lista = new ListaLigada();
		
		lista.adicionarNoFinal("Carlos");
		
		assertThrows(IllegalArgumentException.class, ()->lista.removerNaPosicao(-1));
		
	}
	
	@Test
	void testRemoverNaPosicaoInvalidaQuantidadeElementos() {
	
		ListaLigada lista = new ListaLigada();

		lista.adicionarNoFinal("Carlos");
		
		assertThrows(IllegalArgumentException.class, ()->lista.removerNaPosicao(2));
		
	}
	
	@Test
	void testRemoverNaPosicaoValida() {
		
		ListaLigada lista = new ListaLigada();

		lista.adicionarNoFinal("Fernando");
		lista.adicionarNoFinal("Maria");
		lista.adicionarNoFinal("Carlos");

		lista.removerNaPosicao(1);
		
		assertEquals(2, lista.pegarTotalElementos());
		assertEquals("Fernando", lista.pegar(0));
		assertEquals("Carlos", lista.pegar(1));
		
	}
	
	@Test
	void testRemoverNaPosicaoValidaApenasUmElemento() {
		
		ListaLigada lista = new ListaLigada();

		lista.adicionarNoFinal("Fernando");

		lista.removerNaPosicao(0);
		
		assertEquals(0, lista.pegarTotalElementos());
		assertTrue(lista.primeiroEhNulo());
		assertTrue(lista.ultimoEhNulo());
		
	}

}
