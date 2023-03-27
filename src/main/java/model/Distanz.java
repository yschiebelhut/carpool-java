package model;

import java.util.Objects;

/**
 * @author Yannik Schiebelhut
 */
public class Distanz {
	private final double betrag;
	private final Streckeneinheit einheit;

	public Distanz(double betrag, Streckeneinheit einheit) {
		this.betrag = betrag;
		this.einheit = einheit;
	}

	public double getBetrag() {
		return betrag;
	}

	public Streckeneinheit getEinheit() {
		return einheit;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Distanz distanz1 = (Distanz) o;

		double absoluteDistanzA = this.betrag * this.einheit.getFactor();
		double absoluteDistanzB = distanz1.getBetrag() * distanz1.getEinheit().getFactor();
		double genauigkeit = 1e-6;
		return Math.abs(absoluteDistanzA - absoluteDistanzB) < genauigkeit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(betrag, einheit);
	}
}
