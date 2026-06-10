package it.uniroma3.diadia.giocatore;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Giocatore {

	private static final int CFU_DEFAULT = 20;
	private static final int PESO_BORSA_DEFAULT = 10;

	private int cfu;
	private Borsa borsa;

	public Giocatore() {
		Properties props = caricaProperties();
		this.cfu = Integer.parseInt(props.getProperty("CFU_INIZIALI", String.valueOf(CFU_DEFAULT)));
		int pesoMax = Integer.parseInt(props.getProperty("PESO_MAX_BORSA", String.valueOf(PESO_BORSA_DEFAULT)));
		this.borsa = new Borsa(pesoMax);
	}

	private static Properties caricaProperties() {
		Properties props = new Properties();
		try (InputStream in = Giocatore.class.getClassLoader().getResourceAsStream("diadia.properties")) {
			if (in != null)
				props.load(in);
		} catch (IOException e) {
			// usa i default
		}
		return props;
	}

	public Borsa getBorsa() {
		return this.borsa;
	}

	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
}
