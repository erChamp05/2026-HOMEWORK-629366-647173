package homework;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoNonValido;
import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.comandi.FabbricaComandiIntrospettiva;

class FabbricaComandiIntrospettivaTest {

	private FabbricaComandiIntrospettiva fabbrica;

	@BeforeEach
	void setUp() {
		fabbrica = new FabbricaComandiIntrospettiva(new IOSimulator());
	}

	@Test
	void testCostruisciComandoVai() {
		Comando c = fabbrica.costruisciComando("vai nord");
		assertTrue(c instanceof ComandoVai);
	}

	@Test
	void testComandoVaiParametro() {
		Comando c = fabbrica.costruisciComando("vai nord");
		assertEquals("nord", c.getParametro());
	}

	@Test
	void testComandoNonValido() {
		Comando c = fabbrica.costruisciComando("vola");
		assertTrue(c instanceof ComandoNonValido);
	}

	@Test
	void testComandoVuotoNonValido() {
		Comando c = fabbrica.costruisciComando("");
		assertTrue(c instanceof ComandoNonValido);
	}

	@Test
	void testNomeComandoVai() {
		Comando c = fabbrica.costruisciComando("vai sud");
		assertEquals("vai", c.getNome());
	}

	@Test
	void testNomeComandoGuarda() {
		Comando c = fabbrica.costruisciComando("guarda");
		assertEquals("guarda", c.getNome());
	}

	@Test
	void testNomiComandiNonVuoti() {
		assertFalse(FabbricaComandiIntrospettiva.getNomiComandi().isEmpty());
	}

	@Test
	void testNomiComandiContieneVai() {
		assertTrue(FabbricaComandiIntrospettiva.getNomiComandi().contains("vai"));
	}
}
