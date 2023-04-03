package speicher;

import model.Fahrgemeinschaft;
import model.FahrgemeinschaftRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yannik Schiebelhut
 */
public class JsonFahrgemeinschaftRepository implements FahrgemeinschaftRepository {
	private final Map<String, Fahrgemeinschaft> fahrgemeinschaften = new HashMap<>();

	@Override
	public void speichere(Fahrgemeinschaft fahrgemeinschaft) {
		this.fahrgemeinschaften.put(fahrgemeinschaft.getName(), fahrgemeinschaft);
	}

	@Override
	public Iterable<Fahrgemeinschaft> gibAlle() {
		return this.fahrgemeinschaften.values();
	}
}
