package telegram;

import services.TelegramClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author Yannik Schiebelhut
 */
public class Telegram implements TelegramClient {
	private final HttpClient client;

	public Telegram(HttpClient client) {
		this.client = client;
	}

	public Telegram() {
		this.client = HttpClient.newHttpClient();
	}

	private String telegramToken() {
		String telegramToken = System.getenv("TELEGRAM_TOKEN");
		if (telegramToken == null || telegramToken.equals("")) {
			throw new RuntimeException("Umgebungsvariable TELEGRAM_TOKEN muss gesetzt sein.");
		}
		return telegramToken;
	}

	public void send(String chatId, String message) throws URISyntaxException, IOException, InterruptedException {
		client.send(request(chatId, message), HttpResponse.BodyHandlers.ofString());
	}

	private HttpRequest request(String chatId, String message) throws URISyntaxException {
		return HttpRequest.newBuilder().uri(requestUriFor(chatId, message)).build();
	}

	private URI requestUriFor(String chatId, String message) throws URISyntaxException {
		return new URI(String.format("https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s", telegramToken(),
				chatId, message));
	}
}
