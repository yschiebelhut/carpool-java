package gui;

import model.Fahrgemeinschaft;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yannik Schiebelhut
 */
public class MitgliederFahrgemeinschaftGUI extends JFrame {
	private final Controller controller;
	private final Fahrgemeinschaft fahrgemeinschaft;

	private JList<Person> listMitglieder = new JList<>();
	private JList<Person> listSonstigePersonen = new JList<>();

	public MitgliederFahrgemeinschaftGUI(JFrame parent, Controller controller, Fahrgemeinschaft fahrgemeinschaft) throws HeadlessException {
		this.controller = controller;
		this.fahrgemeinschaft = fahrgemeinschaft;

		this.setTitle("Mitglieder verwalten");

		this.updateMitgliederListe();
		JPanel panelLinks = new JPanel();
		panelLinks.setLayout(new BorderLayout());
		JLabel labelListeMitglieder = new JLabel("Liste Mitglieder:");
		panelLinks.add(labelListeMitglieder, BorderLayout.NORTH);
		JScrollPane paneListMitglieder = new JScrollPane(this.listMitglieder);
		panelLinks.add(paneListMitglieder, BorderLayout.CENTER);
		this.add(panelLinks, BorderLayout.WEST);


		this.updateListeSonstigerPersonen();
		JPanel panelSonstigePersonen = new JPanel();
		panelSonstigePersonen.setLayout(new BorderLayout());
		JLabel labelListeSonstigePersonen = new JLabel("Liste sonstiger Personen:");
		panelSonstigePersonen.add(labelListeSonstigePersonen, BorderLayout.NORTH);
		JScrollPane paneListPersonen = new JScrollPane(this.listSonstigePersonen);
		panelSonstigePersonen.add(paneListPersonen, BorderLayout.CENTER);
		this.add(panelSonstigePersonen, BorderLayout.CENTER);

		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new GridLayout(3, 1, 5, 5));

		JButton buttonHinzufugen = new JButton("ausgewählte hinzufügen");
		panelButtons.add(buttonHinzufugen);
		buttonHinzufugen.addActionListener(e -> {
			List<Person> ausgewaehltePersonen = this.listSonstigePersonen.getSelectedValuesList();
			ausgewaehltePersonen.forEach(person -> this.fahrgemeinschaft.addStandardmitglied(person.getId()));
			this.updateMitgliederListe();
			this.updateListeSonstigerPersonen();
		});

		JButton buttonEntfernen = new JButton("ausgewählte entfernen");
		panelButtons.add(buttonEntfernen);
		buttonEntfernen.addActionListener(e -> {
			List<Person> ausgewaehltePersonen = this.listMitglieder.getSelectedValuesList();
			ausgewaehltePersonen.forEach(person -> this.fahrgemeinschaft.entferneStandardmitglied(person.getId()));
			this.updateMitgliederListe();
			this.updateListeSonstigerPersonen();
		});

		JButton buttonNeuePerson = new JButton("neue Person");
		panelButtons.add(buttonNeuePerson);
		buttonNeuePerson.addActionListener(e -> {
			NeuePersonGUI neuePersonGUI = new NeuePersonGUI(this, this.controller);
			Controller.lock(this, neuePersonGUI);
		});

		this.add(panelButtons, BorderLayout.EAST);


		this.pack();
		this.setVisible(true);
	}

	public void updateListeSonstigerPersonen() {
		List<Person> allePersonen = new ArrayList<>();
		this.controller.getPersonRepository().gibAlle().forEach(allePersonen::add);
		List<Person> mitglieder = new ArrayList<>();
		this.controller.getPersonRepository().gibAlleAus(this.fahrgemeinschaft.getStandardmitglieder()).forEach(mitglieder::add);
		allePersonen.removeAll(mitglieder);
		listSonstigePersonen.setListData(allePersonen.toArray(new Person[0]));
	}

	public void updateMitgliederListe() {
		List<Person> personen = new ArrayList<>();
		this.controller.getPersonRepository().gibAlleAus(this.fahrgemeinschaft.getStandardmitglieder()).forEach(personen::add);
		listMitglieder.setListData(personen.toArray(new Person[0]));
	}
}
