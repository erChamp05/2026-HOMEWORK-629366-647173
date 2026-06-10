package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Versione di Stanza con campi protetti per permettere l'accesso diretto
 * alle sottoclassi senza passare dall'API pubblica.
 *
 * @author docente di POO
 * @version base
 */
public class StanzaProtected {

	static final protected int NUMERO_MASSIMO_DIREZIONI = 4;
	static final protected int NUMERO_MASSIMO_ATTREZZI = 10;

	protected String nome;

	protected Attrezzo[] attrezzi;
	protected int numeroAttrezzi;

	protected Stanza[] stanzeAdiacenti;
	protected int numeroStanzeAdiacenti;

	protected String[] direzioni;

	public StanzaProtected(String nome) {
		this.nome = nome;
		this.numeroStanzeAdiacenti = 0;
		this.numeroAttrezzi = 0;
		this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
		this.stanzeAdiacenti = new Stanza[NUMERO_MASSIMO_DIREZIONI];
		this.attrezzi = new Attrezzo[NUMERO_MASSIMO_ATTREZZI];
	}

	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		boolean aggiornato = false;
		for (int i = 0; i < this.direzioni.length; i++)
			if (direzione.equals(this.direzioni[i])) {
				this.stanzeAdiacenti[i] = stanza;
				aggiornato = true;
			}
		if (!aggiornato)
			if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
				this.direzioni[numeroStanzeAdiacenti] = direzione;
				this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
				this.numeroStanzeAdiacenti++;
			}
	}

	public Stanza getStanzaAdiacente(String direzione) {
		Stanza stanza = null;
		for (int i = 0; i < this.numeroStanzeAdiacenti; i++)
			if (this.direzioni[i].equals(direzione))
				stanza = this.stanzeAdiacenti[i];
		return stanza;
	}

	public String getNome() {
		return this.nome;
	}

	public String getDescrizione() {
		return this.toString();
	}

	public Attrezzo[] getAttrezzi() {
		return this.attrezzi;
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
			this.attrezzi[numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			return true;
		}
		return false;
	}

	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for (String direzione : this.direzioni)
			if (direzione != null)
				risultato.append(" " + direzione);
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi)
			if (attrezzo != null)
				risultato.append(attrezzo.toString() + " ");
		return risultato.toString();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		for (Attrezzo attrezzo : this.attrezzi)
			if (attrezzo != null && attrezzo.getNome().equals(nomeAttrezzo))
				return true;
		return false;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for (Attrezzo attrezzo : this.attrezzi)
			if (attrezzo != null && attrezzo.getNome().equals(nomeAttrezzo))
				return attrezzo;
		return null;
	}

	public boolean removeAttrezzo(Attrezzo attrezzo) {
		for (int i = 0; i < this.numeroAttrezzi; i++) {
			if (this.attrezzi[i] != null && this.attrezzi[i].equals(attrezzo)) {
				for (int j = i; j < this.numeroAttrezzi - 1; j++)
					this.attrezzi[j] = this.attrezzi[j + 1];
				this.attrezzi[this.numeroAttrezzi - 1] = null;
				this.numeroAttrezzi--;
				return true;
			}
		}
		return false;
	}

	public String[] getDirezioni() {
		String[] direzioni = new String[this.numeroStanzeAdiacenti];
		for (int i = 0; i < this.numeroStanzeAdiacenti; i++)
			direzioni[i] = this.direzioni[i];
		return direzioni;
	}
}
