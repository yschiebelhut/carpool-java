package gui;

import devutil.DataGenerator;
import model.FahrgemeinschaftRepository;
import model.PersonRepository;
import speicher.JsonFahrgemeinschaftRepository;
import speicher.JsonPersonRepository;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Yannik Schiebelhut
 */
public class Controller {
	private final JsonPersonRepository personRepository;
	private final JsonFahrgemeinschaftRepository fahrgemeinschaftRepository;
	private final MainGUI mainGUI;

	public Controller() {
//		this.personRepository = new JsonPersonRepository();
//		this.fahrgemeinschaftRepository = new JsonFahrgemeinschaftRepository();
//		DataGenerator.generateDemoData(this.personRepository, this.fahrgemeinschaftRepository);
		try {
			this.personRepository = JsonPersonRepository.ladenVonStandardpfad();
			this.fahrgemeinschaftRepository = JsonFahrgemeinschaftRepository.ladeVonStandardpfad();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		this.mainGUI = new MainGUI(this);
		mainGUI.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					personRepository.schreibenNachStandardpfad();
					fahrgemeinschaftRepository.schreibenNachStandardpfad();
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		});
	}

	public static void main(String[] args) {
		new Controller();
	}

	public PersonRepository getPersonRepository() {
		return personRepository;
	}

	public FahrgemeinschaftRepository getFahrgemeinschaftRepository() {
		return fahrgemeinschaftRepository;
	}
}
