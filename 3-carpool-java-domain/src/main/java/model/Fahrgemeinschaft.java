package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
	private List<UUID> standardmitglieder = new ArrayList<>();
	private List<Fahrperiode> fahrperioden = new ArrayList<>();

	public Fahrgemeinschaft(String name) {
		if (fahrgemeinschaften.stream().anyMatch(fahrgemeinschaft -> fahrgemeinschaft.getName().equals(name))) {
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

	public List<UUID> getStandardmitglieder() {
		return standardmitglieder;
	}

	public List<Fahrperiode> getFahrperioden() {
		return fahrperioden;
	}

	public void addStandardmitglied(UUID personenID) {
		this.standardmitglieder.add(personenID);
	}

	public void entferneStandardmitglied(UUID personenID) {
		this.standardmitglieder.remove(personenID);
	}

	public void addFahrperiode(Fahrperiode fahrperiode) {
		this.fahrperioden.add(fahrperiode);
		fahrperiode.setFahrgemeinschaft(this);
	}

	@Override
	public String toString() {
		return this.name;
	}
}
