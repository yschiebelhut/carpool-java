package services;

/**
 * @author Yannik Schiebelhut
 */
public interface TelegramClient {
	void send(String chatId, String message);
}
