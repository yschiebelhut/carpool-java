package speicher;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.assertj.core.api.Assertions;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

/**
 * @author Yannik Schiebelhut
 */
public class LocalDateAdapterTest {
	@Test
	public void test_write() throws IOException {
		LocalDate date = LocalDate.of(2023, 4, 17);
		JsonWriter writer = EasyMock.createMock(JsonWriter.class);
		EasyMock.expect(writer.value(date.toString())).andReturn(writer);
		EasyMock.replay(writer);

		LocalDateAdapter adapter = new LocalDateAdapter();

		adapter.write(writer, date);

		EasyMock.verify(writer);
	}

	@Test
	public void test_read() throws IOException {
		JsonReader reader = EasyMock.createMock(JsonReader.class);
		String dateString = "2023-04-17";
		EasyMock.expect(reader.nextString()).andReturn(dateString);
		EasyMock.replay(reader);

		LocalDateAdapter adapter = new LocalDateAdapter();

		LocalDate parsedDate = adapter.read(reader);

		LocalDate expectedDate = LocalDate.parse(dateString);
		Assertions.assertThat(parsedDate).isEqualTo(expectedDate);

		EasyMock.verify(reader);
	}
}
