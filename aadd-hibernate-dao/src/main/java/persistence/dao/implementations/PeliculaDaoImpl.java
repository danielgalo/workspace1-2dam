package persistence.dao.implementations;

import org.hibernate.Session;

import persistence.dao.interfaces.PeliculaDaoI;
import persistence.entities.Pelicula;

/**
 * Implementación del DAO de películas
 */
public class PeliculaDaoImpl extends CommonDaoImpl<Pelicula> implements PeliculaDaoI {

	/**
	 * Constructor
	 * 
	 * @param session
	 */
	public PeliculaDaoImpl(Session session) {
		super(session);
	}

	@Override
	public Pelicula searchMovieByTitle(String title) {
		return null;
	}

}
