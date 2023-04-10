package gui;

import model.Adresse;
import model.Ort;
import model.Person;
import model.Strasse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

/**
 * @author Yannik Schiebelhut
 */
public class NeuePersonGUI extends JFrame implements IPopup {
	private final MitgliederFahrgemeinschaftGUI parent;
	private final Controller controller;

	private final JTextArea areaName = new JTextArea();
	private final JTextArea areaStrasse = new JTextArea("Sesamstraße");
	private final JTextArea areaHausnummer = new JTextArea("12");
	private final JTextArea areaOrt = new JTextArea("Karlsruhe");
	private final JTextArea areaPlz = new JTextArea("12345");
	private final JTextArea areaTelegramChatId = new JTextArea("228799678");

	public NeuePersonGUI(MitgliederFahrgemeinschaftGUI parent, Controller controller) throws HeadlessException {
		this.parent = parent;
		this.controller = controller;

		this.setTitle("Neue Person hinzufügen");

		JPanel panelEingabefelder = new JPanel();
		panelEingabefelder.setLayout(new GridLayout(6, 2, 5, 5));

		panelEingabefelder.add(new JLabel("Name:"));
		panelEingabefelder.add(areaName);

		panelEingabefelder.add(new JLabel("Straße:"));
		panelEingabefelder.add(areaStrasse);

		panelEingabefelder.add(new JLabel("Hausnummer:"));
		panelEingabefelder.add(areaHausnummer);

		panelEingabefelder.add(new JLabel("Ort"));
		panelEingabefelder.add(areaOrt);

		panelEingabefelder.add(new JLabel("Postleitzahl"));
		panelEingabefelder.add(areaPlz);

		panelEingabefelder.add(new JLabel("Telegram Chat ID"));
		panelEingabefelder.add(areaTelegramChatId);

		this.add(panelEingabefelder, BorderLayout.CENTER);

		JButton buttonPersonAnlegen = new JButton("Person anlegen");
		this.add(buttonPersonAnlegen, BorderLayout.SOUTH);
		buttonPersonAnlegen.addActionListener(e -> {
			try {
				Person neuePerson = new Person(
						areaName.getText(),
						new Adresse(
								new Strasse(areaStrasse.getText(), areaHausnummer.getText()),
								new Ort(areaOrt.getText(), areaPlz.getText())
						),
						areaTelegramChatId.getText()
				);
				this.controller.getPersonRepository().speichere(neuePerson);
				this.parent.updateMitgliederListe();
				this.parent.updateListeSonstigerPersonen();
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(this, exception.getMessage());
			}
		});

		this.pack();
		this.setVisible(true);
	}

	@Override
	public JFrame getParentFrame() {
		return this.parent;
	}
}
