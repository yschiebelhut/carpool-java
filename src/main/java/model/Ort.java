package model;

import java.util.Objects;

/**
 * @author Yannik Schiebelhut
 */
public final class Ort {
	private final String ortsname;

	/**
	 * eine deutsche Postleitzahl, andere werden aktuell nicht unterstützt
	 */
	private final String plz;

	public Ort(String ortsname, String plz) {
		this.ortsname = ortsname;
		if (plz.matches("^[0-9]{5}$")) {
			this.plz = plz;
		} else {
			throw new RuntimeException("PLZ entspricht keinem erlaubten deutschen Format.");
		}
	}

	public String getOrtsname() {
		return ortsname;
	}

	public String getPlz() {
		return plz;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Ort ort = (Ort) o;
		return ortsname.equals(ort.ortsname) && plz.equals(ort.plz);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ortsname, plz);
	}
}
