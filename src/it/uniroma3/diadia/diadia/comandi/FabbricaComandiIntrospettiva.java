package it.uniroma3.diadia.comandi;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import it.uniroma3.diadia.IO;

/**
 * Fabbrica di comandi basata su introspezione (Java Reflection).
 * Dati i nomi dei comandi disponibili, istanzia le classi Comando
 * corrispondenti cercandole nel package it.uniroma3.diadia.comandi
 * con la convenzione "Comando" + <nome capitalizzato>.
 *
 * @author docente di POO
 * @version introspettiva
 */
public class FabbricaComandiIntrospettiva implements FabbricaDiComandi {

	private static final String PACKAGE_COMANDI = "it.uniroma3.diadia.comandi";

	static final List<String> NOMI_COMANDI = Arrays.asList(
		"vai", "prendi", "posa", "guarda", "aiuto", "fine",
		"saluta", "interagisci", "regala"
	);

	private IO io;

	public FabbricaComandiIntrospettiva(IO io) {
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

		Comando comando = null;
		if (nomeComando != null) {
			String nomeClasse = PACKAGE_COMANDI + ".Comando"
				+ Character.toUpperCase(nomeComando.charAt(0))
				+ nomeComando.substring(1);
			try {
				Class<?> clazz = Class.forName(nomeClasse);
				comando = (Comando) clazz.getDeclaredConstructor().newInstance();
			} catch (Exception e) {
				// classe non trovata o non istanziabile: comando non valido
			}
		}

		if (comando == null)
			comando = new ComandoNonValido();

		comando.setParametro(parametro);
		comando.setIO(this.io);
		return comando;
	}

	public static List<String> getNomiComandi() {
		return NOMI_COMANDI;
	}
}
