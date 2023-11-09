package main;

import dao.Productos;
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
		// DBManagement.crearTablaSuperUsuarios("Alumnado_nuevo.txt");
		DBManagement.almacenarProductosEnFichero(2, "fichero.txt");

		for (Productos p : DBManagement.getListaProductos()) {
			System.out.println(p.getDatosProducto());
		}
	}
}
