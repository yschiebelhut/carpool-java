package gui;

import model.Fahrperiode;
import model.Fahrt;
import paypal.PayPalLinkBuilder;
import services.FahrperiodenAbschliessService;
import telegram.Telegram;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 * @author Yannik Schiebelhut
 */
public class FahrperiodeGUI extends JFrame {
	private final Controller controller;
	private final Fahrperiode fahrperiode;

	private JList<Fahrt> listFahrten;
	private JScrollPane paneListFahrten;

	public FahrperiodeGUI(Controller controller, Fahrperiode fahrperiode) throws HeadlessException {
		this.controller = controller;
		this.fahrperiode = fahrperiode;
		this.setTitle(String.format("Fahrperiode %s", fahrperiode.getId()));

		// Details über Fahrperiode in Borderlayout WEST
		JPanel panelInformationen = new JPanel();
		panelInformationen.setLayout(new GridLayout(7, 2, 5, 5));
		panelInformationen.add(new JLabel("Beginn:"));
		JLabel areaBeginn = new JLabel(fahrperiode.getBeginn().toString());
		panelInformationen.add(areaBeginn);

		panelInformationen.add(new JLabel("Ende:"));
		JLabel areaEnde;
		if (!fahrperiode.getEnde().equals(LocalDate.MAX)) {
			areaEnde = new JLabel(fahrperiode.getEnde().toString());
		} else {
			areaEnde = new JLabel("-");
		}
		panelInformationen.add(areaEnde);

		panelInformationen.add(new JLabel("Distanz (km):"));
		JTextArea areaDistanz = new JTextArea(String.valueOf(fahrperiode.getDistanz().getBetrag()));
		panelInformationen.add(areaDistanz);

		panelInformationen.add(new JLabel("Spritpreis (Cent/Liter):"));
		JTextArea areaSpritpreis = new JTextArea(String.valueOf(fahrperiode.getSpritpreis().getBetrag()));
		panelInformationen.add(areaSpritpreis);

		panelInformationen.add(new JLabel("Durchschnittsverbrauch (l/100km):"));
		JTextArea areaVerbrauch = new JTextArea(String.valueOf(fahrperiode.getDurchschnittsverbrauch()));
		panelInformationen.add(areaVerbrauch);

		panelInformationen.add(new JLabel("Fixbetrag (Cent/km):"));
		JTextArea areaFixbetrag = new JTextArea(String.valueOf(fahrperiode.getFixbetrag().getBetrag()));
		panelInformationen.add(areaFixbetrag);

		panelInformationen.add(new JLabel("abgeschlossen:"));
		JLabel labelAbgeschlossen = new JLabel(String.valueOf(fahrperiode.isAbgeschlossen()));
		panelInformationen.add(labelAbgeschlossen);

		this.add(panelInformationen, BorderLayout.WEST);

		this.listFahrten = new JList<>(this.fahrperiode.getFahrten().toArray(new Fahrt[0]));
		this.paneListFahrten = new JScrollPane(this.listFahrten);
		this.add(this.paneListFahrten, BorderLayout.CENTER);

		JPanel panelButtons = new JPanel();
//		panelButtons.setLayout(new GridLayout(4, 1, 5, 5));
		panelButtons.setLayout(new GridLayout(3, 1, 5, 5));
//		JButton buttonSondermitgliederVerwalten = new JButton("Sondermitglieder verwalten");
//		panelButtons.add(buttonSondermitgliederVerwalten);
//		buttonSondermitgliederVerwalten.addActionListener(e -> {
//			// auf eine Implementierung von Sondermitgliedern in einer Fahrperiode wird verzichtet
//		});

		JButton buttonNeueFahrt = new JButton("neue Fahrt");
		panelButtons.add(buttonNeueFahrt);
		buttonNeueFahrt.addActionListener(e -> {
			NeueFahrtGUI neueFahrtGUI = new NeueFahrtGUI(this, this.controller, this.fahrperiode);
			Controller.lock(this, neueFahrtGUI);
		});
		if (this.fahrperiode.isAbgeschlossen()) {
			buttonNeueFahrt.setEnabled(false);
		}

		JButton buttonAbschliessen = new JButton("abschließen");
		panelButtons.add(buttonAbschliessen);
		Telegram client = new Telegram();
		PayPalLinkBuilder payPalLinkBuilder = new PayPalLinkBuilder();
		buttonAbschliessen.addActionListener(new FahrperiodenAbschliessService(this.fahrperiode,
				this.controller.getPersonRepository(), client, payPalLinkBuilder));
		buttonAbschliessen.addActionListener(e -> {
			buttonNeueFahrt.setEnabled(false);
			buttonAbschliessen.setEnabled(false);
		});
		if (this.fahrperiode.isAbgeschlossen()) {
			buttonAbschliessen.setEnabled(false);
		}


		JButton buttonErgebnisAnzeigen = new JButton("Ergebnis anzeigen");
		panelButtons.add(buttonErgebnisAnzeigen);
		buttonErgebnisAnzeigen.addActionListener(e -> {
			if (this.fahrperiode.getFahrten().size() == 0) {
				JOptionPane.showMessageDialog(this, "Bitte zunächst eine Fahrt anlegen.");
			} else {
				ErgebnisGUI ergebnisGUI = new ErgebnisGUI(this.controller, this.fahrperiode.getErgebnis());
				Controller.lock(this, ergebnisGUI);
			}
		});

		this.add(panelButtons, BorderLayout.EAST);

		this.pack();
		this.setVisible(true);
	}

	public void updateFahrtenListe() {
		this.listFahrten.setListData(this.fahrperiode.getFahrten().toArray(new Fahrt[0]));
	}
}
