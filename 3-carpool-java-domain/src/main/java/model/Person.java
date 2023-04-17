package model;

import java.util.Objects;
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
	private String telegramChatId;


	public Person(String name, Adresse adresse, String telegramChatId) {
		this.id = UUID.randomUUID();
		if (name.equals("")) {
			throw new RuntimeException("Der Name einer Person darf nicht leer sein.");
		}
		this.name = name;
		this.adresse = adresse;
		this.telegramChatId = telegramChatId;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public String getTelegramChatId() {
		return telegramChatId;
	}

	public void setTelegramChatId(String telegramChatId) {
		this.telegramChatId = telegramChatId;
	}

	public String toString() {
		return this.name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return Objects.equals(id, person.id) && Objects.equals(name, person.name) && Objects.equals(adresse, person.adresse) && Objects.equals(telegramChatId, person.telegramChatId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, adresse, telegramChatId);
	}
}
