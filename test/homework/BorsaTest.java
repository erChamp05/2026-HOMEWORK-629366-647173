package homework;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class BorsaTest {

	Borsa borsa;
	
	@BeforeEach	
	void setUp() {
		borsa = new Borsa();
	}
	
	@Test
	void testBorsaVuota() {
		assertEquals(0,borsa.getNumeroAttrezzi());
	}
	
	@Test
	void testAddAttrezzo() {
		Attrezzo osso = new Attrezzo("osso",1);
		assertTrue(borsa.addAttrezzo(osso));
	}
	

	@Test
	void testNumeroAttrezzi() {
		Attrezzo osso = new Attrezzo("osso",1);
		assertTrue(borsa.addAttrezzo(osso));
		assertEquals(1,borsa.getNumeroAttrezzi());
	}
	
	@Test
	void testGetAttrezzo() {
		assertNull(borsa.getAttrezzo("osso"));
		Attrezzo osso = new Attrezzo("osso",1);
		assertTrue(borsa.addAttrezzo(osso));
		assertEquals(osso,borsa.getAttrezzo("osso"));
	}
	
	@Test
	void testGetPeso() {
		assertEquals(0,borsa.getPeso());
		Attrezzo osso = new Attrezzo("osso",7);
		assertTrue(borsa.addAttrezzo(osso));
		assertEquals(7,borsa.getPeso());
	}
	
	@Test
	void testIsEmpty() {
		assertTrue(borsa.isEmpty());
		Attrezzo osso = new Attrezzo("osso",7);
		assertTrue(borsa.addAttrezzo(osso));
		assertFalse(borsa.isEmpty());
	}
	
	@Test
	void testAttrezzoTroppoPesante() {	
		Attrezzo osso = new Attrezzo("osso",20);
		assertFalse(borsa.addAttrezzo(osso));
	}
	
	@Test
	void testTroppiAttrezzi() {	
		for(int i = 0; i<10;i++){
			Attrezzo spada = new Attrezzo("spada",1);
			borsa.addAttrezzo(spada);
		}
		
		Attrezzo osso = new Attrezzo("osso",1);
		assertFalse(borsa.addAttrezzo(osso));
	
	}
	
	
	
}
