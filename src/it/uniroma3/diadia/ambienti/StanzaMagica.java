package it.uniroma3.diadia.ambienti;

/**
 * Stanza magica: quando si chiede la stanza adiacente in una direzione,
 * restituisce la stanza corrispondente alla direzione successiva (scarto di 1).
 * Estende Stanza usando solo l'API pubblica (campi privati).
 *
 * @author docente di POO
 * @version base
 */
public class StanzaMagica extends Stanza {

	static final private int SCARTO_MAGICO = 1;

	public StanzaMagica(String nome) {
		super(nome);
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		String[] direzioni = this.getDirezioni();
		int numDirezioni = direzioni.length;
		if (numDirezioni == 0)
			return null;
		for (int i = 0; i < numDirezioni; i++) {
			if (direzioni[i].equals(direzione)) {
				String direzioneReale = direzioni[(i + SCARTO_MAGICO) % numDirezioni];
				return super.getStanzaAdiacente(direzioneReale);
			}
		}
		return null;
	}
}
