package model;

import java.util.Objects;

/**
 * @author Yannik Schiebelhut
 */
public class Strasse {
	private final String name;
	private final String hausnummer;

	public Strasse(String name, String hausnummer) {
		this.name = name;
		if (hausnummer.matches("^[0-9]+[a-z]?$")) {
		this.hausnummer = hausnummer;
		} else {
			throw new RuntimeException("Hausnummer entspricht keinem korrekten Format (z.B. 12, 314a");
		}
	}

	public String getName() {
		return name;
	}

	public String getHausnummer() {
		return hausnummer;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Strasse strasse = (Strasse) o;
		return name.equals(strasse.name) && hausnummer.equals(strasse.hausnummer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, hausnummer);
	}
}
