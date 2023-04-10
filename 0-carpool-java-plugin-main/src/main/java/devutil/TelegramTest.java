package devutil;

import model.Adresse;
import model.Ort;
import model.Person;
import model.Strasse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yannik Schiebelhut
 */
public class TelegramTest {
	static List<Person> personen = new ArrayList<>();

	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
		personen.add(
				new Person(
						"Peter",
						new Adresse(
								new Strasse("Sesamstraße", "12"),
								new Ort("Karlsruhe", "12345")
						),
						"228799678"
				)
		);
		personen.add(
				new Person(
						"Silie",
						new Adresse(
								new Strasse("Sesamstraße", "12"),
								new Ort("Karlsruhe","12345")
						),
						"228799678"
				)
		);

		personen.get(0).setTelegramChatId("0815");
		personen.get(1).setTelegramChatId("0815");

		List<Double> amount = new ArrayList<>();
		amount.add(3939.221);
		amount.add(234567.3);

		HttpClient client = HttpClient.newHttpClient();

		for (int i = 0; i < personen.size(); i++) {
			String uri = String.format("https://api.telegram.org/FILLMEIN/sendMessage?chat_id=%s&text=%s", personen.get(i).getTelegramChatId(), amount.get(i));
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(uri)).build();
			var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		}
	}
}
