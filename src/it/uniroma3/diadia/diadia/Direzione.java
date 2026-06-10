package it.uniroma3.diadia;

/**
 * Enum che rappresenta le quattro direzioni cardinali del labirinto.
 * Sostituisce l'uso di stringhe letterali come "nord", "sud", "est", "ovest".
 *
 * @author docente di POO
 * @version enum
 */
public enum Direzione {
	NORD, SUD, EST, OVEST;

	/** Converte una stringa (case-insensitive) nella direzione corrispondente, o null. */
	public static Direzione get(String s) {
		if (s == null) return null;
		try {
			return valueOf(s.toUpperCase());
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
