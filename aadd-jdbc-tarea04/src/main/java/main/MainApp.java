package main;

import java.util.List;

import utils.DBManagement;
import utils.FileUtils;

/**
 * Clase principal
 *
 */
public class MainApp {

	/** Ruta al fichero de datos */
	private static final String RUTA_FICHERO_DATOS = "files/datos.txt";

	/**
	 * Método principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Conseguir datos del fichero
		FileUtils fu = new FileUtils(RUTA_FICHERO_DATOS);
		List<String[]> listaLineas = fu.getDataList();

		// Imprimir datos
		imprimeLista(listaLineas);

		// Insertar datos
		insertaDatos(listaLineas);

	}

	/**
	 * Inserta los datos de la lista en una base de datos
	 * 
	 * @param listaLineas
	 */
	private static void insertaDatos(List<String[]> listaLineas) {

		// Por cada linea del archivo
		for (String[] arr : listaLineas) {

			// Si es profesor
			if (arr[0].equalsIgnoreCase("Profesor")) {
				DBManagement.insertaProfesor(arr);

				// Si es asignatura
			} else if (arr[0].equalsIgnoreCase("Asignatura")) {
				DBManagement.insertaAsignatura(arr);

				// Si es alumno
			} else if (arr[0].equalsIgnoreCase("Alumno")) {
				DBManagement.insertaAlumno(arr);
			}

		}
	}

	/**
	 * @param listaLineas
	 */
	private static void imprimeLista(List<String[]> listaLineas) {

		for (String[] arr : listaLineas) {

			for (String str : arr) {
				System.out.println(str);
			}

			System.out.println("------");
		}
	}

}
