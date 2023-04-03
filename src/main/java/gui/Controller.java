package gui;

import devutil.DataGenerator;
import model.FahrgemeinschaftRepository;
import model.PersonRepository;
import speicher.JsonFahrgemeinschaftRepository;
import speicher.JsonPersonRepository;

/**
 * @author Yannik Schiebelhut
 */
public class Controller {
	private final PersonRepository personRepository;
	private final FahrgemeinschaftRepository fahrgemeinschaftRepository;
	private final MainGUI mainGUI;

	public Controller() {
		this.personRepository = new JsonPersonRepository();
		this.fahrgemeinschaftRepository = new JsonFahrgemeinschaftRepository();
		DataGenerator.generateDemoData(this.personRepository, this.fahrgemeinschaftRepository);
		this.mainGUI = new MainGUI(this);
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
