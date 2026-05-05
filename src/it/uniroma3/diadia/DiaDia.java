package it.uniroma3.diadia;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * @author  docente di POO
 *         (da un'idea di Michael Kolling and David J. Barnes)
 * @version base
 */
public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	private Partita partita;
	private IO io;
	private FabbricaDiComandi fabbrica;

	public DiaDia(IO io) {
		this.io = io;
		this.partita = new Partita();
		this.fabbrica = new FabbricaDiComandiFisarmonica(io);
	}

	public void gioca() {
		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		while (!partita.isFinita()) {
			String istruzione = io.leggiRiga();
			processaIstruzione(istruzione);
		}
		if (partita.vinta())
			io.mostraMessaggio("Hai vinto!");
	}

	private void processaIstruzione(String istruzione) {
		Comando comando = fabbrica.costruisciComando(istruzione);
		comando.esegui(partita);
	}

	public static void main(String[] argc) {
		IO io = new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}
}
