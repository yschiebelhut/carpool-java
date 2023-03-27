package model;

import java.util.Objects;

/**
 * @author Yannik Schiebelhut
 */
public abstract class Streckeneinheit {
	public static final Streckeneinheit Meter = new Meter();
	public static final Streckeneinheit Kilometer = new Kilometer();
	private final String name;
	private final String symbol;
	private final int factor;

	public Streckeneinheit(String name, String symbol, int factor) {
		this.name = name;
		this.symbol = symbol;
		this.factor = factor;
	}

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

	public int getFactor() {
		return factor;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Streckeneinheit that = (Streckeneinheit) o;
		return factor == that.factor && name.equals(that.name) && symbol.equals(that.symbol);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, symbol, factor);
	}

	private static final class Meter extends Streckeneinheit {
		public Meter() {
			super("Meter", "m", 1000);
		}
	}

	private static final class Kilometer extends Streckeneinheit {
		public Kilometer() {
			super("Kilometer", "km", 1000);
		}
	}
}
