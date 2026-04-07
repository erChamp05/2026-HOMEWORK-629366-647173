package homework;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.jupiter.api.BeforeEach;

class StanzaTest {
	
	Stanza stanza;
	
	@BeforeEach
	void setUp() {
		stanza = new Stanza("aula");
	}
	
	@Test
	void testNome() {
		assertEquals("aula" , stanza.getNome());
	}
	
	@Test
	void testGetStanzaAdiacente() {
		Stanza N11 = new Stanza("N11");
		stanza.impostaStanzaAdiacente("nord",N11);
		assertEquals(N11, stanza.getStanzaAdiacente("nord"));
		
	}
	

	@Test
	void testDirezioneAssente(){
		assertNull(stanza.getStanzaAdiacente("sud"));	
	}
		
	@Test
	void testStessaDirezione() {
		Stanza biblioteca = new Stanza("biblioteca");
		Stanza N10 = new Stanza("N10");
		stanza.impostaStanzaAdiacente("nord",biblioteca);
		stanza.impostaStanzaAdiacente("nord", N10);
		assertEquals(N10,stanza.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testAddAttrezzo() {
		Attrezzo spada = new Attrezzo("spada",2);
		assertTrue(stanza.addAttrezzo(spada));		
	}
	
	@Test
	void testAddAttrezziFinoAlMassimo() {
		for(int i =0;i<10;i++)
		{
			assertTrue(stanza.addAttrezzo(new Attrezzo("osso",1+i)));
		}
	}	
	
	@Test
	void testAddTroppiAttrezzi() {
		for(int i =0;i<10;i++)
		{
			stanza.addAttrezzo(new Attrezzo("osso",1+i));
		}
		
		assertFalse(stanza.addAttrezzo(new Attrezzo("spada",3)));
	}	

	@Test
	void testMaxDirezioni() {
		stanza.impostaStanzaAdiacente("nord", new Stanza("aula1"));
		stanza.impostaStanzaAdiacente("sud", new Stanza("aula2"));
		stanza.impostaStanzaAdiacente("est", new Stanza("aula3"));
		stanza.impostaStanzaAdiacente("ovest", new Stanza("aula4"));
		
		assertNotNull(stanza.getStanzaAdiacente("nord"));
		assertNotNull(stanza.getStanzaAdiacente("sud"));
		assertNotNull(stanza.getStanzaAdiacente("est"));
		assertNotNull(stanza.getStanzaAdiacente("ovest"));	
	}	
	
	void testTroppeDirezioni() {
		stanza.impostaStanzaAdiacente("nord", new Stanza("aula1"));
		stanza.impostaStanzaAdiacente("sud", new Stanza("aula2"));
		stanza.impostaStanzaAdiacente("est", new Stanza("aula3"));
		stanza.impostaStanzaAdiacente("ovest", new Stanza("aula4"));
		
		stanza.impostaStanzaAdiacente("sotto", new Stanza("aula5"));
		
		assertNull(stanza.getStanzaAdiacente("sotto"));		
	}		
	
	@Test
	void testHasAttrezzo() {
		Attrezzo libro = new Attrezzo("libro",10);
		stanza.addAttrezzo(libro);
		assertTrue(stanza.hasAttrezzo("libro"));
		
		
	}	
	
	@Test
	void testHasAttrezzoNULL() {
		assertFalse(stanza.hasAttrezzo("penna"));
	}
	
	@Test
	void testGetAttrezzo() {
		Attrezzo libro = new Attrezzo("libro",10);
		stanza.addAttrezzo(libro);
		assertEquals(libro,stanza.getAttrezzo("libro"));
		
	}	
	
	@Test
	void testDescrizioneContieneNome() {
		assertTrue(stanza.getDescrizione().contains("aula"));
	}	

}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	