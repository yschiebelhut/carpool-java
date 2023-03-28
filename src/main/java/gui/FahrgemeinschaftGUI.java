package gui;

import model.Fahrgemeinschaft;
import model.Fahrperiode;

import javax.swing.*;
import java.awt.*;
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

		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(2, 1, 5, 5));

		JButton buttonMitglieder = new JButton("Mitglieder verwalten");
		buttons.add(buttonMitglieder);
		buttonMitglieder.addActionListener(e -> {
			// TODO: implement
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


	@Override
	public JFrame getParentFrame() {
		return this.parent;
	}

	public void update() {
		listPerioden.setListData(this.fahrgemeinschaft.getFahrperioden().toArray(new Fahrperiode[0]));
		this.revalidate();
		this.repaint();
		this.pack();
		System.out.println("update was called");
	}
}
