package gui;

import model.Geldbetrag;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.UUID;

/**
 * @author Yannik Schiebelhut
 */
public class ErgebnisGUI extends JFrame {
	private final Controller controller;

	public ErgebnisGUI(Controller controller, Map<UUID, Geldbetrag> ergebnis) throws HeadlessException {
		this.controller = controller;

		this.setTitle("Ergebnis");

		this.setLayout(new GridLayout(ergebnis.keySet().size(), 2, 5, 5));

		ergebnis.forEach((uuid, geldbetrag) -> {
			Person aktuellerMitfahrer = this.controller.getPersonRepository().finde(uuid).orElseThrow();
			JTextArea areaTmp;
			areaTmp = new JTextArea(aktuellerMitfahrer.getName());
			areaTmp.setEditable(false);
			this.add(areaTmp);
			areaTmp = new JTextArea(String.valueOf((double) ergebnis.get(uuid).getBetrag() / 100.));
			areaTmp.setEditable(false);
			this.add(areaTmp);
		});

		this.setMinimumSize(new Dimension(150, 0));
		this.pack();
		this.setVisible(true);
	}
}
