package speicher;

import model.Person;
import model.PersonRepository;

import java.util.*;

/**
 * @author Yannik Schiebelhut
 */
public class JsonPersonRepository implements PersonRepository {
	// TODO: JSON Speicherung ergänzen

	private final Map<UUID, Person> personen = new HashMap<>();

	@Override
	public void speichere(Person person) {
		this.personen.put(person.getId(), person);
	}

	@Override
	public Iterable<Person> gibAlle() {
		return this.personen.values();
	}

	@Override
	public Iterable<Person> gibAlleAus(List<UUID> personenListe) {
		return this.personen.values().stream().filter(person -> personenListe.contains(person.getId())).toList();
	}

	@Override
	public Optional<Person> finde(UUID personenId) {
		return Optional.ofNullable(personen.get(personenId));
	}
}
