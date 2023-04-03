package gui;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yannik Schiebelhut
 */
public class NeuePersonGUI extends JFrame implements IPopup {
	private final JFrame parent;
	private final Controller controller;

	private final JTextArea areaName = new JTextArea();
	private final JTextArea areaStrasse = new JTextArea("Sesamstraße");
	private final JTextArea areaHausnummer = new JTextArea("12");
	private final JTextArea areaOrt = new JTextArea("Karlsruhe");
	private final JTextArea areaPlz = new JTextArea("12345");

	public NeuePersonGUI(JFrame parent, Controller controller) throws HeadlessException {
		this.parent = parent;
		this.controller = controller;

		this.setTitle("Neue Person hinzufügen");

		JPanel panelEingabefelder = new JPanel();
		panelEingabefelder.setLayout(new GridLayout());

		panelEingabefelder.add(new JLabel("Name:"));

		panelEingabefelder.add(new JLabel("Straße:"));

		panelEingabefelder.add(new JLabel("Hausnummer:"));

		panelEingabefelder.add(new JLabel("Ort"));

		panelEingabefelder.add(new JLabel("Postleitzahl"));

		this.add(new JLabel("Hi from se neue Personen Fenster"));

		this.pack();
		this.setVisible(true);
	}

	@Override
	public JFrame getParentFrame() {
		return this.parent;
	}
}
