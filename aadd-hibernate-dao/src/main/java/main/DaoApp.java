package main;

import java.util.Scanner;

import org.hibernate.Session;

import persistence.dao.UserDaoImpl;
import persistence.entities.User;
import utils.HibernateUtil;

/**
 * Clase Principal para probar DAO
 *
 */
public class DaoApp {

	/**
	 * Método principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Session session = HibernateUtil.getSession();

		try {
			UserDaoImpl userDao = new UserDaoImpl(session);

			Scanner sc = new Scanner(System.in);

			int option = 0;

			do {
				System.out.println("1- Log");
				System.out.println("2- Registro");
				option = Integer.parseInt(sc.nextLine());
				if (option == 1) {

					System.out.println("Email: ");
					String email = sc.nextLine();

					System.out.println("Password: ");
					String password = sc.nextLine();

					User usuarioEncontrado = userDao.getUserByEmail(email);

					if (usuarioEncontrado == null) {
						System.out.println("No se han encontraod usuarios");
					} else {

						if (usuarioEncontrado.getPassword().equals(password)) {
							System.out.println("Logeado con exito");
						} else {
							System.out.println("No se han encontrado usuarios");
						}

					}

				} else if (option == 2) {

					System.out.println("Introduce email:");
					String email = sc.nextLine();

					System.out.println("Introduce contraseña:");
					String password = sc.nextLine();

					User newUser = new User(email, password);

					userDao.insert(newUser);

				}
			} while (option != 0);
			HibernateUtil.closeSession();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
