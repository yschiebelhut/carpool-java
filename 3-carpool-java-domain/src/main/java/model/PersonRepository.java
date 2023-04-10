package model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Yannik Schiebelhut
 */
public interface PersonRepository {
	void speichere(Person person);

	Iterable<Person> gibAlle();

	Iterable<Person> gibAlleAus(List<UUID> personenListe);

	Optional<Person> finde(UUID personenId);
}
