package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		for (String nome : FabbricaComandiIntrospettiva.getNomiComandi())
			io.mostraMessaggio(nome + " ");
	}
}
