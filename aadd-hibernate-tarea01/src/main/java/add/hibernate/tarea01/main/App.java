package add.hibernate.tarea01.main;

import aadd.hibernate.tarea01.persistencia.UsuarioDaoImpl;

/**
 * Aplicación principal
 */
public class App {

	/**
	 * Método principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		UsuarioDaoImpl u = new UsuarioDaoImpl();

		u.inserta();
	}
}
