package speicher;

import model.Fahrgemeinschaft;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Yannik Schiebelhut
 */
@SuppressWarnings("unchecked")
public class JsonFahrgemeinschaftRepositoryTest {
	@Test
	public void test_neuesRepositoryIstLeer() {
		JsonFahrgemeinschaftRepository repository = new JsonFahrgemeinschaftRepository();

		Iterable<Fahrgemeinschaft> rueckgabe = repository.gibAlle();

		assertThat(rueckgabe).isEmpty();
	}

	@Test
	public void test_speichern() {
		Fahrgemeinschaft fahrgemeinschaft = new Fahrgemeinschaft("Testfahrgemeinschaft");
		JsonFahrgemeinschaftRepository repository = new JsonFahrgemeinschaftRepository();

		repository.speichere(fahrgemeinschaft);

		Map<String, Fahrgemeinschaft> fahrgemeinschaftMap;
		try {
			Field fahrgemeinschaftenField = repository.getClass().getDeclaredField("fahrgemeinschaften");
			fahrgemeinschaftenField.setAccessible(true);
			fahrgemeinschaftMap = (Map<String, Fahrgemeinschaft>) fahrgemeinschaftenField.get(repository);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		assertThat(fahrgemeinschaftMap).containsExactly(new Map.Entry<String,
				Fahrgemeinschaft>() {
			@Override
			public String getKey() {
				return "Testfahrgemeinschaft";
			}

			@Override
			public Fahrgemeinschaft getValue() {
				return fahrgemeinschaft;
			}

			@Override
			public Fahrgemeinschaft setValue(Fahrgemeinschaft value) {
				return null;
			}
		});
	}

	@Test
	public void test_gibAlleFahrgemeinschaften() {
		Fahrgemeinschaft f1 = new Fahrgemeinschaft("Fahrgemeinschaft 1");
		Fahrgemeinschaft f2 = new Fahrgemeinschaft("Fahrgemeinschaft 2");
		JsonFahrgemeinschaftRepository repository = new JsonFahrgemeinschaftRepository();
		repository.speichere(f1);
		repository.speichere(f2);

		Iterable<Fahrgemeinschaft> fahrgemeinschaften = repository.gibAlle();

		assertThat(fahrgemeinschaften).containsExactlyInAnyOrder(f1, f2);
	}
}
