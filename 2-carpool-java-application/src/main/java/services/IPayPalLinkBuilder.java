package services;

import model.Geldbetrag;

/**
 * @author Yannik Schiebelhut
 */
public interface IPayPalLinkBuilder {
	String getLinkFor(Geldbetrag geldbetrag);
}
