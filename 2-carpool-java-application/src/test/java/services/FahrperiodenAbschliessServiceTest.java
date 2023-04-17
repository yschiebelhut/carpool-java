package services;

import model.*;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.easymock.EasyMock.*;

/**
 * @author Yannik Schiebelhut
 */
public class FahrperiodenAbschliessServiceTest {
	@Test
	public void test_actionPerformed() {
		Person p1 = new Person("Peter", null, "1");
		Person p2 = new Person("Silie", null, "2");
		Person p3 = new Person("Alice", null, "3");

		Map<UUID, Geldbetrag> ergebnisMap = new HashMap<>();
		ergebnisMap.put(p1.getId(), new Geldbetrag(50, Waehrung.EuroCent));
		ergebnisMap.put(p2.getId(), new Geldbetrag(100, Waehrung.EuroCent));
		ergebnisMap.put(p3.getId(), new Geldbetrag(150, Waehrung.EuroCent));

		Fahrperiode fahrperiode = mock(Fahrperiode.class);
		fahrperiode.abschliessen();
		expectLastCall().andVoid();
		expect(fahrperiode.getErgebnis()).andReturn(ergebnisMap);
		replay(fahrperiode);

		PersonRepository repository = mock(PersonRepository.class);
		expect(repository.finde(p1.getId())).andReturn(Optional.of(p1));
		expect(repository.finde(p2.getId())).andReturn(Optional.of(p2));
		expect(repository.finde(p3.getId())).andReturn(Optional.of(p3));
		replay(repository);

		IPayPalLinkBuilder payPalLinkBuilder = mock(IPayPalLinkBuilder.class);
		expect(payPalLinkBuilder.getLinkFor(ergebnisMap.get(p1.getId()))).andReturn("link1");
		expect(payPalLinkBuilder.getLinkFor(ergebnisMap.get(p2.getId()))).andReturn("link2");
		expect(payPalLinkBuilder.getLinkFor(ergebnisMap.get(p3.getId()))).andReturn("link3");
		replay(payPalLinkBuilder);

		TelegramClient telegramClient = mock(TelegramClient.class);
		telegramClient.send(p1.getTelegramChatId(), "link1");
		expectLastCall().andVoid();
		telegramClient.send(p2.getTelegramChatId(), "link2");
		expectLastCall().andVoid();
		telegramClient.send(p3.getTelegramChatId(), "link3");
		expectLastCall().andVoid();
		replay(telegramClient);

		FahrperiodenAbschliessService service = new FahrperiodenAbschliessService(fahrperiode, repository,
				telegramClient, payPalLinkBuilder);
		service.actionPerformed(new ActionEvent(new Object(), 12, "someCommand"));

		verify(fahrperiode);
		verify(repository);
		verify(payPalLinkBuilder);
		verify(telegramClient);
	}
}
