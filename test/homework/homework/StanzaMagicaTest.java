package homework;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaMagica;

class StanzaMagicaTest {

	private StanzaMagica magica;
	private Stanza nord;
	private Stanza est;
	private Stanza sud;

	@BeforeEach
	void setUp() {
		magica = new StanzaMagica("magica");
		nord = new Stanza("nord");
		est  = new Stanza("est");
		sud  = new Stanza("sud");
		magica.impostaStanzaAdiacente("nord", nord);
		magica.impostaStanzaAdiacente("est",  est);
		magica.impostaStanzaAdiacente("sud",  sud);
	}

	@Test
	void testVaiNordRitornaEst() {
		// scarto magico = 1: nord(0) → est(1)
		assertEquals(est, magica.getStanzaAdiacente("nord"));
	}

	@Test
	void testVaiEstRitornaSud() {
		assertEquals(sud, magica.getStanzaAdiacente("est"));
	}

	@Test
	void testVaiSudRitornaNord() {
		// sud(2) → nord(0)  [(2+1)%3 = 0]
		assertEquals(nord, magica.getStanzaAdiacente("sud"));
	}

	@Test
	void testDirezioneAssenteRitornaNull() {
		assertNull(magica.getStanzaAdiacente("ovest"));
	}

	@Test
	void testStanzaSenzaUsciteRitornaNull() {
		StanzaMagica vuota = new StanzaMagica("vuota");
		assertNull(vuota.getStanzaAdiacente("nord"));
	}

	@Test
	void testConUnaUscitaRitornaSteStanza() {
		// con una sola uscita lo scarto porta alla stessa: (0+1)%1 = 0
		StanzaMagica sola = new StanzaMagica("sola");
		Stanza x = new Stanza("x");
		sola.impostaStanzaAdiacente("nord", x);
		assertEquals(x, sola.getStanzaAdiacente("nord"));
	}
}
