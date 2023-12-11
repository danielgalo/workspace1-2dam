package aadd.hibernate.tarea02.persistencia;

import org.hibernate.Session;

/**
 * Interfaz para objetos de acceso a datos
 * 
 * @param <T>
 */
public interface CommonDaoI<T> {

	/**
	 * Inserta un objeto
	 * 
	 * @param paramT
	 */
	public void inserta(final T paramT, Session session);

}
