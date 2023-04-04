package speicher;

import com.google.gson.InstanceCreator;
import model.Streckeneinheit;

import java.lang.reflect.Type;

/**
 * @author Yannik Schiebelhut
 */
public class StreckeneinheitInstanceCreator implements InstanceCreator<Streckeneinheit> {
	@Override
	public Streckeneinheit createInstance(Type type) {
		// TODO: das ist hacky und hässlich, aber damit der Rest funktioniert ...
		return Streckeneinheit.Kilometer;
	}
}
