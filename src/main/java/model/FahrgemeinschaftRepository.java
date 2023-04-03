package model;

/**
 * @author Yannik Schiebelhut
 */
public interface FahrgemeinschaftRepository {
	void speichere(Fahrgemeinschaft fahrgemeinschaft);
	Iterable<Fahrgemeinschaft> gibAlle();
}
