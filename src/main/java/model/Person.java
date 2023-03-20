package model;

import java.util.UUID;

/**
 * @author Yannik Schiebelhut
 */
public class Person {
	/**
	 * künstliche ID für eine Person, da Name und Adresse nicht ausreichen, um eine Person eindeutig zu identifizieren
	 */
	private final UUID id;
	private String name;
	private Adresse adresse;

	public Person(UUID id, String name, Adresse adresse) {
		this.id = id;
		this.name = name;
		this.adresse = adresse;
	}

	public Person(String name, Adresse adresse) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.adresse = adresse;
	}
}
