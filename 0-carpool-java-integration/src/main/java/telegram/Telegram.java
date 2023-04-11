package telegram;

import model.Geldbetrag;
import model.Waehrung;
import services.TelegramClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author Yannik Schiebelhut
 */
public class Telegram implements TelegramClient {
	public void send(String chatId, String message) {
		String telegramToken = System.getenv("TELEGRAM_TOKEN");
		if (telegramToken == null || telegramToken.equals("")) {
			throw new RuntimeException("Umgebungsvariable TELEGRAM_TOKEN muss gesetzt sein.");
		}

		try {
			HttpClient client = HttpClient.newHttpClient();
			String uri = String.format("https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s", telegramToken,
					chatId, message);
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(uri)).build();
			client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
