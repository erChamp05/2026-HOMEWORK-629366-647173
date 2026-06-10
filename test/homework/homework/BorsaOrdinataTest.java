package homework;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class BorsaOrdinataTest {

	private Borsa borsa;
	private Attrezzo spada;   // peso 2
	private Attrezzo osso;    // peso 1
	private Attrezzo lanterna; // peso 3
	private Attrezzo chiave;  // peso 1

	@BeforeEach
	void setUp() {
		borsa = new Borsa(100);
		spada    = new Attrezzo("spada",    2);
		osso     = new Attrezzo("osso",     1);
		lanterna = new Attrezzo("lanterna", 3);
		chiave   = new Attrezzo("chiave",   1);
		borsa.addAttrezzo(spada);
		borsa.addAttrezzo(osso);
		borsa.addAttrezzo(lanterna);
		borsa.addAttrezzo(chiave);
	}

	// ---------- Es. 3: getContenutoOrdinatoPerPeso ----------

	@Test
	void testOrdinamentoPerPesoPrimoElemento() {
		List<Attrezzo> lista = borsa.getContenutoOrdinatoPerPeso();
		assertEquals(1, lista.get(0).getPeso());
	}

	@Test
	void testOrdinamentoPerPesoUltimoElemento() {
		List<Attrezzo> lista = borsa.getContenutoOrdinatoPerPeso();
		assertEquals(3, lista.get(lista.size() - 1).getPeso());
	}

	@Test
	void testOrdinamentoPerPesoDimensione() {
		List<Attrezzo> lista = borsa.getContenutoOrdinatoPerPeso();
		assertEquals(4, lista.size());
	}

	// ---------- Es. 3: getContenutoOrdinatoPerNome ----------

	@Test
	void testOrdinamentoPerNomePrimoElemento() {
		SortedSet<Attrezzo> set = borsa.getContenutoOrdinatoPerNome();
		assertEquals("chiave", set.first().getNome());
	}

	@Test
	void testOrdinamentoPerNomeUltimoElemento() {
		SortedSet<Attrezzo> set = borsa.getContenutoOrdinatoPerNome();
		assertEquals("spada", set.last().getNome());
	}

	@Test
	void testOrdinamentoPerNomeDimensione() {
		SortedSet<Attrezzo> set = borsa.getContenutoOrdinatoPerNome();
		assertEquals(4, set.size());
	}

	// ---------- Es. 3: getContenutoRaggruppatoPerPeso ----------

	@Test
	void testRaggruppamentoPeso1ContieneDueAttrezzi() {
		Map<Integer, Set<Attrezzo>> mappa = borsa.getContenutoRaggruppatoPerPeso();
		assertEquals(2, mappa.get(1).size());
	}

	@Test
	void testRaggruppamentoPeso2ContieneSolo() {
		Map<Integer, Set<Attrezzo>> mappa = borsa.getContenutoRaggruppatoPerPeso();
		assertEquals(1, mappa.get(2).size());
	}

	@Test
	void testRaggruppamentoNumeroChiavi() {
		Map<Integer, Set<Attrezzo>> mappa = borsa.getContenutoRaggruppatoPerPeso();
		assertEquals(3, mappa.size()); // pesi 1, 2, 3
	}

	// ---------- Es. 4: getSortedSetOrdinatoPerPeso ----------

	@Test
	void testSortedSetPerPesoPrimoElemento() {
		SortedSet<Attrezzo> set = borsa.getSortedSetOrdinatoPerPeso();
		assertEquals(1, set.first().getPeso());
	}

	@Test
	void testSortedSetPerPesoUltimoElemento() {
		SortedSet<Attrezzo> set = borsa.getSortedSetOrdinatoPerPeso();
		assertEquals(3, set.last().getPeso());
	}

	@Test
	void testSortedSetPerPesoContieneEntrambiPeso1() {
		SortedSet<Attrezzo> set = borsa.getSortedSetOrdinatoPerPeso();
		assertEquals(4, set.size());
	}

	@Test
	void testSortedSetPerPesoDuePesiUgualiDistinti() {
		// osso e chiave hanno entrambi peso 1 ma nomi diversi: devono essere 2 elementi
		SortedSet<Attrezzo> set = borsa.getSortedSetOrdinatoPerPeso();
		long count = set.stream().filter(a -> a.getPeso() == 1).count();
		assertEquals(2, count);
	}
}
