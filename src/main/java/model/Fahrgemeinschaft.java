package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yannik Schiebelhut
 */
public class Fahrgemeinschaft {
	private static final List<Fahrgemeinschaft> fahrgemeinschaften = new ArrayList<>();
	/**
	 * In der Annahme, dass die Anzahl der Fahrgemeinschaften, die ein Fahrer hat, auf einige wenige beschränkt ist,
	 * wird der Name einer Fahrgemeinschaft zusätzlich als ihr eindeutiger Identifier verwendet.
	 */
	private final String name;
	private List<Person> standardmitglieder;
	private List<Fahrperiode> fahrperioden;

	public Fahrgemeinschaft(String name) {
		if (fahrgemeinschaften.stream().filter(fahrgemeinschaft -> fahrgemeinschaft.getName().equals(name)).count() != 0) {
			throw new RuntimeException("Fahrgemeinschaft mit diesem Namen ist bereits registriert");
		}
		this.name = name;
		this.standardmitglieder = new ArrayList<>();
		this.fahrperioden = new ArrayList<>();
		fahrgemeinschaften.add(this);
	}

	public String getName() {
		return name;
	}

	public List<Person> getStandardmitglieder() {
		return standardmitglieder;
	}

	public List<Fahrperiode> getFahrperioden() {
		return fahrperioden;
	}

	public void addStandardmitglied(Person p) {
		this.standardmitglieder.add(p);
	}

	public void addFahrperiode(Fahrperiode f) {
		this.fahrperioden.add(f);
	}

	@Override
	public String toString() {
		return this.name;
	}
}
