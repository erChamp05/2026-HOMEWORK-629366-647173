package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<>();
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
	}

	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	public int getPeso() {
		int peso = 0;
		for (Attrezzo a : this.attrezzi.values())
			peso += a.getPeso();
		return peso;
	}

	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}

	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List<Attrezzo> lista = new ArrayList<>(this.attrezzi.values());
		Collections.sort(lista, Comparator.comparingInt(Attrezzo::getPeso));
		return lista;
	}

	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> set = new TreeSet<>(Comparator.comparing(Attrezzo::getNome));
		set.addAll(this.attrezzi.values());
		return set;
	}

	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> mappa = new TreeMap<>();
		for (Attrezzo a : this.attrezzi.values()) {
			mappa.computeIfAbsent(a.getPeso(), k -> new TreeSet<>(Comparator.comparing(Attrezzo::getNome))).add(a);
		}
		return mappa;
	}

	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> set = new TreeSet<>(
			Comparator.comparingInt(Attrezzo::getPeso).thenComparing(Attrezzo::getNome)
		);
		set.addAll(this.attrezzi.values());
		return set;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa (").append(this.getPeso()).append("kg/").append(this.getPesoMax()).append("kg): ");
			for (Attrezzo a : this.attrezzi.values())
				s.append(a.toString()).append(" ");
		} else {
			s.append("Borsa vuota");
		}
		return s.toString();
	}
}
