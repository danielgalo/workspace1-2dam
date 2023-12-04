package aadd.hibernate.tarea02.persistencias.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import aadd.hibernate.tarea02.entidades.Correo;
import aadd.hibernate.tarea02.persistencia.CommonDaoI;
import aadd.hibernate.tarea02.utils.HibernateUtils;

public class CorreoDaoImpl implements CommonDaoI<Correo> {

	@Override
	public void inserta(Correo correo) {

		// Obtiene sesion
		SessionFactory sf = HibernateUtils.getSessionFactory();
		// Abre sesion y abro una transacción
		Session session = sf.openSession();
		session.getTransaction().begin();
		// Guardo el profesor y hago commit
		session.save(correo);
		session.getTransaction().commit();
		// Cierro sesion
		HibernateUtils.closeSession(sf);

	}

}
