package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public interface Comando {
	void esegui(Partita partita);
	String getNome();
	String getParametro();
	void setParametro(String parametro);
	void setIO(IO io);
}
