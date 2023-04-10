package main;

import gui.Controller;
import speicher.JsonFahrgemeinschaftRepository;
import speicher.JsonPersonRepository;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Yannik Schiebelhut
 */
public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		JsonPersonRepository jsonPersonRepository = JsonPersonRepository.ladenVonStandardpfad();
		JsonFahrgemeinschaftRepository jsonFahrgemeinschaftRepository = JsonFahrgemeinschaftRepository.ladeVonStandardpfad();
		Controller guiController = new Controller(jsonPersonRepository, jsonFahrgemeinschaftRepository);

		Thread speicherHook = new Thread(() -> {
			try {
				jsonPersonRepository.schreibenNachStandardpfad();
				jsonFahrgemeinschaftRepository.schreibenNachStandardpfad();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		Runtime.getRuntime().addShutdownHook(speicherHook);
	}
}
