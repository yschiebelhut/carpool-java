import model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Yannik Schiebelhut
 */
public class FahrperiodeTest {
	@Test
	public void test_ergebnisBerechnen() throws OperationNotSupportedException {
		UUID u1 = UUID.randomUUID();
		UUID u2 = UUID.randomUUID();
		UUID u3 = UUID.randomUUID();
		List<UUID> fahrt1 = new ArrayList<>();
		fahrt1.add(u1);
		fahrt1.add(u2);
		fahrt1.add(u3);

		List<UUID> fahrt2 = new ArrayList<>();
		fahrt2.add(u1);
		fahrt2.add(u2);

		List<UUID> fahrt3 = new ArrayList<>();
		fahrt3.add(u3);


		Fahrperiode fahrperiode = new Fahrperiode(new Distanz(50.0, Streckeneinheit.Kilometer), 6.5, new Geldbetrag(5,
				Waehrung.EuroCent), new Geldbetrag(200, Waehrung.EuroCent));
		fahrperiode.addFahrt(new Fahrt(null, fahrt1));
		fahrperiode.addFahrt(new Fahrt(null, fahrt2));
		fahrperiode.addFahrt(new Fahrt(null, fahrt3));

		Map<UUID, Geldbetrag> expectedErgebnis = new HashMap<>();
		expectedErgebnis.put(u1, new Geldbetrag(525, Waehrung.EuroCent));
		expectedErgebnis.put(u2, new Geldbetrag(525, Waehrung.EuroCent));
		expectedErgebnis.put(u3, new Geldbetrag(675, Waehrung.EuroCent));

		Map<UUID, Geldbetrag> actualErgebnis = fahrperiode.getErgebnis();

		Assertions.assertThat(fahrperiode.getErgebnis()).containsExactlyInAnyOrderEntriesOf(expectedErgebnis);
	}

	@Test
	public void test_abgeschlossenePeriodenSindReadOnly() {
		Fahrperiode fahrperiode = new Fahrperiode(new Distanz(50.0, Streckeneinheit.Kilometer), 6.5, new Geldbetrag(5,
				Waehrung.EuroCent), new Geldbetrag(200, Waehrung.EuroCent));
		fahrperiode.abschliessen();

		List<UUID> mitfahrer = new ArrayList<>();
		Assertions.assertThatThrownBy(() -> fahrperiode.addFahrt(new Fahrt(LocalDate.now(), mitfahrer)))
				.isInstanceOf(OperationNotSupportedException.class);
	}
}
