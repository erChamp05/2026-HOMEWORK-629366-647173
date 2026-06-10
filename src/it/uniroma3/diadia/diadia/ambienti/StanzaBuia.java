package it.uniroma3.diadia.ambienti;

/**
 * Stanza buia: senza l'attrezzo necessario, getDescrizione() restituisce
 * "qui c'e' buio pesto" invece della descrizione normale.
 *
 * @author docente di POO
 * @version base
 */
public class StanzaBuia extends Stanza {

	private String nomeAttrezzoNecessario;

	public StanzaBuia(String nome, String nomeAttrezzoNecessario) {
		super(nome);
		this.nomeAttrezzoNecessario = nomeAttrezzoNecessario;
	}

	@Override
	public String getDescrizione() {
		if (this.hasAttrezzo(nomeAttrezzoNecessario))
			return super.getDescrizione();
		return "qui c'e' buio pesto";
	}
}
