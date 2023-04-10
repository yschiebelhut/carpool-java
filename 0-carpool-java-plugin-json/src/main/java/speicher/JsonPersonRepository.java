package speicher;

import com.google.gson.GsonBuilder;
import model.Person;
import model.PersonRepository;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Yannik Schiebelhut
 */
public class JsonPersonRepository implements PersonRepository {
	private final static String STANDARDPFAD = "personen.json";
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

	public static JsonPersonRepository ladenVon(String pfad) throws FileNotFoundException {
		FileReader fr = new FileReader(new File(pfad));
		return new GsonBuilder()
				.registerTypeAdapter(LocalDate.class, new LocalDateAdapter().nullSafe())
				.create()
				.fromJson(fr, JsonPersonRepository.class);
	}

	public static JsonPersonRepository ladenVonStandardpfad() throws FileNotFoundException {
		return JsonPersonRepository.ladenVon(JsonPersonRepository.STANDARDPFAD);
	}

	public void schreibenNach(String pfad) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(pfad));
		new GsonBuilder()
				.registerTypeAdapter(LocalDate.class, new LocalDateAdapter().nullSafe())
				.create()
				.toJson(this, pw);
		pw.flush();
	}

	public void schreibenNachStandardpfad() throws IOException {
		this.schreibenNach(JsonPersonRepository.STANDARDPFAD);
	}
}
