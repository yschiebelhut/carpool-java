package devutil;

import model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yannik Schiebelhut
 */
public class DataGenerator {
	public static List<Fahrgemeinschaft> getDemoData() {
		List<Fahrgemeinschaft> output = new ArrayList<>();
		output.add(new Fahrgemeinschaft("Fahrgemeinschaft A"));
		output.add(new Fahrgemeinschaft("Fahrgemeinschaft B"));

		output.get(0).addFahrperiode(new Fahrperiode(
				new Distanz(50.0, Streckeneinheit.Kilometer),
				6.5,
				new Geldbetrag(5, Waehrung.EuroCent),
				new Geldbetrag(161, Waehrung.EuroCent)
		));
		output.get(0).addFahrperiode(new Fahrperiode(
				new Distanz(50.0, Streckeneinheit.Kilometer),
				6.5,
				new Geldbetrag(5, Waehrung.EuroCent),
				new Geldbetrag(162, Waehrung.EuroCent)
		));
		return output;
	}

	public static void generateDemoData(
			PersonRepository personRepository,
			FahrgemeinschaftRepository fahrgemeinschaftRepository
	) {
		Fahrgemeinschaft f1 = new Fahrgemeinschaft("Fahrgemeinschaft A");
		f1.addFahrperiode(new Fahrperiode(
				new Distanz(50.0, Streckeneinheit.Kilometer),
				6.5,
				new Geldbetrag(5, Waehrung.EuroCent),
				new Geldbetrag(161, Waehrung.EuroCent)
		));
		f1.addFahrperiode(new Fahrperiode(
				new Distanz(50.0, Streckeneinheit.Kilometer),
				6.5,
				new Geldbetrag(5, Waehrung.EuroCent),
				new Geldbetrag(162, Waehrung.EuroCent)
		));
		fahrgemeinschaftRepository.speichere(f1);

		Fahrgemeinschaft f2 = new Fahrgemeinschaft("Fahrgemeinschaft B");
		fahrgemeinschaftRepository.speichere(f2);


		Person p1 = new Person(
				"Peter",
				new Adresse(
						new Strasse("Sesamstraße", "12"),
						new Ort("Karlsruhe", "12345")
				)
		);
//		f1.addStandardmitglied(p1.getId());
		personRepository.speichere(p1);

		Person p2 = new Person(
				"Silie",
				new Adresse(
						new Strasse("Sesamstraße", "12"),
						new Ort("Karlsruhe","12345")
				)
		);
		f1.addStandardmitglied(p2.getId());
		personRepository.speichere(p2);

	}
}
