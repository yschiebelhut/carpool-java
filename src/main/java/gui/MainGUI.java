package gui;

import model.Fahrgemeinschaft;
import model.Fahrperiode;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yannik Schiebelhut
 */
public class MainGUI extends JFrame {

	private Fahrgemeinschaft fahrgemeinschaft;

	public MainGUI(Fahrgemeinschaft f) throws HeadlessException {
//		this.fahrgemeinschaft = f;
//
//		this.setTitle("Fahrgemeinschaftsverwaltung");
//
//		JPanel panelPerioden = new JPanel();
//		panelPerioden.setLayout(new BorderLayout());
//		panelPerioden.add(new JLabel("Liste der Fahrperioden:"), BorderLayout.NORTH);
//		DefaultListModel<Fahrperiode> model = new DefaultListModel<>();
//		model.addAll(this.fahrgemeinschaft.getFahrtperioden());
//		System.out.println(this.fahrgemeinschaft.getFahrtperioden());
//		JList periodeListe = new JList(model);
//		JScrollPane panePeriodenListe = new JScrollPane(periodeListe);
//		panelPerioden.add(panePeriodenListe);
//
//		this.add(panelPerioden);
//
//		this.setVisible(true);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
//		Fahrgemeinschaft f = new Fahrgemeinschaft();
//		f.addFahrtperiode(new Fahrperiode("Periode 1", 50.0, 6.5, 1679, 5));
//		f.addFahrtperiode(new Fahrperiode("Periode 2", 50.0, 6.5, 1679, 5));
//		f.addFahrtperiode(new Fahrperiode("Periode 3", 50.0, 6.5, 1679, 5));
//		f.addFahrtperiode(new Fahrperiode("Periode 4", 50.0, 6.5, 1679, 5));
//		f.addFahrtperiode(new Fahrperiode("Periode 5", 50.0, 6.5, 1679, 5));
//		MainGUI gui = new MainGUI(f);


//		JFrame frame = new JFrame();
//		frame.setTitle("Listfoo");
//		DefaultListModel fruits = new DefaultListModel();
//		fruits.addElement("banana");
//		fruits.addElement("mango");
//		fruits.addElement("mango");
//		fruits.addElement("mango");
//		fruits.addElement("mango");
//		fruits.addElement("mango");
//		fruits.addElement("mango");
//		fruits.addElement("mango");
//		fruits.addElement("mango");
//		JList list = new JList(fruits);
//		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//		list.setLayoutOrientation(JList.VERTICAL);
//
//		JScrollPane pane = new JScrollPane(list);
////		pane.setPreferredSize(new Dimension(225, 125));
//		frame.add(pane);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(225, 125);
//		frame.setVisible(true);
//		frame.validate();
	}
}
