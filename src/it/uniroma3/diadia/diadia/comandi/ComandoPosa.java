package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		if (getParametro() == null) {
			io.mostraMessaggio("Quale attrezzo vuoi posare ?");
			return;
		}
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().removeAttrezzo(getParametro());
		if (attrezzo == null) {
			io.mostraMessaggio("Attrezzo non presente nella borsa");
			return;
		}
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		io.mostraMessaggio("Hai posato: " + attrezzo.getNome());
	}
}
