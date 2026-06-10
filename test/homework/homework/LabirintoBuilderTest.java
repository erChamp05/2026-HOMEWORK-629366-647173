package homework;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

class LabirintoBuilderTest {

	private Labirinto labirinto;

	@BeforeEach
	void setUp() {
		labirinto = Labirinto.newBuilder()
			.addStanza("atrio")
			.addAttrezzo("osso", 1)
			.addStanza("biblioteca")
			.addAdiacenza("atrio", "biblioteca", "nord")
			.addAdiacenza("biblioteca", "atrio", "sud")
			.setStanzaIniziale("atrio")
			.setStanzaVincente("biblioteca")
			.getLabirinto();
	}

	@Test
	void testStanzaInizialeNome() {
		assertEquals("atrio", labirinto.getStanzaIniziale().getNome());
	}

	@Test
	void testStanzaVincenteNome() {
		assertEquals("biblioteca", labirinto.getStanzaVincente().getNome());
	}

	@Test
	void testAdiacenzaNord() {
		Stanza atrio = labirinto.getStanzaIniziale();
		assertEquals("biblioteca", atrio.getStanzaAdiacente("nord").getNome());
	}

	@Test
	void testAdiacenzaSud() {
		Stanza biblioteca = labirinto.getStanzaVincente();
		assertEquals("atrio", biblioteca.getStanzaAdiacente("sud").getNome());
	}

	@Test
	void testAttrezzoInStanzaIniziale() {
		Stanza atrio = labirinto.getStanzaIniziale();
		assertTrue(atrio.hasAttrezzo("osso"));
	}

	@Test
	void testAttrezzoNonInStanzaVincente() {
		Stanza biblioteca = labirinto.getStanzaVincente();
		assertFalse(biblioteca.hasAttrezzo("osso"));
	}

	@Test
	void testLabirintoDefaultHaAtrio() {
		Labirinto def = Labirinto.creaDefault();
		assertEquals("Atrio", def.getStanzaIniziale().getNome());
	}

	@Test
	void testLabirintoDefaultStanzaVincente() {
		Labirinto def = Labirinto.creaDefault();
		assertEquals("Biblioteca", def.getStanzaVincente().getNome());
	}
}
