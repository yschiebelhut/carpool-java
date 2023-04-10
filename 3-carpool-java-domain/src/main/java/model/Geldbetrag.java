package model;

import java.util.Objects;

/**
 * @author Yannik Schiebelhut
 */
public final class Geldbetrag {
	private final int betrag;
	private final Waehrung waehrung;

	public Geldbetrag(int betrag, Waehrung waehrung) {
		this.betrag = betrag;
		this.waehrung = waehrung;
	}

	public int getBetrag() {
		return betrag;
	}

	public Waehrung getWaehrung() {
		return waehrung;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Geldbetrag that = (Geldbetrag) o;
		return betrag == that.betrag && waehrung.equals(that.waehrung);
	}

	@Override
	public int hashCode() {
		return Objects.hash(betrag, waehrung);
	}
}
