package homework;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {

	private StanzaBloccata stanzaBloccata;
	private Stanza stanzaNord;

	@BeforeEach
	void setUp() {
		stanzaBloccata = new StanzaBloccata("corridoio", "nord", "chiave");
		stanzaNord = new Stanza("nord");
		stanzaBloccata.impostaStanzaAdiacente("nord", stanzaNord);
	}

	@Test
	void testDirezioneBloccataSenzaChiave() {
		assertEquals(stanzaBloccata, stanzaBloccata.getStanzaAdiacente("nord"));
	}

	@Test
	void testDirezioneBloccataConChiave() {
		stanzaBloccata.addAttrezzo(new Attrezzo("chiave", 1));
		assertEquals(stanzaNord, stanzaBloccata.getStanzaAdiacente("nord"));
	}

	@Test
	void testDirezioneNonBloccataPassaSempre() {
		Stanza stanzaSud = new Stanza("sud");
		stanzaBloccata.impostaStanzaAdiacente("sud", stanzaSud);
		assertEquals(stanzaSud, stanzaBloccata.getStanzaAdiacente("sud"));
	}

	@Test
	void testDirezioneNonBloccataPassaAncheSenzaChiave() {
		Stanza stanzaEst = new Stanza("est");
		stanzaBloccata.impostaStanzaAdiacente("est", stanzaEst);
		assertEquals(stanzaEst, stanzaBloccata.getStanzaAdiacente("est"));
	}

	@Test
	void testDescrizioneContieneDirezioneBloccata() {
		assertTrue(stanzaBloccata.getDescrizione().contains("nord"));
	}

	@Test
	void testDescrizioneContieneSbloccante() {
		assertTrue(stanzaBloccata.getDescrizione().contains("chiave"));
	}

	@Test
	void testDescrizioneContieneNomeStanza() {
		assertTrue(stanzaBloccata.getDescrizione().contains("corridoio"));
	}
}
