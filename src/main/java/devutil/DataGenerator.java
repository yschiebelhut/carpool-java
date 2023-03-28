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
}
