package main;

import java.util.List;

import models.Empleado;
import utils.UtilsFicheroArray;

/**
 * Main class
 */
public class mainApp {

	/**
	 * Main method, app stars here
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Create a list and print source from empleados file
		List<Empleado> lista = UtilsFicheroArray.leerEmpleados(args[0]);

		// Store data from the list into a text file
		UtilsFicheroArray.toArchivoTexto(lista);

		// Sore data from the list in reverse order into a binary file
		UtilsFicheroArray.toArchivoBinarioInverso(lista);

		// Create a text file from the data of the list
		UtilsFicheroArray.toArchivoTexto(lista);

		// Print the content from the binary file and print it
		UtilsFicheroArray.leerEmpleadosB("ficheros/inverso.dat");

	}
}
