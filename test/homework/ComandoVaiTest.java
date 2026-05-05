package homework;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.ComandoVai;

class ComandoVaiTest {

	private Partita partita;
	private ComandoVai comandoVai;
	private IO ioStub;

	@BeforeEach
	void setUp() {
		partita = new Partita();
		comandoVai = new ComandoVai();
		ioStub = new IO() {
			public void mostraMessaggio(String m) {}
			public String leggiRiga() { return ""; }
		};
		comandoVai.setIO(ioStub);
	}

	@Test
	void testVaiSpostaStanzaCorrente() {
		String dir = partita.getStanzaCorrente().getDirezioni()[0];
		Stanza attesa = partita.getStanzaCorrente().getStanzaAdiacente(dir);
		comandoVai.setParametro(dir);
		comandoVai.esegui(partita);
		assertEquals(attesa, partita.getStanzaCorrente());
	}

	@Test
	void testVaiDecremtaCfu() {
		int cfuPrima = partita.getCfu();
		String dir = partita.getStanzaCorrente().getDirezioni()[0];
		comandoVai.setParametro(dir);
		comandoVai.esegui(partita);
		assertEquals(cfuPrima - 1, partita.getCfu());
	}

	@Test
	void testVaiDirezioneInesistenteNonSposta() {
		Stanza stanzaPrima = partita.getStanzaCorrente();
		comandoVai.setParametro("pippo");
		comandoVai.esegui(partita);
		assertEquals(stanzaPrima, partita.getStanzaCorrente());
	}

	@Test
	void testVaiParametroNullNonSposta() {
		Stanza stanzaPrima = partita.getStanzaCorrente();
		comandoVai.setParametro(null);
		comandoVai.esegui(partita);
		assertEquals(stanzaPrima, partita.getStanzaCorrente());
	}

	@Test
	void testVaiParametroNullNonDecremtaCfu() {
		int cfuPrima = partita.getCfu();
		comandoVai.setParametro(null);
		comandoVai.esegui(partita);
		assertEquals(cfuPrima, partita.getCfu());
	}

	@Test
	void testVaiRaggiungeVittoria() {
		// Dall'Atrio, "nord" porta alla Biblioteca (stanza vincente)
		comandoVai.setParametro("nord");
		comandoVai.esegui(partita);
		assertTrue(partita.vinta());
	}
}
