package homework;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.comandi.ComandoPrendi;

class ComandoPrendiTest {

	private Partita partita;
	private ComandoPrendi comandoPrendi;
	private IO ioStub;

	@BeforeEach
	void setUp() {
		partita = new Partita();
		// Il labirinto mette un "osso" nell'Atrio (stanza iniziale)
		comandoPrendi = new ComandoPrendi();
		ioStub = new IO() {
			public void mostraMessaggio(String m) {}
			public String leggiRiga() { return ""; }
		};
		comandoPrendi.setIO(ioStub);
	}

	@Test
	void testPrendiRimuoveDaStanza() {
		comandoPrendi.setParametro("osso");
		comandoPrendi.esegui(partita);
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("osso"));
	}

	@Test
	void testPrendiAggiungeABorsa() {
		comandoPrendi.setParametro("osso");
		comandoPrendi.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
	}

	@Test
	void testPrendiAttrezzoAssenteNonModificaBorsa() {
		comandoPrendi.setParametro("fantasma");
		comandoPrendi.esegui(partita);
		assertEquals(0, partita.getGiocatore().getBorsa().getNumeroAttrezzi());
	}

	@Test
	void testPrendiParametroNullNonCrasha() {
		comandoPrendi.setParametro(null);
		comandoPrendi.esegui(partita);
		assertEquals(0, partita.getGiocatore().getBorsa().getNumeroAttrezzi());
	}
}
