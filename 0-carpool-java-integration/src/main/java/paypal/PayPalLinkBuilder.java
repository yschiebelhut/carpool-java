package paypal;

import model.Geldbetrag;
import model.Waehrung;

/**
 * @author Yannik Schiebelhut
 */
public class PayPalLinkBuilder {
	public String getLinkFor(Geldbetrag geldbetrag) {
		String paypalUsername = System.getenv("PAYPAL_USERNAME");
		if (paypalUsername == null || paypalUsername.equals("")) {
			throw new RuntimeException("Umgebungsvariable PAYPAL_USERNAME muss gesetzt sein.");
		}
		String euro = String.valueOf(geldbetrag.getBetrag() / 100);
		String cent = String.valueOf(geldbetrag.getBetrag() % 100);
		if (cent.length() == 1) {
			cent = "0" + cent;
		}
		return String.format("https://paypal.me/%s/%s.%sEUR", paypalUsername, euro, cent);
	}
}
