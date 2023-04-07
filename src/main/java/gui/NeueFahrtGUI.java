package gui;

import model.Fahrperiode;
import model.Fahrt;
import model.Person;

import javax.naming.OperationNotSupportedException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Yannik Schiebelhut
 */
public class NeueFahrtGUI extends JFrame implements IPopup {
	private final FahrperiodeGUI parent;
	private final Controller controller;
	private final Fahrperiode fahrperiode;

	private JCheckBox[] checkBoxen;
	private List<UUID> standardmitglieder;

	public NeueFahrtGUI(FahrperiodeGUI parent, Controller controller, Fahrperiode fahrperiode) throws HeadlessException {
		this.parent = parent;
		this.controller = controller;
		this.fahrperiode = fahrperiode;

		this.setTitle("Neue Fahrt");

		JPanel panelCheckBoxen = new JPanel();
		this.standardmitglieder = this.fahrperiode.getFahrgemeinschaft().getStandardmitglieder();
		this.checkBoxen = new JCheckBox[this.standardmitglieder.size()];
		panelCheckBoxen.setLayout(new GridLayout(this.standardmitglieder.size(), 1, 5, 5));
		for (int i = 0; i < this.standardmitglieder.size(); i++) {
			Person aktuellePerson =
					this.controller.getPersonRepository().finde(this.standardmitglieder.get(i)).orElseThrow();
			checkBoxen[i] = new JCheckBox(aktuellePerson.getName());
			panelCheckBoxen.add(checkBoxen[i]);
		}
		this.add(panelCheckBoxen);


		JButton buttonFahrtAnlegen = new JButton("Fahrt anlegen");
		this.add(buttonFahrtAnlegen, BorderLayout.SOUTH);
		buttonFahrtAnlegen.addActionListener(e -> {
			List<UUID> mitfahrer = new ArrayList<>();
			for (int i = 0; i < this.checkBoxen.length; i++) {
				if (checkBoxen[i].isSelected()) {
					mitfahrer.add(standardmitglieder.get(i));
				}
			}
			if (mitfahrer.size() == 0) {
				JOptionPane.showMessageDialog(this, "Es müssen Mitfahrer ausgewählt werden.");
			} else {
				Fahrt neueFahrt = new Fahrt(LocalDate.now(), mitfahrer);
				try {
					this.fahrperiode.addFahrt(neueFahrt);
				} catch (OperationNotSupportedException ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage());
				}
				this.parent.updateFahrtenListe();
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
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
