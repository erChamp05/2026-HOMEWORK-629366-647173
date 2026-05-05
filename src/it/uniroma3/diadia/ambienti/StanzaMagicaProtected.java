package it.uniroma3.diadia.ambienti;

/**
 * Stanza magica che estende StanzaProtected, potendo quindi accedere
 * direttamente ai campi protetti senza passare dall'API pubblica.
 *
 * @author docente di POO
 * @version base
 */
public class StanzaMagicaProtected extends StanzaProtected {

	static final private int SCARTO_MAGICO = 1;

	public StanzaMagicaProtected(String nome) {
		super(nome);
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if (this.numeroStanzeAdiacenti == 0)
			return null;
		for (int i = 0; i < this.numeroStanzeAdiacenti; i++) {
			if (this.direzioni[i].equals(direzione)) {
				int indiceReale = (i + SCARTO_MAGICO) % this.numeroStanzeAdiacenti;
				return this.stanzeAdiacenti[indiceReale];
			}
		}
		return null;
	}
}
