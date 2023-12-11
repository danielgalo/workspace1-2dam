package aadd.hibernate.tarea02.persistencias.dao;

import org.hibernate.Session;

import aadd.hibernate.tarea02.entidades.Modulo;
import aadd.hibernate.tarea02.persistencia.CommonDaoI;

/**
 * Objeto de acceso a datos de Modulos
 */
public class ModuloDaoImpl implements CommonDaoI<Modulo> {

	@Override
	public void inserta(Modulo paramT, Session session) {

		if (paramT != null) {
			session.save(paramT);
		}
	}

}
