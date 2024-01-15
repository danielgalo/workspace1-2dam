package persistence.dao.implementations;

import org.hibernate.Session;

import persistence.dao.interfaces.CompanyDaoI;
import persistence.entities.Company;

public class CompanyDaoImpl extends CommonDaoImpl<Company> implements CompanyDaoI {

	protected CompanyDaoImpl(Session session) {
		super(session);
	}

	@Override
	public Company searchCompanyByNombre(String nombre) {
		return null;
	}

}
