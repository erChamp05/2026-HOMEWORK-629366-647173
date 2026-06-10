package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Il Mago aggiunge CFU al giocatore quando interagisce con lui.
 * Se riceve un regalo, lo restituisce alleggerito (peso dimezzato).
 */
public class Mago extends AbstractPersonaggio {

	public Mago(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		partita.setCfu(partita.getCfu() + 1);
		return "Il mago ti ha dato 1 CFU! CFU rimasti: " + partita.getCfu();
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		int nuovoPeso = Math.max(1, attrezzo.getPeso() / 2);
		Attrezzo alleggerito = new Attrezzo(attrezzo.getNome(), nuovoPeso);
		partita.getGiocatore().getBorsa().addAttrezzo(alleggerito);
		return "Il mago ha alleggerito " + attrezzo.getNome() + " a " + nuovoPeso + "kg e lo ha messo nella tua borsa";
	}
}
