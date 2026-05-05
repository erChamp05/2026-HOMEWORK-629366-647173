package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {

	private String parametro;
	private IO io;

	@Override
	public void esegui(Partita partita) {
		if (parametro == null) {
			io.mostraMessaggio("Quale attrezzo vuoi prendere ?");
			return;
		}
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Attrezzo attrezzo = stanzaCorrente.getAttrezzo(parametro);
		if (attrezzo == null) {
			io.mostraMessaggio("Attrezzo non presente nella stanza");
			return;
		}
		boolean aggiunto = partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		if (aggiunto) {
			stanzaCorrente.removeAttrezzo(attrezzo);
			io.mostraMessaggio("Hai preso: " + attrezzo.getNome());
		} else {
			io.mostraMessaggio("Non riesci a prendere " + attrezzo.getNome() + " (borsa piena o troppo pesante)");
		}
	}

	@Override
	public String getNome() {
		return "prendi";
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
