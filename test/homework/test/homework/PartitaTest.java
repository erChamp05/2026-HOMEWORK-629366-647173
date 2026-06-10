package homework;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


class PartitaTest {

	Partita partita;
	
	@BeforeEach	
	void setUp() {
		partita = new Partita();
	}
		
	@Test
	void testCfuIniziali() {
		assertEquals(20,partita.getCfu());
		
	}
	
	@Test
	void testGetStanzaCorrente() {
		assertEquals("Atrio",partita.getStanzaCorrente().getNome());
		
	}

	@Test
	void testSetStanzaCorrente() {
		Stanza palestra = new Stanza("palestra");
		partita.setStanzaCorrente(palestra);
		assertEquals(palestra,partita.getStanzaCorrente());
		
	}
	
	@Test
	void testStanzaVincente(){
		assertEquals("Biblioteca" , partita.getStanzaVincente().getNome());	
	}

	@Test
	void testPartitaNonVinta(){
		assertFalse(partita.vinta());
	}

	@Test
	void testPartitaFinitaNo(){
		assertFalse(partita.isFinita());	
	}
	
	@Test
	void testSetPartitaFinita(){
		partita.setFinita();
		assertTrue(partita.isFinita());	
	}
	
	@Test
	void testCfuPartitaFinitaSi(){
		partita.setCfu(0);
		assertTrue(partita.isFinita());	
	}
	
	@Test
	void testPartitaFinitaVittoria(){
		partita.setStanzaCorrente(partita.getStanzaVincente());
		assertTrue(partita.isFinita());	
	}
	
	@Test
	void testSetCfu(){
		partita.setCfu(7);
		assertEquals(7,partita.getCfu());	
	}
}
