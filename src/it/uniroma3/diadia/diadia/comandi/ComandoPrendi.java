package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		if (getParametro() == null) {
			io.mostraMessaggio("Quale attrezzo vuoi prendere ?");
			return;
		}
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Attrezzo attrezzo = stanzaCorrente.getAttrezzo(getParametro());
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
}
