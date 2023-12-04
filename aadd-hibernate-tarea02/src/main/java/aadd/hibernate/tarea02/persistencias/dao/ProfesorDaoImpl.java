package aadd.hibernate.tarea02.persistencias.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import aadd.hibernate.tarea02.entidades.Profesor;
import aadd.hibernate.tarea02.persistencia.CommonDaoI;
import aadd.hibernate.tarea02.utils.HibernateUtils;

/**
 * Objeto de acceso de profesor
 */
public class ProfesorDaoImpl implements CommonDaoI<Profesor> {

	@Override
	public void inserta(Profesor profesor) {

		// Obtiene sesion
		SessionFactory sessionf = HibernateUtils.getSessionFactory();
		// Abre sesion y abro una transacción
		Session session = sessionf.openSession();
		session.getTransaction().begin();
		// Guardo el profesor y hago commit
		session.save(profesor);
		session.getTransaction().commit();
		// Cierro sesion
		HibernateUtils.closeSession(sessionf);

	}

}
