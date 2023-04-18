package gui;

import model.Fahrgemeinschaft;
import model.Fahrperiode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Yannik Schiebelhut
 */
public class FahrgemeinschaftGUI extends JFrame {

	private final Controller controller;
	private final Fahrgemeinschaft fahrgemeinschaft;

	private JList<Fahrperiode> listPerioden;
	private JScrollPane paneListPerioden;

	public FahrgemeinschaftGUI(Controller controller, Fahrgemeinschaft fahrgemeinschaft) throws HeadlessException {
		super(fahrgemeinschaft.getName());
		this.controller = controller;
		this.fahrgemeinschaft = fahrgemeinschaft;

		listPerioden = new JList<>(this.fahrgemeinschaft.getFahrperioden().toArray(new Fahrperiode[0]));
		paneListPerioden = new JScrollPane(listPerioden);
		this.add(paneListPerioden, BorderLayout.WEST);
		listPerioden.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JList list = (JList) e.getSource();
				Rectangle r = list.getCellBounds(0, list.getLastVisibleIndex());
				if (e.getButton() == MouseEvent.BUTTON1 && r != null && r.contains(e.getPoint())) {
					if (e.getClickCount() == 2) {
						int index = list.locationToIndex(e.getPoint());
						list.setSelectedIndex(index);
						Fahrperiode selectedPeriode = (Fahrperiode) list.getSelectedValue();
						System.out.printf("ausgewählter Index %d, Periode %s\n", index, selectedPeriode.getId());

						// Öffne Detailansicht
						starteFahrperiodeGUI(selectedPeriode);
					}
				}
			}
		});

		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(2, 1, 5, 5));

		JButton buttonMitglieder = new JButton("Mitglieder verwalten");
		buttons.add(buttonMitglieder);
		buttonMitglieder.addActionListener(e -> {
			MitgliederFahrgemeinschaftGUI mitgliederGUI = new MitgliederFahrgemeinschaftGUI(this, this.controller, this.fahrgemeinschaft);
		});

		JButton buttonNeuePeriode = new JButton("neue Periode");
		buttons.add(buttonNeuePeriode);
		buttonNeuePeriode.addActionListener(e -> {
			NeueFahrperiodeGUI neuGUI = new NeueFahrperiodeGUI(this, this.controller, fahrgemeinschaft);
			Controller.lock(this, neuGUI);
		});

		this.add(buttons);

		this.pack();
		this.setVisible(true);
	}


	private void starteFahrperiodeGUI(Fahrperiode fahrperiode) {
		FahrperiodeGUI periodeGUI = new FahrperiodeGUI(this.controller, fahrperiode);
		Controller.lock(this, periodeGUI);
	}

	public void update() {
		listPerioden.setListData(this.fahrgemeinschaft.getFahrperioden().toArray(new Fahrperiode[0]));
		this.pack();
	}
}
