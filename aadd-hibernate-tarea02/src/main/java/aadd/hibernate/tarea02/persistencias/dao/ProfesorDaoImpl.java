package aadd.hibernate.tarea02.persistencias.dao;

import org.hibernate.Session;

import aadd.hibernate.tarea02.entidades.Profesor;
import aadd.hibernate.tarea02.persistencia.CommonDaoI;

/**
 * Objeto de acceso de profesor
 */
public class ProfesorDaoImpl implements CommonDaoI<Profesor> {

	@Override
	public void inserta(Profesor profesor, Session session) {

		if (profesor != null) {
			// Guardo el profesor
			session.save(profesor);
		}

	}

}
