package gui;

import devutil.DataGenerator;
import model.Fahrgemeinschaft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yannik Schiebelhut
 */
public class MainGUI extends JFrame {

	private final Controller controller;


	public MainGUI(Controller controller) throws HeadlessException {
		this.controller = controller;
		this.setTitle("Fahrgemeinschaftsverwaltung");

		JPanel mainContainer = new JPanel();
		mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));

		JPanel auswahl = new JPanel();
		auswahl.setLayout(new BoxLayout(auswahl, BoxLayout.X_AXIS));

		auswahl.add(new JLabel("Fahrgemeinschaft auswählen: "));

//		Fahrgemeinschaft[] datenArray = this.controller.getFahrgemeinschaftRepository().gibAlle()
		final JComboBox<Fahrgemeinschaft> dropDownMenue = new JComboBox<>();
		this.controller.getFahrgemeinschaftRepository().gibAlle().forEach(dropDownMenue::addItem);
		dropDownMenue.setMaximumSize(dropDownMenue.getPreferredSize());
		dropDownMenue.setAlignmentX(Component.LEFT_ALIGNMENT);
		auswahl.add(dropDownMenue);

		JButton buttonFahrgemeinschaftAuswaehlen = new JButton("öffnen");
		buttonFahrgemeinschaftAuswaehlen.addActionListener(e -> {
			System.out.println(dropDownMenue.getSelectedItem() + ", " + dropDownMenue.getSelectedIndex());
			Fahrgemeinschaft ausgewaehlteFahrgemeinschaft = (Fahrgemeinschaft) dropDownMenue.getSelectedItem();
			FahrgemeinschaftGUI fahrGUI = new FahrgemeinschaftGUI(this, this.controller, ausgewaehlteFahrgemeinschaft);
			fahrGUI.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					fahrGUI.getParentFrame().setEnabled(true);
				}
			});
			this.setEnabled(false);
		});
		auswahl.add(buttonFahrgemeinschaftAuswaehlen);

		mainContainer.add(auswahl);

		JButton buttonNeueFahrgemeinschaft = new JButton("neue Fahrgemeinschaft");
		buttonNeueFahrgemeinschaft.addActionListener(e -> {
			String name = JOptionPane.showInputDialog(this, "Bitte Namen für neue Fahrgemeinschaft eingeben:");
			if (name.length() != 0) {
				Fahrgemeinschaft tmp = new Fahrgemeinschaft(name);
				this.controller.getFahrgemeinschaftRepository().speichere(tmp);
				dropDownMenue.addItem(tmp);
			}
		});
		mainContainer.add(buttonNeueFahrgemeinschaft);

		this.add(mainContainer);
		this.setMinimumSize(new Dimension(500, 300));
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // TODO: save changes
		this.setVisible(true);
	}
}
