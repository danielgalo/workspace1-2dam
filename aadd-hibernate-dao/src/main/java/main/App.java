package main;

import org.hibernate.Session;

import persistence.dao.implementations.PeliculaDaoImpl;
import persistence.entities.Pelicula;
import utils.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		Pelicula p = new Pelicula();

		p.setTitulo("Hola");

		// Abro sesi�n
		Session session = HibernateUtil.getSession();

		PeliculaDaoImpl peliDao = new PeliculaDaoImpl(session);

		try {

			peliDao.insert(p);

		} catch (Exception e) {
		}

	}
}
