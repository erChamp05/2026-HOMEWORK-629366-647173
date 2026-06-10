package homework;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.CaricatoreLabirinto;
import it.uniroma3.diadia.ambienti.Labirinto;

class CaricatoreLabirintoTest {

	private static final String LABIRINTO_SEMPLICE =
		"Stanze:\n"
		+ "atrio\n"
		+ "biblioteca\n"
		+ "\n"
		+ "Adiacenze:\n"
		+ "atrio biblioteca nord\n"
		+ "biblioteca atrio sud\n"
		+ "\n"
		+ "Attrezzi:\n"
		+ "osso 1 atrio\n"
		+ "\n"
		+ "Inizio:\n"
		+ "atrio\n"
		+ "\n"
		+ "Vincente:\n"
		+ "biblioteca\n";

	private static final String LABIRINTO_CON_BUIA =
		"StanzeBuie:\n"
		+ "cantina lanterna\n"
		+ "\n"
		+ "Stanze:\n"
		+ "uscita\n"
		+ "\n"
		+ "Adiacenze:\n"
		+ "cantina uscita nord\n"
		+ "\n"
		+ "Inizio:\n"
		+ "cantina\n"
		+ "\n"
		+ "Vincente:\n"
		+ "uscita\n";

	@Test
	void testStanzaInizialeCaricata() throws IOException {
		Labirinto lab = new CaricatoreLabirinto(new StringReader(LABIRINTO_SEMPLICE)).carica();
		assertEquals("atrio", lab.getStanzaIniziale().getNome());
	}

	@Test
	void testStanzaVincenteCaricata() throws IOException {
		Labirinto lab = new CaricatoreLabirinto(new StringReader(LABIRINTO_SEMPLICE)).carica();
		assertEquals("biblioteca", lab.getStanzaVincente().getNome());
	}

	@Test
	void testAdiacenzaCaricata() throws IOException {
		Labirinto lab = new CaricatoreLabirinto(new StringReader(LABIRINTO_SEMPLICE)).carica();
		assertNotNull(lab.getStanzaIniziale().getStanzaAdiacente("nord"));
	}

	@Test
	void testAttrezzoCaricatoInStanza() throws IOException {
		Labirinto lab = new CaricatoreLabirinto(new StringReader(LABIRINTO_SEMPLICE)).carica();
		assertTrue(lab.getStanzaIniziale().hasAttrezzo("osso"));
	}

	@Test
	void testStanzaBuiaCaricata() throws IOException {
		Labirinto lab = new CaricatoreLabirinto(new StringReader(LABIRINTO_CON_BUIA)).carica();
		assertEquals("cantina", lab.getStanzaIniziale().getNome());
	}

	@Test
	void testStanzaBuiaBuia() throws IOException {
		Labirinto lab = new CaricatoreLabirinto(new StringReader(LABIRINTO_CON_BUIA)).carica();
		assertEquals("qui c'e' buio pesto", lab.getStanzaIniziale().getDescrizione());
	}
}
