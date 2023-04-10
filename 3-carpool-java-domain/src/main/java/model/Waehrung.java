package model;

import java.util.Objects;

/**
 * @author Yannik Schiebelhut
 */
public abstract class Waehrung {
	public static final Waehrung EuroCent = new EuroCent();

	private final String name;

	public Waehrung(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	private static final class EuroCent extends Waehrung {
		public EuroCent() {
			super("EuroCent");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Waehrung waehrung = (Waehrung) o;
		return name.equals(waehrung.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
