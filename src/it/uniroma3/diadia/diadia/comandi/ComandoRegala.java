package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		if (getParametro() == null) {
			io.mostraMessaggio("Quale attrezzo vuoi regalare ?");
			return;
		}
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio == null) {
			io.mostraMessaggio("Non c'e' nessuno a cui regalare qualcosa qui.");
			return;
		}
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().removeAttrezzo(getParametro());
		if (attrezzo == null) {
			io.mostraMessaggio("Non hai " + getParametro() + " nella borsa.");
			return;
		}
		io.mostraMessaggio(personaggio.riceviRegalo(attrezzo, partita));
	}
}
