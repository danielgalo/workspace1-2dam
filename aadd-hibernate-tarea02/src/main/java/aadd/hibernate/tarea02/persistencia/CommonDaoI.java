package aadd.hibernate.tarea02.persistencia;

/**
 * 
 * @param <T>
 */
public interface CommonDaoI<T> {

	/**
	 * Inserta un objeto
	 * 
	 * @param paramT
	 */
	public void inserta(final T paramT);

}
