package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * La Strega toglie CFU al giocatore quando interagisce con lei.
 * Se riceve un regalo, lo restituisce "trasformato" (nome invertito, peso raddoppiato).
 */
public class Strega extends AbstractPersonaggio {

	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		partita.setCfu(partita.getCfu() - 1);
		return "La strega ti ha tolto 1 CFU! CFU rimasti: " + partita.getCfu();
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String nomeInvertito = new StringBuilder(attrezzo.getNome()).reverse().toString();
		Attrezzo trasformato = new Attrezzo(nomeInvertito, attrezzo.getPeso() * 2);
		partita.getStanzaCorrente().addAttrezzo(trasformato);
		return "La strega ha trasformato " + attrezzo.getNome() + " in " + trasformato.getNome();
	}
}
