package aadd.hibernate.tarea02.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import aadd.hibernate.tarea02.entidades.Correo;
import aadd.hibernate.tarea02.entidades.Modulo;
import aadd.hibernate.tarea02.entidades.Profesor;
import aadd.hibernate.tarea02.persistencia.dao.CorreoDaoImpl;
import aadd.hibernate.tarea02.persistencia.dao.ProfesorDaoImpl;
import aadd.hibernate.tarea02.utils.HibernateUtils;

/**
 * Hello world!
 *
 */
public class App {

	/**
	 * Método principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Obtiene sesion
		SessionFactory sessionf = HibernateUtils.getSessionFactory();
		// Abre sesion y abro una transacción
		Session session = sessionf.openSession();
		session.getTransaction().begin();

		// Creo Objetos de acceso a datos
		ProfesorDaoImpl profesorDao = new ProfesorDaoImpl();
		CorreoDaoImpl correoDao = new CorreoDaoImpl();

		// Crea un profesor
		Profesor profesor1 = new Profesor();
		profesor1.setNombre("Paco");
		profesor1.setApellidos("Peña");

		// Crea correos asociados al profesor1
		Correo correo1 = new Correo();
		correo1.setDireccion("paco1@email.com");
		correo1.setRelatedProfesor(profesor1);

		Correo correo2 = new Correo();
		correo2.setDireccion("paco2@email.com");
		correo2.setRelatedProfesor(profesor1);

		// Creo modulos
		Modulo modulo1 = new Modulo();
		modulo1.setNombre("Matematicas");
		Modulo modulo2 = new Modulo();
		modulo2.setNombre("Ingles");

		// Añado modulos al profesor 1
		profesor1.getModulos().add(modulo1);
		profesor1.getModulos().add(modulo2);
		modulo1.getProfesores().add(profesor1);
		modulo2.getProfesores().add(profesor1);

		// Inserta el profesor en la base de datos (con sus correos y modulos)
		profesorDao.inserta(profesor1, session);
		// Inserta los correos en la base de datos
		correoDao.inserta(correo1, session);
		correoDao.inserta(correo2, session);

		session.getTransaction().commit();
		HibernateUtils.closeSession(sessionf);

	}
}
