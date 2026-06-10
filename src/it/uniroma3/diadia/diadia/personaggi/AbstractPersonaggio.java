package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe astratta per modellare i personaggi del gioco.
 * Ogni personaggio ha un nome e puo' essere salutato, ricevere un regalo
 * o essere oggetto di un'interazione.
 *
 * @author docente di POO
 * @version base
 */
public abstract class AbstractPersonaggio {

	private String nome;
	private String presentazione;

	protected AbstractPersonaggio(String nome, String presentazione) {
		this.nome = nome;
		this.presentazione = presentazione;
	}

	public String getNome() {
		return this.nome;
	}

	public String getPresentazione() {
		return this.presentazione;
	}

	public String saluta() {
		return "Ciao, sono " + this.nome + ". " + this.presentazione;
	}

	public abstract String agisci(Partita partita);

	public abstract String riceviRegalo(Attrezzo attrezzo, Partita partita);
}
