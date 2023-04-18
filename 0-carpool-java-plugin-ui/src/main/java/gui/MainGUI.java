package gui;

import model.Fahrgemeinschaft;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yannik Schiebelhut
 */
public class MainGUI extends JFrame {

	private final Controller controller;


	public MainGUI(@NotNull Controller controller) throws HeadlessException {
		this.controller = controller;
		this.setTitle("Fahrgemeinschaftsverwaltung");

		JPanel mainContainer = new JPanel();
		mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));

		JPanel auswahl = new JPanel();
		auswahl.setLayout(new BoxLayout(auswahl, BoxLayout.X_AXIS));

		auswahl.add(new JLabel("Fahrgemeinschaft auswählen: "));

		final JComboBox<Fahrgemeinschaft> dropDownMenue = new JComboBox<>();
		this.controller.getFahrgemeinschaftRepository().gibAlle().forEach(dropDownMenue::addItem);
		dropDownMenue.setMaximumSize(dropDownMenue.getPreferredSize());
		dropDownMenue.setAlignmentX(Component.LEFT_ALIGNMENT);
		auswahl.add(dropDownMenue);

		JButton buttonFahrgemeinschaftAuswaehlen = new JButton("öffnen");
		buttonFahrgemeinschaftAuswaehlen.addActionListener(e -> {
			System.out.println(dropDownMenue.getSelectedItem() + ", " + dropDownMenue.getSelectedIndex());
			Fahrgemeinschaft ausgewaehlteFahrgemeinschaft = (Fahrgemeinschaft) dropDownMenue.getSelectedItem();
			FahrgemeinschaftGUI fahrGUI = new FahrgemeinschaftGUI(this.controller, ausgewaehlteFahrgemeinschaft);
			Controller.lock(this, fahrGUI);
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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
