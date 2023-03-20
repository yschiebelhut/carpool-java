package model;

import java.util.Objects;

/**
 * @author Yannik Schiebelhut
 */
public class Adresse {
	private final Strasse strasse;
	private final Ort ort;

	public Adresse(Strasse strasse, Ort ort) {
		this.strasse = strasse;
		this.ort = ort;
	}

	public Strasse getStrasse() {
		return strasse;
	}

	public Ort getOrt() {
		return ort;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Adresse adresse = (Adresse) o;
		return strasse.equals(adresse.strasse) && ort.equals(adresse.ort);
	}

	@Override
	public int hashCode() {
		return Objects.hash(strasse, ort);
	}
}
