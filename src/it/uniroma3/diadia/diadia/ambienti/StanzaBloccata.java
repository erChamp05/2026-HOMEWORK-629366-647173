package it.uniroma3.diadia.ambienti;

/**
 * Stanza bloccata: una direzione e' inaccessibile a meno che nella stanza
 * non sia presente l'attrezzo sbloccante. In quel caso getStanzaAdiacente()
 * restituisce this (ci si ritrova nella stanza corrente).
 *
 * @author docente di POO
 * @version base
 */
public class StanzaBloccata extends Stanza {

	private String direzioneBloccata;
	private String nomeAttrezzoSbloccante;

	public StanzaBloccata(String nome, String direzioneBloccata, String nomeAttrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.nomeAttrezzoSbloccante = nomeAttrezzoSbloccante;
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if (direzione.equals(direzioneBloccata) && !this.hasAttrezzo(nomeAttrezzoSbloccante))
			return this;
		return super.getStanzaAdiacente(direzione);
	}

	@Override
	public String getDescrizione() {
		return super.getDescrizione()
				+ "\nAttenzione: la direzione " + direzioneBloccata
				+ " e' bloccata. Serve: " + nomeAttrezzoSbloccante;
	}
}
