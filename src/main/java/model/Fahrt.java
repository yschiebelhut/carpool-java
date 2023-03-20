package model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * @author Yannik Schiebelhut
 */
public class Fahrt {
	private final LocalDate datum;

	/**
	 * Mitglieder der Fahrgemeinschaft, die bei dieser Fahrt im Auto saßen
	 */
	private final List<Person> mitfahrer;

	public Fahrt(LocalDate datum, List<Person> mitfahrer) {
		this.datum = datum;
		this.mitfahrer = mitfahrer;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public List<Person> getMitfahrer() {
		return mitfahrer;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Fahrt fahrt = (Fahrt) o;
		return datum.equals(fahrt.datum) && mitfahrer.equals(fahrt.mitfahrer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(datum, mitfahrer);
	}
}
