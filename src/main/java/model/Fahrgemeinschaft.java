package model;

import java.util.List;

/**
 * @author Yannik Schiebelhut
 */
public class Fahrgemeinschaft {
	/**
	 * In der Annahme, dass die Anzahl der Fahrgemeinschaften, die ein Fahrer hat, auf einige wenige beschränkt ist,
	 * wird der Name einer Fahrgemeinschaft zusätzlich als ihr eindeutiger Identifier verwendet.
	 */
	private final String name;
	private List<Person> standardmitglieder;
	private List<Fahrperiode> fahrperioden;

	public Fahrgemeinschaft(String name) {
		this.name = name;
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
}
