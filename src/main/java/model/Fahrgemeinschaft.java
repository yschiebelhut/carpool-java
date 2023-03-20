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
	private String name;
	private List<Person> standardmitglieder;
	private List<Fahrperiode> fahrperioden;
}
