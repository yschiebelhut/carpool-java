package speicher;

import com.google.gson.GsonBuilder;
import model.Fahrgemeinschaft;
import model.FahrgemeinschaftRepository;
import model.Streckeneinheit;
import model.Waehrung;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yannik Schiebelhut
 */
public class JsonFahrgemeinschaftRepository implements FahrgemeinschaftRepository {

	private final static String STANDARDPFAD = "fahrgemeinschaften.json";
	private final Map<String, Fahrgemeinschaft> fahrgemeinschaften = new HashMap<>();

	@Override
	public void speichere(Fahrgemeinschaft fahrgemeinschaft) {
		this.fahrgemeinschaften.put(fahrgemeinschaft.getName(), fahrgemeinschaft);
	}

	@Override
	public Iterable<Fahrgemeinschaft> gibAlle() {
		return this.fahrgemeinschaften.values();
	}

	public static JsonFahrgemeinschaftRepository ladenVon(String pfad) throws FileNotFoundException {
		FileReader fr = new FileReader(pfad);
		JsonFahrgemeinschaftRepository repositoryAusDatei = new GsonBuilder()
				.registerTypeAdapter(LocalDate.class, new LocalDateAdapter().nullSafe())
				.registerTypeAdapter(Streckeneinheit.class, new StreckeneinheitInstanceCreator())
				.registerTypeAdapter(Waehrung.class, new WaehrungInstanceCreator())
				.create()
				.fromJson(fr, JsonFahrgemeinschaftRepository.class);
		repositoryAusDatei.fahrgemeinschaften.values().forEach(fahrgemeinschaft -> {
			fahrgemeinschaft.getFahrperioden().forEach(fahrperiode -> {
				fahrperiode.setFahrgemeinschaft(fahrgemeinschaft);
			});
		});
		return repositoryAusDatei;
	}

	public static JsonFahrgemeinschaftRepository ladeVonStandardpfad() throws FileNotFoundException {
		return JsonFahrgemeinschaftRepository.ladenVon(JsonFahrgemeinschaftRepository.STANDARDPFAD);
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
		this.schreibenNach(JsonFahrgemeinschaftRepository.STANDARDPFAD);
	}
}
