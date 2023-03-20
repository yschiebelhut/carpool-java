package model;

import java.time.LocalDate;
import java.util.List;

/**
 * Eine Fahrperiode bezeichnet die Menge der Fahrten gleicher Strecke im Zeitraum zwischen zwei Tankstopps.
 *
 * @author Yannik Schiebelhut
 */
public class Fahrperiode extends Fahrt {
	/**
	 * Liste der Fahrten in der Fahrperiode
	 */
	private List<Fahrt> fahrten;

	/**
	 * Mitglieder, die nur in einer bestimmten Fahrperiode mitfahren
	 */
	private List<Person> sondermitglieder;

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


	public boolean isAbgeschlossen() {
		return this.abgeschlossen;
	}

	/**
	 * schließt eine Fahrperiode ab
	 * abgeschlossene Fahrperioden können nicht wieder geöffnet werden
	 */
	public void abschliessen() {
		this.abgeschlossen = true;
	}
}
