package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		if (getParametro() == null) {
			io.mostraMessaggio("Dove vuoi andare ?");
			return;
		}
		Stanza prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(getParametro());
		if (prossimaStanza == null) {
			io.mostraMessaggio("Direzione inesistente");
		} else {
			partita.setStanzaCorrente(prossimaStanza);
			partita.setCfu(partita.getCfu() - 1);
		}
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}
}
