package aadd.hibernate.tarea02.main;

import aadd.hibernate.tarea02.entidades.Correo;
import aadd.hibernate.tarea02.entidades.Profesor;
import aadd.hibernate.tarea02.persistencias.dao.CorreoDaoImpl;
import aadd.hibernate.tarea02.persistencias.dao.ProfesorDaoImpl;

/**
 * Hello world!
 *
 */
public class App {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		ProfesorDaoImpl profesorDao = new ProfesorDaoImpl();
		CorreoDaoImpl correoDao = new CorreoDaoImpl();

		// Crea un profesor
		Profesor profesor1 = new Profesor();
		profesor1.setNombre("Paco");
		profesor1.setApellidos("Peña");

		// Inserta el profesor en la base de datos
		profesorDao.inserta(profesor1);

		// Crea correos asociados al profesor1
		Correo correo1 = new Correo();
		correo1.setDireccion("paco1@email.com");
		correo1.setRelatedProfesor(profesor1);

		Correo correo2 = new Correo();
		correo2.setDireccion("paco2@email.com");
		correo2.setRelatedProfesor(profesor1);

		// Inserta los correos en la base de datos
		correoDao.inserta(correo1);
		correoDao.inserta(correo2);
	}
}
