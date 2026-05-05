package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {

	private String parametro;
	private IO io;

	@Override
	public void esegui(Partita partita) {
		if (parametro == null) {
			io.mostraMessaggio("Quale attrezzo vuoi posare ?");
			return;
		}
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().removeAttrezzo(parametro);
		if (attrezzo == null) {
			io.mostraMessaggio("Attrezzo non presente nella borsa");
			return;
		}
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		io.mostraMessaggio("Hai posato: " + attrezzo.getNome());
	}

	@Override
	public String getNome() {
		return "posa";
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
