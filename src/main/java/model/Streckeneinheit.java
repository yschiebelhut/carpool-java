package model;

import java.util.Objects;

/**
 * @author Yannik Schiebelhut
 */
public abstract class Streckeneinheit {
	public static final Streckeneinheit Kilometer = new Kilometer();
	private final String name;

	public Streckeneinheit(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	private static final class Kilometer extends Streckeneinheit {
		public Kilometer() {
			super("Kilometer");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Streckeneinheit that = (Streckeneinheit) o;
		return name.equals(that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
