package main;

import utils.DBManagement;

/**
 * Clase principal
 *
 */
public class App {

	/**
	 * Método principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Crear tabla usuarios
		DBManagement.crearTablaSuperUsuarios("Alumnado_nuevo.txt");
		DBManagement.almacenarProductosEnFichero(2, "fichero.txt");
		DBManagement.ficheroATablas("fichero.txt");
	}
}
