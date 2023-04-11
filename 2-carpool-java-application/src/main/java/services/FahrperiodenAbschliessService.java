package services;

import model.Fahrperiode;
import model.Person;
import model.PersonRepository;
import paypal.PayPalLinkBuilder;
import telegram.Telegram;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Yannik Schiebelhut
 */
public class FahrperiodenAbschliessService implements ActionListener {
	private final Fahrperiode fahrperiode;
	private final PersonRepository repository;

	public FahrperiodenAbschliessService(Fahrperiode fahrperiode, PersonRepository personRepository) {
		this.fahrperiode = fahrperiode;
		this.repository = personRepository;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.fahrperiode.abschliessen();
		var ergebnis = this.fahrperiode.getErgebnis();
		ergebnis.keySet().forEach(uuid -> {
			Person p = this.repository.finde(uuid).orElseThrow();
			Telegram client = new Telegram();
			PayPalLinkBuilder payPalLinkBuilder = new PayPalLinkBuilder();
			client.send(p.getTelegramChatId(), payPalLinkBuilder.getLinkFor(ergebnis.get(uuid)));
		});
		// TODO: Telegram nach außen in Plugin-Schicht für Dependency Inversion
	}
}
