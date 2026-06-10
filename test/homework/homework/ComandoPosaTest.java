package homework;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;

class ComandoPosaTest {

	private Partita partita;
	private ComandoPosa comandoPosa;
	private IO ioStub;

	@BeforeEach
	void setUp() {
		partita = new Partita();
		Attrezzo spada = new Attrezzo("spada", 2);
		partita.getGiocatore().getBorsa().addAttrezzo(spada);
		comandoPosa = new ComandoPosa();
		ioStub = new IO() {
			public void mostraMessaggio(String m) {}
			public String leggiRiga() { return ""; }
		};
		comandoPosa.setIO(ioStub);
	}

	@Test
	void testPosaRimuoveDaBorsa() {
		comandoPosa.setParametro("spada");
		comandoPosa.esegui(partita);
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
	}

	@Test
	void testPosaAggiungeAStanza() {
		comandoPosa.setParametro("spada");
		comandoPosa.esegui(partita);
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("spada"));
	}

	@Test
	void testPosaAttrezzoAssenteNonModificaBorsa() {
		comandoPosa.setParametro("fantasma");
		comandoPosa.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
	}

	@Test
	void testPosaParametroNullNonCrasha() {
		comandoPosa.setParametro(null);
		comandoPosa.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
	}
}
