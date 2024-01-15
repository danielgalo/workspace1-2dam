package persistence.dao.interfaces;

import persistence.entities.Company;

/**
 * Interfaz DAO de la compañia
 */
public interface CompanyDaoI {

	/**
	 * 
	 * @param name
	 * @return
	 */
	public Company searchCompanyByNombre(final String nombre);

}
