package paypal;

import model.Geldbetrag;
import model.Waehrung;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Yannik Schiebelhut
 */
public class PayPalLinkBuilderTest {
	@Test
	public void test_generierePayPalLink() {
		Geldbetrag betrag = new Geldbetrag(153, Waehrung.EuroCent);
		PayPalLinkBuilder builder = new PayPalLinkBuilder();

		String link = builder.getLinkFor(betrag);

		assertEquals(link, "https://paypal.me/testuser/1.53EUR");
	}

	@Test
	public void test_generierePayPalLinkFuer1Cent() {
		Geldbetrag betrag = new Geldbetrag(1, Waehrung.EuroCent);
		PayPalLinkBuilder builder = new PayPalLinkBuilder();

		String link = builder.getLinkFor(betrag);

		assertEquals(link, "https://paypal.me/testuser/0.01EUR");
	}
}
