package homework;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.giocatore.Giocatore;

class GiocatoreTest {

	Giocatore giocatore;
	
	@BeforeEach	
	void setUp() {
		giocatore = new Giocatore();
	}
	
	@Test
	void testSetCfu() {
		giocatore.setCfu(8);
		assertEquals(8,giocatore.getCfu());
	}
	
	@Test
	void testCfuIniziali() {
		assertEquals(20,giocatore.getCfu());
	}
	
	@Test
	void testEsisteBorsa() {
		assertNotNull(giocatore.getBorsa());
	}
	
	@Test
	void testBorsaVuota() {
		assertEquals(0,giocatore.getBorsa().getNumeroAttrezzi());
	}

}
