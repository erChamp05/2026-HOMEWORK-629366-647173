package it.uniroma3.diadia;

import java.util.Scanner;

/**
 * IOConsole con Scanner creato una sola volta per l'intera sessione di gioco.
 * Implementa AutoCloseable per permettere l'uso con try-with-resource in main().
 *
 * Bug originale: leggiRiga() creava un nuovo Scanner(System.in) ad ogni chiamata
 * e lo chiudeva subito dopo, chiudendo System.in in modo permanente.
 */
public class IOConsole implements IO, AutoCloseable {

	private final Scanner scanner = new Scanner(System.in);

	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}

	@Override
	public String leggiRiga() {
		if (scanner.hasNextLine())
			return scanner.nextLine();
		return "";
	}

	@Override
	public void close() {
		scanner.close();
	}
}
