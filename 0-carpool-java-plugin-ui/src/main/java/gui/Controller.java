package gui;

import model.FahrgemeinschaftRepository;
import model.PersonRepository;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

	public static void lock(JFrame parent, JFrame popup) {
		popup.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				parent.setEnabled(true);
			}
		});
		parent.setEnabled(false);
	}
}
