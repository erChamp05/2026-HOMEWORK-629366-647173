package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import java.util.Scanner;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {

	private IO io;

	public FabbricaDiComandiFisarmonica(IO io) {
		this.io = io;
	}

	@Override
	public Comando costruisciComando(String istruzione) {
		Scanner scanner = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		if (scanner.hasNext())
			nomeComando = scanner.next();
		if (scanner.hasNext())
			parametro = scanner.next();
		scanner.close();

		Comando comando;
		if (nomeComando == null) {
			comando = new ComandoNonValido();
		} else {
			switch (nomeComando) {
				case "vai":    comando = new ComandoVai();    break;
				case "prendi": comando = new ComandoPrendi(); break;
				case "posa":   comando = new ComandoPosa();   break;
				case "guarda": comando = new ComandoGuarda(); break;
				case "aiuto":  comando = new ComandoAiuto();  break;
				case "fine":   comando = new ComandoFine();   break;
				default:       comando = new ComandoNonValido(); break;
			}
		}
		comando.setParametro(parametro);
		comando.setIO(this.io);
		return comando;
	}
}
