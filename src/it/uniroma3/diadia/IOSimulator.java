package it.uniroma3.diadia;

/**
 * Implementazione di IO che simula l'interazione utente tramite array predefiniti.
 * Usata per i test di accettazione (non unit-test): consente di "iniettare"
 * comandi e di leggere i messaggi prodotti dal gioco senza input manuale.
 *
 * @author docente di POO
 * @version base
 */
public class IOSimulator implements IO {

	static final private int MAX_MESSAGGI = 1000;

	private String[] righeInput;
	private int indiceInput;

	private String[] messaggiOutput;
	private int numeroMessaggi;

	public IOSimulator(String... inputs) {
		this.righeInput = inputs;
		this.indiceInput = 0;
		this.messaggiOutput = new String[MAX_MESSAGGI];
		this.numeroMessaggi = 0;
	}

	@Override
	public String leggiRiga() {
		if (indiceInput < righeInput.length)
			return righeInput[indiceInput++];
		return "";
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		if (numeroMessaggi < MAX_MESSAGGI) {
			messaggiOutput[numeroMessaggi] = messaggio;
			numeroMessaggi++;
		}
	}

	public String getMessaggio(int i) {
		return messaggiOutput[i];
	}

	public int getNumeroMessaggi() {
		return numeroMessaggi;
	}
}
