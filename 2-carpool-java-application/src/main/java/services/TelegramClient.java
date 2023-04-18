package services;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author Yannik Schiebelhut
 */
public interface TelegramClient {
	void send(String chatId, String message) throws URISyntaxException, IOException, InterruptedException;
}
