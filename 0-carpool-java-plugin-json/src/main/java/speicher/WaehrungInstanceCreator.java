package speicher;

import com.google.gson.InstanceCreator;
import model.Waehrung;

import java.lang.reflect.Type;

/**
 * @author Yannik Schiebelhut
 */
public class WaehrungInstanceCreator implements InstanceCreator<Waehrung> {
	@Override
	public Waehrung createInstance(Type type) {
		// TODO: das ist hacky und hässlich, aber damit der Rest funktioniert ...
		return Waehrung.EuroCent;
	}
}
