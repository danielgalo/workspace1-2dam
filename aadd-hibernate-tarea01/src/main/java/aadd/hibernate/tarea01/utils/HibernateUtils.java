package aadd.hibernate.tarea01.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Clase de utilidades de hibernate
 */
public class HibernateUtils {

	/**
	 * @return
	 */
	public static SessionFactory getSessionFactory() {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory session = new MetadataSources(sr).buildMetadata().buildSessionFactory();
		return session;
	}

	/**
	 * Cierra la sesión de hibernate
	 * 
	 * @param session sesion a cerrar
	 */
	public static void closeSession(SessionFactory session) {
		session.close();
	}

}
