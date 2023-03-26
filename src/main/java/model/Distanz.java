package model;

import java.util.Objects;

/**
 * @author Yannik Schiebelhut
 */
public class Distanz {
	private final double distanz;
	private final Streckeneinheit einheit;

	public Distanz(double distanz, Streckeneinheit einheit) {
		this.distanz = distanz;
		this.einheit = einheit;
	}

	public double getDistanz() {
		return distanz;
	}

	public Streckeneinheit getEinheit() {
		return einheit;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Distanz distanz1 = (Distanz) o;
		return Double.compare(distanz1.distanz, distanz) == 0 && einheit.equals(distanz1.einheit);
	}

	@Override
	public int hashCode() {
		return Objects.hash(distanz, einheit);
	}
}
