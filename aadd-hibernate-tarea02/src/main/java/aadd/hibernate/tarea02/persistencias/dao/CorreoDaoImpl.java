package aadd.hibernate.tarea02.persistencias.dao;

import org.hibernate.Session;

import aadd.hibernate.tarea02.entidades.Correo;
import aadd.hibernate.tarea02.persistencia.CommonDaoI;

/**
 * Objeto de acceso a datos del correo
 */
public class CorreoDaoImpl implements CommonDaoI<Correo> {

	@Override
	public void inserta(Correo correo, Session session) {
		if (correo != null) {
			// Guardo el correo
			session.save(correo);
		}

	}

}
