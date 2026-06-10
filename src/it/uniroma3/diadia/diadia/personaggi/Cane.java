package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Il Cane non fa nulla di particolare quando interagisce.
 * Se riceve un regalo (un osso), abbaia di gioia.
 */
public class Cane extends AbstractPersonaggio {

	private static final String CIBO_PREFERITO = "osso";

	public Cane(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		return getNome() + " ti annusa e scodinzola.";
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if (CIBO_PREFERITO.equals(attrezzo.getNome()))
			return getNome() + " abbaia di gioia per l'osso!";
		return getNome() + " annusa " + attrezzo.getNome() + " e lo ignora.";
	}
}
