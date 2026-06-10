package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione di IO che simula l'interazione utente tramite liste predefinite.
 * Usata per i test: consente di "iniettare" comandi e leggere i messaggi prodotti.
 *
 * @author docente di POO
 * @version JCF
 */
public class IOSimulator implements IO {

	private List<String> righeInput;
	private int indiceInput;
	private List<String> messaggiOutput;

	public IOSimulator(String... inputs) {
		this.righeInput = new ArrayList<>();
		for (String s : inputs)
			this.righeInput.add(s);
		this.indiceInput = 0;
		this.messaggiOutput = new ArrayList<>();
	}

	@Override
	public String leggiRiga() {
		if (indiceInput < righeInput.size())
			return righeInput.get(indiceInput++);
		return "";
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		this.messaggiOutput.add(messaggio);
	}

	public String getMessaggio(int i) {
		return messaggiOutput.get(i);
	}

	public int getNumeroMessaggi() {
		return messaggiOutput.size();
	}
}
