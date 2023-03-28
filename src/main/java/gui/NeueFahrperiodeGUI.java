package gui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

/**
 * @author Yannik Schiebelhut
 */
public class NeueFahrperiodeGUI extends JFrame implements IPopup {

	private final FahrgemeinschaftGUI parent;

	public NeueFahrperiodeGUI(FahrgemeinschaftGUI parent, Fahrgemeinschaft fahrgemeinschaft) throws HeadlessException {
		this.parent = parent;
		this.setTitle("Neue Fahrperiode");

		this.add(new JLabel("Bitte nachfolgende Daten ausfüllen:"), BorderLayout.NORTH);

		JPanel eingabefelder = new JPanel();
		eingabefelder.setLayout(new GridLayout(4, 2, 5, 5));

		eingabefelder.add(new JLabel("Distanz in Kilometern:"));
		JTextField fieldDistanz = new JTextField("50.0");
		eingabefelder.add(fieldDistanz);

		eingabefelder.add(new JLabel("Durchschnittsverbrauch in Litern pro 100 km:"));
		JTextField fieldVerbrauch = new JTextField("6.5");
		eingabefelder.add(fieldVerbrauch);

		//Spritpreis
		eingabefelder.add(new JLabel("Spritpreis in Cent pro Liter:"));
		JTextField fieldSpritpreis = new JTextField("165");
		eingabefelder.add(fieldSpritpreis);

		// Fixbetrag
		eingabefelder.add(new JLabel("Fixbetrag in Cent pro Kilometer:"));
		JTextField fieldFixbetrag = new JTextField("5");
		eingabefelder.add(fieldFixbetrag);

		this.add(eingabefelder, BorderLayout.CENTER);

		JButton buttonHinzufuegen = new JButton("hinzufügen");
		this.add(buttonHinzufuegen, BorderLayout.SOUTH);
		buttonHinzufuegen.addActionListener(e -> {
			fahrgemeinschaft.addFahrperiode(new Fahrperiode(
					new Distanz(Double.valueOf(fieldDistanz.getText()), Streckeneinheit.Kilometer),
					Double.valueOf(fieldVerbrauch.getText()),
					new Geldbetrag(Integer.valueOf(fieldFixbetrag.getText()), Waehrung.EuroCent),
					new Geldbetrag(Integer.valueOf(fieldSpritpreis.getText()), Waehrung.EuroCent)
			));
			this.parent.update();
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		});

		this.pack();
		this.setVisible(true);
	}

	@Override
	public JFrame getParentFrame() {
		return this.parent;
	}
}
