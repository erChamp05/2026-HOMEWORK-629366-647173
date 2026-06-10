package homework;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;



class Labirintotest {
	
	Labirinto labirinto;
	
	@BeforeEach	
	void setUp() {
		labirinto = Labirinto.creaDefault();
	}
	
	@Test
	void testStanzaIniziale() {
		assertEquals("Atrio",labirinto.getStanzaIniziale().getNome());
	}
	
	@Test
	void testStanzaVincente() {
		assertEquals("Biblioteca",labirinto.getStanzaVincente().getNome());
	}

}
