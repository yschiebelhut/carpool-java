package services;

import model.Fahrperiode;
import model.Person;
import model.PersonRepository;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author Yannik Schiebelhut
 */
public class FahrperiodenAbschliessService implements ActionListener {
	private final Fahrperiode fahrperiode;
	private final PersonRepository repository;

	private final TelegramClient client;
	private final IPayPalLinkBuilder payPalLinkBuilder;

	public FahrperiodenAbschliessService(Fahrperiode fahrperiode, PersonRepository personRepository,
										 TelegramClient client, IPayPalLinkBuilder payPalLinkBuilder) {
		this.fahrperiode = fahrperiode;
		this.repository = personRepository;
		this.client = client;
		this.payPalLinkBuilder = payPalLinkBuilder;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.fahrperiode.abschliessen();
		var ergebnis = this.fahrperiode.getErgebnis();
		ergebnis.keySet().forEach(uuid -> {
			Person p = this.repository.finde(uuid).orElseThrow();
			try {
				this.client.send(p.getTelegramChatId(), this.payPalLinkBuilder.getLinkFor(ergebnis.get(uuid)));
			} catch (URISyntaxException | IOException | InterruptedException ex) {
				throw new RuntimeException(ex);
			}
		});
	}
}
