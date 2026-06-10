package homework;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

class FabbricaDiComandiFisarmonicaTest {

	private FabbricaDiComandiFisarmonica fabbrica;

	@BeforeEach
	void setUp() {
		IO ioStub = new IO() {
			public void mostraMessaggio(String m) {}
			public String leggiRiga() { return ""; }
		};
		fabbrica = new FabbricaDiComandiFisarmonica(ioStub);
	}

	@Test
	void testVai() {
		assertEquals("vai", fabbrica.costruisciComando("vai nord").getNome());
	}

	@Test
	void testVaiParametro() {
		assertEquals("nord", fabbrica.costruisciComando("vai nord").getParametro());
	}

	@Test
	void testPrendi() {
		assertEquals("prendi", fabbrica.costruisciComando("prendi osso").getNome());
	}

	@Test
	void testPrendiParametro() {
		assertEquals("osso", fabbrica.costruisciComando("prendi osso").getParametro());
	}

	@Test
	void testPosa() {
		assertEquals("posa", fabbrica.costruisciComando("posa spada").getNome());
	}

	@Test
	void testGuarda() {
		assertEquals("guarda", fabbrica.costruisciComando("guarda").getNome());
	}

	@Test
	void testAiuto() {
		assertEquals("aiuto", fabbrica.costruisciComando("aiuto").getNome());
	}

	@Test
	void testFine() {
		assertEquals("fine", fabbrica.costruisciComando("fine").getNome());
	}

	@Test
	void testComandoSconosciuto() {
		assertEquals("non valido", fabbrica.costruisciComando("miaomiao").getNome());
	}

	@Test
	void testStringaVuota() {
		assertEquals("non valido", fabbrica.costruisciComando("").getNome());
	}

	@Test
	void testVaiSenzaParametro() {
		assertNull(fabbrica.costruisciComando("vai").getParametro());
	}
}
