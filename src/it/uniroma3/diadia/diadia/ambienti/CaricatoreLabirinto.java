package it.uniroma3.diadia.ambienti;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

/**
 * Carica un labirinto da un file di testo.
 *
 * Formato atteso (sezioni marcate da parola chiave seguita da ":"):
 *
 *   Stanze:
 *   <nome stanza>
 *   ...
 *
 *   StanzeBuie:
 *   <nome stanza> <attrezzo necessario>
 *   ...
 *
 *   StanzeBloccate:
 *   <nome stanza> <direzione bloccata> <attrezzo sbloccante>
 *   ...
 *
 *   StanzeMagiche:
 *   <nome stanza>
 *   ...
 *
 *   Adiacenze:
 *   <stanzaDa> <stanzaA> <direzione>
 *   ...
 *
 *   Attrezzi:
 *   <nome attrezzo> <peso> <stanza>
 *   ...
 *
 *   Personaggi:
 *   <tipo> <nome> <stanza> <presentazione>
 *   ...
 *
 *   Inizio:
 *   <nome stanza>
 *
 *   Vincente:
 *   <nome stanza>
 *
 * @author docente di POO
 * @version Es.15-16
 */
public class CaricatoreLabirinto {

	private static final String SEZIONE_STANZE         = "Stanze:";
	private static final String SEZIONE_STANZE_BUIE    = "StanzeBuie:";
	private static final String SEZIONE_STANZE_BLOCC   = "StanzeBloccate:";
	private static final String SEZIONE_STANZE_MAGICHE = "StanzeMagiche:";
	private static final String SEZIONE_ADIACENZE      = "Adiacenze:";
	private static final String SEZIONE_ATTREZZI       = "Attrezzi:";
	private static final String SEZIONE_PERSONAGGI     = "Personaggi:";
	private static final String SEZIONE_INIZIO         = "Inizio:";
	private static final String SEZIONE_VINCENTE       = "Vincente:";

	private final Reader reader;
	private Labirinto.LabirintoBuilder builder;

	public CaricatoreLabirinto(String nomeFile) throws IOException {
		this(new FileReader(nomeFile));
	}

	public CaricatoreLabirinto(Reader reader) {
		this.reader = reader;
	}

	public Labirinto carica() throws IOException {
		this.builder = Labirinto.newBuilder();
		try (BufferedReader br = new BufferedReader(reader)) {
			String sezioneCorrente = null;
			String linea;
			while ((linea = br.readLine()) != null) {
				linea = linea.trim();
				if (linea.isEmpty())
					continue;
				if (linea.endsWith(":")) {
					sezioneCorrente = linea;
					continue;
				}
				if (sezioneCorrente != null)
					processaLinea(sezioneCorrente, linea);
			}
		}
		return builder.getLabirinto();
	}

	private void processaLinea(String sezione, String linea) {
		String[] token = linea.split("\\s+");
		switch (sezione) {
			case SEZIONE_STANZE:
				builder.addStanza(linea);
				break;
			case SEZIONE_STANZE_BUIE:
				if (token.length >= 2)
					builder.addStanzaBuia(token[0], token[1]);
				break;
			case SEZIONE_STANZE_BLOCC:
				if (token.length >= 3)
					builder.addStanzaBloccata(token[0], token[1], token[2]);
				break;
			case SEZIONE_STANZE_MAGICHE:
				builder.addStanzaMagica(linea);
				break;
			case SEZIONE_ADIACENZE:
				if (token.length >= 3)
					builder.addAdiacenza(token[0], token[1], token[2]);
				break;
			case SEZIONE_ATTREZZI:
				if (token.length >= 3) {
					int peso = Integer.parseInt(token[1]);
					builder.addAttrezzoInStanza(token[0], peso, token[2]);
				}
				break;
			case SEZIONE_PERSONAGGI:
				if (token.length >= 3)
					processaPersonaggio(token);
				break;
			case SEZIONE_INIZIO:
				builder.setStanzaIniziale(linea);
				break;
			case SEZIONE_VINCENTE:
				builder.setStanzaVincente(linea);
				break;
		}
	}

	private void processaPersonaggio(String[] token) {
		String tipo   = token[0];
		String nome   = token[1];
		String stanza = token[2];
		String presentazione = token.length >= 4
			? String.join(" ", java.util.Arrays.copyOfRange(token, 3, token.length))
			: "";

		switch (tipo.toLowerCase()) {
			case "strega":
				builder.addPersonaggio(new Strega(nome, presentazione), stanza);
				break;
			case "mago":
				builder.addPersonaggio(new Mago(nome, presentazione), stanza);
				break;
			case "cane":
				builder.addPersonaggio(new Cane(nome, presentazione), stanza);
				break;
		}
	}
}
