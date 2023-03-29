package gui;

import model.Fahrgemeinschaft;
import model.Fahrperiode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Yannik Schiebelhut
 */
public class FahrgemeinschaftGUI extends JFrame implements IPopup {

	private final JFrame parent;
	private final Fahrgemeinschaft fahrgemeinschaft;

	private JList<Fahrperiode> listPerioden;
	private JScrollPane paneListPerioden;

	public FahrgemeinschaftGUI(JFrame parent, Fahrgemeinschaft fahrgemeinschaft) throws HeadlessException {
		super(fahrgemeinschaft.getName());
		this.parent = parent;
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
						System.out.printf("ausgewählter Index %d, Periode %s", index, selectedPeriode.getId());

						// Öffne Detailansicht
						launchFahrperiodeGUI(selectedPeriode);
					}
				}
			}
		});

		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(2, 1, 5, 5));

		JButton buttonMitglieder = new JButton("Mitglieder verwalten");
		buttons.add(buttonMitglieder);
		buttonMitglieder.addActionListener(e -> {
			// TODO: Mitgliederverwaltung implementieren
		});

		JButton buttonNeuePeriode = new JButton("neue Periode");
		buttons.add(buttonNeuePeriode);
		buttonNeuePeriode.addActionListener(e -> {
			NeueFahrperiodeGUI neuGUI = new NeueFahrperiodeGUI(this, fahrgemeinschaft);
			neuGUI.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					neuGUI.getParentFrame().setEnabled(true);
					super.windowClosing(e);
				}


			});
			this.setEnabled(false);
		});

		this.add(buttons);

		this.pack();
		this.setVisible(true);
	}


	private void launchFahrperiodeGUI(Fahrperiode fahrperiode) {
		FahrperiodeGUI periodeGUI = new FahrperiodeGUI(this, fahrperiode);
		periodeGUI.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				periodeGUI.getParentFrame().setEnabled(true);
				super.windowClosing(e);
			}
		});
		this.setEnabled(false);
	}

	@Override
	public JFrame getParentFrame() {
		return this.parent;
	}

	public void update() {
		listPerioden.setListData(this.fahrgemeinschaft.getFahrperioden().toArray(new Fahrperiode[0]));
		this.pack();
	}
}
