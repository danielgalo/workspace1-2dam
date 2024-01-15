package persistence.dao.interfaces;

import persistence.entities.Pelicula;

/**
 * Interfaz DAO de película
 */
public interface PeliculaDaoI {

	/**
	 * Busca una película por su título
	 * 
	 * @param title título de la película
	 * @return Película con el título
	 */
	public Pelicula searchMovieByTitle(final String titulo);

}
