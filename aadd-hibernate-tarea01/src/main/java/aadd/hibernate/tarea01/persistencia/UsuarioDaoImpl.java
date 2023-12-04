package aadd.hibernate.tarea01.persistencia;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import aadd.hibernate.tarea01.entidades.Usuario;
import aadd.hibernate.tarea01.utils.FileUtils;
import aadd.hibernate.tarea01.utils.HibernateUtils;

/**
 * DAO de usuario con métodos para acceder a la base de datos
 */
public class UsuarioDaoImpl implements CommonDaoI {

	@Override
	public void inserta() {

		// Obtiene sesion
		SessionFactory sessionf = HibernateUtils.getSessionFactory();
		// Abre sesion y abro una transacción
		Session session = sessionf.openSession();
		session.getTransaction().begin();

		// Obtener datos del usuario
		FileUtils fu = new FileUtils("Alumnado_nuevo.txt");
		List<String[]> datosUsuarios = fu.getDatosUsuariosDeFichero();

		int contador = 0;

		// Guardar los usuarios
		for (String[] datos : datosUsuarios) {
			// Crear usuario
			Usuario usuario = new Usuario();
			usuario.setNombre(datos[1]);
			usuario.setApellidos(datos[0]);

			// Guarda usuario
			session.save(usuario);

			// Commit en batch de 10
			if (contador % 10 == 0) {
				session.getTransaction().commit();
				// Volver a empezar una transaccion
				session.getTransaction().begin();
			}
			contador++;

		}

		// Commit a usuarios restantes
		session.getTransaction().commit();
		// Cierra sesion
		session.close();
	}

}
