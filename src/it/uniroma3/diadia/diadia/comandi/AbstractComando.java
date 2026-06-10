package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;

/**
 * Classe astratta che implementa i metodi comuni dell'interfaccia Comando,
 * riducendo la duplicazione nelle sottoclassi concrete.
 * Il nome del comando e' derivato dal nome della classe tramite riflessione.
 *
 * @author docente di POO
 * @version astratta
 */
public abstract class AbstractComando implements Comando {

	private String parametro;
	protected IO io;

	@Override
	public String getNome() {
		String nomeClasse = getClass().getSimpleName(); // es. "ComandoVai"
		return nomeClasse.replace("Comando", "").toLowerCase(); // → "vai"
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
