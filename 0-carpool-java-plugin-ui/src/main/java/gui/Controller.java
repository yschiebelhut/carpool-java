package gui;

import model.FahrgemeinschaftRepository;
import model.PersonRepository;

/**
 * @author Yannik Schiebelhut
 */
public class Controller {
	private final PersonRepository personRepository;
	private final FahrgemeinschaftRepository fahrgemeinschaftRepository;
	private final MainGUI mainGUI;

	public Controller(PersonRepository personRepository, FahrgemeinschaftRepository fahrgemeinschaftRepository) {
		this.fahrgemeinschaftRepository = fahrgemeinschaftRepository;
		this.personRepository = personRepository;
		this.mainGUI = new MainGUI(this);
	}

	public PersonRepository getPersonRepository() {
		return personRepository;
	}

	public FahrgemeinschaftRepository getFahrgemeinschaftRepository() {
		return fahrgemeinschaftRepository;
	}
}
