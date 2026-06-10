package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Rappresenta il labirinto del gioco.
 * Il costruttore e' privato: usare {@link #newBuilder()} o {@link #creaDefault()}.
 *
 * @author docente di POO
 * @version builder-nested
 */
public class Labirinto {

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	private Labirinto() {}

	/** Factory method: restituisce un builder per costruire un labirinto personalizzato. */
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}

	/** Crea il labirinto di default del gioco DiaDia. */
	public static Labirinto creaDefault() {
		return newBuilder()
			.addStanza("Atrio")
			.addAttrezzo("osso", 1)
			.addStanza("Aula N11")
			.addStanza("Aula N10")
			.addAttrezzo("lanterna", 3)
			.addStanza("Laboratorio Campus")
			.addStanza("Biblioteca")
			.addAdiacenza("Atrio", "Biblioteca", "nord")
			.addAdiacenza("Atrio", "Aula N11", "est")
			.addAdiacenza("Atrio", "Aula N10", "sud")
			.addAdiacenza("Atrio", "Laboratorio Campus", "ovest")
			.addAdiacenza("Aula N11", "Laboratorio Campus", "est")
			.addAdiacenza("Aula N11", "Atrio", "ovest")
			.addAdiacenza("Aula N10", "Atrio", "nord")
			.addAdiacenza("Aula N10", "Aula N11", "est")
			.addAdiacenza("Aula N10", "Laboratorio Campus", "ovest")
			.addAdiacenza("Laboratorio Campus", "Atrio", "est")
			.addAdiacenza("Laboratorio Campus", "Aula N11", "ovest")
			.addAdiacenza("Biblioteca", "Atrio", "sud")
			.setStanzaIniziale("Atrio")
			.setStanzaVincente("Biblioteca")
			.getLabirinto();
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}

	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}

	// ---------------------------------------------------------------
	// Classe statica nidificata - Esercizio 19
	// ---------------------------------------------------------------

	/**
	 * Builder per Labirinto con supporto al method chaining.
	 * Accessibile come Labirinto.newBuilder() oppure new Labirinto.LabirintoBuilder().
	 */
	public static class LabirintoBuilder {

		private Labirinto labirinto;
		private Map<String, Stanza> stanze;
		private Stanza ultimaStanzaAggiunta;

		public LabirintoBuilder() {
			this.labirinto = new Labirinto();
			this.stanze = new HashMap<>();
		}

		public LabirintoBuilder addStanza(String nome) {
			Stanza s = new Stanza(nome);
			this.stanze.put(nome, s);
			this.ultimaStanzaAggiunta = s;
			return this;
		}

		public LabirintoBuilder addStanzaBuia(String nome, String attrezzoNecessario) {
			Stanza s = new StanzaBuia(nome, attrezzoNecessario);
			this.stanze.put(nome, s);
			this.ultimaStanzaAggiunta = s;
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
			Stanza s = new StanzaBloccata(nome, direzioneBloccata, attrezzoSbloccante);
			this.stanze.put(nome, s);
			this.ultimaStanzaAggiunta = s;
			return this;
		}

		public LabirintoBuilder addStanzaMagica(String nome) {
			Stanza s = new StanzaMagica(nome);
			this.stanze.put(nome, s);
			this.ultimaStanzaAggiunta = s;
			return this;
		}

		public LabirintoBuilder addAttrezzo(String nome, int peso) {
			if (this.ultimaStanzaAggiunta != null)
				this.ultimaStanzaAggiunta.addAttrezzo(new Attrezzo(nome, peso));
			return this;
		}

		public LabirintoBuilder addAttrezzoInStanza(String nomeAttrezzo, int peso, String nomeStanza) {
			Stanza s = this.stanze.get(nomeStanza);
			if (s != null)
				s.addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
			return this;
		}

		public LabirintoBuilder addAdiacenza(String nomeDa, String nomeA, String direzione) {
			Stanza da = this.stanze.get(nomeDa);
			Stanza a  = this.stanze.get(nomeA);
			if (da != null && a != null)
				da.impostaStanzaAdiacente(direzione, a);
			return this;
		}

		public LabirintoBuilder addPersonaggio(AbstractPersonaggio personaggio, String nomeStanza) {
			Stanza s = this.stanze.get(nomeStanza);
			if (s != null)
				s.setPersonaggio(personaggio);
			return this;
		}

		public LabirintoBuilder setStanzaIniziale(String nome) {
			Stanza s = this.stanze.get(nome);
			if (s != null)
				this.labirinto.setStanzaIniziale(s);
			return this;
		}

		public LabirintoBuilder setStanzaVincente(String nome) {
			Stanza s = this.stanze.get(nome);
			if (s != null)
				this.labirinto.setStanzaVincente(s);
			return this;
		}

		public Labirinto getLabirinto() {
			return this.labirinto;
		}
	}
}
