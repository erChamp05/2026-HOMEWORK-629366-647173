package homework;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {

	private StanzaBuia stanzaBuia;

	@BeforeEach
	void setUp() {
		stanzaBuia = new StanzaBuia("cantina", "lanterna");
	}

	@Test
	void testDescrizioneSenzaLanterna() {
		assertEquals("qui c'e' buio pesto", stanzaBuia.getDescrizione());
	}

	@Test
	void testDescrizioneConLanterna() {
		stanzaBuia.addAttrezzo(new Attrezzo("lanterna", 3));
		assertTrue(stanzaBuia.getDescrizione().contains("cantina"));
	}

	@Test
	void testDescrizioneNonBuiaPiuConLanterna() {
		stanzaBuia.addAttrezzo(new Attrezzo("lanterna", 3));
		assertFalse(stanzaBuia.getDescrizione().equals("qui c'e' buio pesto"));
	}

	@Test
	void testDescrizioneTornaOscuraDopoRimozioneLanterna() {
		Attrezzo lanterna = new Attrezzo("lanterna", 3);
		stanzaBuia.addAttrezzo(lanterna);
		stanzaBuia.removeAttrezzo(lanterna);
		assertEquals("qui c'e' buio pesto", stanzaBuia.getDescrizione());
	}

	@Test
	void testAltroAttrezzoNonIllumina() {
		stanzaBuia.addAttrezzo(new Attrezzo("torcia", 1));
		assertEquals("qui c'e' buio pesto", stanzaBuia.getDescrizione());
	}
}
