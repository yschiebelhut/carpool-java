package model;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;

/**
 * Eine Fahrperiode bezeichnet die Menge der Fahrten gleicher Strecke im Zeitraum zwischen zwei Tankstopps.
 *
 * @author Yannik Schiebelhut
 */
public class Fahrperiode {
	private final UUID id;

	private transient Fahrgemeinschaft fahrgemeinschaft;

	/**
	 * Liste der Fahrten in der Fahrperiode
	 */
	private List<Fahrt> fahrten = new ArrayList<>();

//	/**
//	 * Mitglieder, die nur in einer bestimmten Fahrperiode mitfahren
//	 */
//	private List<Person> sondermitglieder = new ArrayList<>();

	/**
	 * Distanz der einfachen Strecke (Fahrweg in eine Richtung)
	 */
	private Distanz distanz;

	/**
	 * Durchschnittsverbrauch des Fahrzeugs in Liter pro 100 km
	 */
	private double durchschnittsverbrauch;

	/**
	 * Fixbetrag pro Kilometer
	 */
	private Geldbetrag fixbetrag;

	/**
	 * Preis des getankten Sprits pro Liter. Getankt wird zu Beginn einer Fahrperiode.
	 */
	private Geldbetrag spritpreis;

	/**
	 * Beginn der Fahrperiode
	 */
	private LocalDate beginn;

	/**
	 * Ende der Fahrperiode
	 */
	private LocalDate ende;

	/**
	 * gibt an, ob an der Fahrperiode noch Änderungen vorgenommen werden dürfen
	 */
	private boolean abgeschlossen;

	public Fahrperiode(Distanz distanz, double durchschnittsverbrauch, Geldbetrag fixbetrag, Geldbetrag spritpreis) {
		this.id = UUID.randomUUID();
		this.distanz = distanz;
		this.durchschnittsverbrauch = durchschnittsverbrauch;
		this.fixbetrag = fixbetrag;
		this.spritpreis = spritpreis;
		this.beginn = LocalDate.now();
		this.ende = LocalDate.MAX;
	}

	public boolean isAbgeschlossen() {
		return this.abgeschlossen;
	}

	/**
	 * schließt eine Fahrperiode ab
	 * abgeschlossene Fahrperioden können nicht wieder geöffnet werden
	 */
	public void abschliessen() {
		this.abgeschlossen = true;
		this.ende = LocalDate.now();
	}

	public Map<UUID, Geldbetrag> getErgebnis() {
		Map<UUID, Geldbetrag> zwischenergebnis = new HashMap<>();
		this.fahrten.forEach(ergaenzeFahrtZu(zwischenergebnis));
		return zwischenergebnis;
	}

	private Consumer<Fahrt> ergaenzeFahrtZu(Map<UUID, Geldbetrag> zwischenergebnis) {
		return fahrt -> {
			//((fixbetrag * kilometer) + (l/100km * preis/l * distanzinkm/100)) / anzahlMitfahrer
			fahrt.getMitfahrer().forEach(uuid -> {
				Geldbetrag aktuellerBetrag = zwischenergebnis.getOrDefault(uuid, new Geldbetrag(0, Waehrung.EuroCent));
				Geldbetrag neuerBetrag = new Geldbetrag(aktuellerBetrag.getBetrag() + betragProMitfahrer(fahrt),
						Waehrung.EuroCent);
				zwischenergebnis.put(uuid, neuerBetrag);
			});
		};
	}

	private int betragProMitfahrer(Fahrt fahrt) {
		return (int) (((this.fixbetrag.getBetrag() * this.distanz.getBetrag()) + (this.durchschnittsverbrauch * this.spritpreis.getBetrag() * this.distanz.getBetrag() / 100)) / (fahrt.getMitfahrer().size() + 1));
	}

	public UUID getId() {
		return id;
	}

	public Fahrgemeinschaft getFahrgemeinschaft() {
		return fahrgemeinschaft;
	}

	public void setFahrgemeinschaft(Fahrgemeinschaft fahrgemeinschaft) {
		this.fahrgemeinschaft = fahrgemeinschaft;
	}

	public List<Fahrt> getFahrten() {
		return fahrten;
	}

	public void addFahrt(Fahrt f) throws OperationNotSupportedException {
		if (!this.abgeschlossen) {
			this.fahrten.add(f);
		} else {
			throw new OperationNotSupportedException("Hinzufügen einer Fahrt zu einer abgeschlossenen Fahrperiode ist nicht möglich.");
		}
	}

//	public List<Person> getSondermitglieder() {
//		return sondermitglieder;
//	}
//
//	public void addSondermitglied(Person p) {
//		this.sondermitglieder.add(p);
//	}

	public Distanz getDistanz() {
		return distanz;
	}

	public void setDistanz(Distanz distanz) {
		this.distanz = distanz;
	}

	public double getDurchschnittsverbrauch() {
		return durchschnittsverbrauch;
	}

	public void setDurchschnittsverbrauch(double durchschnittsverbrauch) {
		this.durchschnittsverbrauch = durchschnittsverbrauch;
	}

	public Geldbetrag getFixbetrag() {
		return fixbetrag;
	}

	public void setFixbetrag(Geldbetrag fixbetrag) {
		this.fixbetrag = fixbetrag;
	}

	public Geldbetrag getSpritpreis() {
		return spritpreis;
	}

	public void setSpritpreis(Geldbetrag spritpreis) {
		this.spritpreis = spritpreis;
	}

	public LocalDate getBeginn() {
		return beginn;
	}

	public void setBeginn(LocalDate beginn) {
		this.beginn = beginn;
	}

	public LocalDate getEnde() {
		return ende;
	}

	public void setEnde(LocalDate ende) {
		this.ende = ende;
	}

	public void setAbgeschlossen(boolean abgeschlossen) {
		this.abgeschlossen = abgeschlossen;
	}

	@Override
	public String toString() {
		return "Fahrperiode{" +
				"id=" + id +
				'}';
	}
}
