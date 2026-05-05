package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {

	private String parametro;
	private IO io;

	@Override
	public void esegui(Partita partita) {
		if (parametro == null) {
			io.mostraMessaggio("Dove vuoi andare ?");
			return;
		}
		Stanza prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(parametro);
		if (prossimaStanza == null) {
			io.mostraMessaggio("Direzione inesistente");
		} else {
			partita.setStanzaCorrente(prossimaStanza);
			partita.setCfu(partita.getCfu() - 1);
		}
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	@Override
	public String getNome() {
		return "vai";
	}

	@Override
	public String getParametro() {
		return this.parametro;
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}
}
