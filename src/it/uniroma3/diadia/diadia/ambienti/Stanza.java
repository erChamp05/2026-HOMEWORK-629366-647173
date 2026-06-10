package it.uniroma3.diadia.ambienti;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione (tipo Direzione).
 *
 * Implementazione basata su Map: nessun ciclo di ricerca lineare.
 * Le uscite usano l'enum Direzione come chiave.
 * I metodi String-based sono conservati per retrocompatibilita'
 * e delegano alla versione con Direzione.
 *
 * @author docente di POO
 * @see Attrezzo
 * @version JCF+Direzione
 */
public class Stanza {

	private String nome;
	private Map<String, Attrezzo> attrezzi;
	private Map<Direzione, Stanza> uscite;
	private AbstractPersonaggio personaggio;

	public Stanza(String nome) {
		this.nome = nome;
		this.attrezzi = new HashMap<>();
		this.uscite = new LinkedHashMap<>();
	}

	// ---------- uscite con Direzione (API primaria, Es.18) ----------

	public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
		if (direzione != null)
			this.uscite.put(direzione, stanza);
	}

	public Stanza getStanzaAdiacente(Direzione direzione) {
		if (direzione == null) return null;
		return this.uscite.get(direzione);
	}

	// ---------- uscite con String (retrocompatibilita') ----------

	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		this.impostaStanzaAdiacente(Direzione.get(direzione), stanza);
	}

	public Stanza getStanzaAdiacente(String direzione) {
		return this.getStanzaAdiacente(Direzione.get(direzione));
	}

	// ---------- altri metodi ----------

	public String getNome() {
		return this.nome;
	}

	public String getDescrizione() {
		return this.toString();
	}

	public Collection<Attrezzo> getAttrezzi() {
		return this.attrezzi.values();
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
	}

	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for (Direzione d : this.uscite.keySet())
			risultato.append(" ").append(d);
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi.values())
			risultato.append(attrezzo.toString()).append(" ");
		if (this.personaggio != null)
			risultato.append("\nPersonaggio: ").append(this.personaggio.getNome());
		return risultato.toString();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.remove(attrezzo.getNome()) != null;
	}

	public String[] getDirezioni() {
		return this.uscite.keySet().stream()
			.map(Direzione::toString)
			.toArray(String[]::new);
	}

	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}
}
