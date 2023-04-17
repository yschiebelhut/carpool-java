package speicher;

import model.Adresse;
import model.Ort;
import model.Person;
import model.Strasse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Yannik Schiebelhut
 */
@SuppressWarnings("unchecked")
public class JsonPersonRepositoryTest {
	@Test
	public void test_neuesRepositoryIstLeer() {
		JsonPersonRepository repository = new JsonPersonRepository();

		Iterable<Person> rueckgabe = repository.gibAlle();

		assertThat(rueckgabe).isEmpty();
	}

	@Test
	public void test_speichern() {
		Person person = new Person(
				"Peter",
				new Adresse(
						new Strasse("Sesamstraße", "12"),
						new Ort("Karlsruhe", "12345")
				),
				"thisisachatid"
		);
		JsonPersonRepository repository = new JsonPersonRepository();

		repository.speichere(person);

		Map<UUID, Person> personMap;
		try {
			Field personenField = repository.getClass().getDeclaredField("personen");
			personenField.setAccessible(true);
			personMap = (Map<UUID, Person>) personenField.get(repository);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		assertThat(personMap).containsExactly(new Map.Entry<UUID, Person>() {
			@Override
			public UUID getKey() {
				return person.getId();
			}

			@Override
			public Person getValue() {
				return person;
			}

			@Override
			public Person setValue(Person value) {
				return null;
			}
		});
	}

	@Test
	public void test_gibAllePersonen() {
		Person p1 = new Person("Peter", null, "someTelegramChatId");
		Person p2 = new Person("Silie", null, "anotherTelegramChatId");
		Person p3 = new Person("Alice", null, "aThirdChatId");
		JsonPersonRepository repository = new JsonPersonRepository();
		repository.speichere(p1);
		repository.speichere(p2);
		repository.speichere(p3);

		Iterable<Person> personen = repository.gibAlle();

		assertThat(personen).containsExactlyInAnyOrder(p1, p2, p3);
	}

	@Test
	public void test_gibAllePersonenAusListe() {
		Person p1 = new Person("Peter", null, "someTelegramChatId");
		Person p2 = new Person("Silie", null, "anotherTelegramChatId");
		Person p3 = new Person("Alice", null, "aThirdChatId");
		JsonPersonRepository repository = new JsonPersonRepository();
		repository.speichere(p1);
		repository.speichere(p2);
		repository.speichere(p3);

		List<UUID> personenListe = new ArrayList<>();
		personenListe.add(p1.getId());
		personenListe.add(p3.getId());
		UUID id;
		do {
			id = UUID.randomUUID();
		} while (personenListe.contains(id));
		personenListe.add(id);

		Iterable<Person> personen = repository.gibAlleAus(personenListe);

		assertThat(personen).containsExactlyInAnyOrder(p1, p3);
	}

	@Test
	public void test_findeVorhandenePerson() {
		Person p1 = new Person("Peter", null, "someTelegramChatId");
		Person p2 = new Person("Silie", null, "anotherTelegramChatId");
		Person p3 = new Person("Alice", null, "aThirdChatId");
		JsonPersonRepository repository = new JsonPersonRepository();
		repository.speichere(p1);
		repository.speichere(p2);
		repository.speichere(p3);

		Optional<Person> person = repository.finde(p2.getId());

		assertThat(person).contains(p2);
	}

	@Test
	public void test_findeNichtVorhandenePerson() {
		Person p1 = new Person("Peter", null, "someTelegramChatId");
		JsonPersonRepository repository = new JsonPersonRepository();
		repository.speichere(p1);
		UUID id;
		do {
			id = UUID.randomUUID();
		} while (id.equals(p1.getId()));

		Optional<Person> person = repository.finde(id);

		assertThat(person).isEmpty();
	}

	@Test
	public void test_speichernUndLadenErzeugtAltenZustand(@TempDir Path tmpDir) throws IOException {
		Person p1 = new Person(
				"Peter",
				new Adresse(
						new Strasse("Sesamstraße", "12"),
						new Ort("Karlsruhe", "12345")
				),
				"someChatID"
		);
		JsonPersonRepository repository = new JsonPersonRepository();
		repository.speichere(p1);

		String filename = tmpDir.toString() + "/" + "personen.json";

		repository.schreibenNach(filename);

		JsonPersonRepository geladenesRepository = JsonPersonRepository.ladenVon(filename);
		assertThat(geladenesRepository.finde(p1.getId())).isEqualTo(repository.finde(p1.getId()));
	}
}
