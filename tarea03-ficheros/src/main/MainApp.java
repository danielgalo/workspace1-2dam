package main;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import models.Empleado;
import utils.MyObjectOutPutStream;

public class MainApp {

	public static void main(String[] args) {

		Empleado empleado1 = new Empleado("Fernando", "Ureña", 23, 800);
		Empleado empleado2 = new Empleado("Antonio", "Lopez", 35, 1000);
		Empleado empleado3 = new Empleado("PACOOOOO", "PEÑAAAAA", 23, 800);
		Empleado empleado4 = new Empleado("PEPEEEEE", "PEREEEEEEZ", 35, 1000);

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ejemplo1.dat"))) {
			// Escribimos en un fichero
			oos.writeObject(empleado1);
			oos.writeObject(empleado2);

		} catch (IOException e) {

			e.printStackTrace();

		}

		List<Empleado> listaEmpleados = new ArrayList<Empleado>();

		listaEmpleados.add(empleado2);
		listaEmpleados.add(empleado1);
		listaEmpleados.add(empleado3);
		listaEmpleados.add(empleado4);

		// guardarEmpleados(listaEmpleados);

		imprimirFicheroEmpleados(false);

	}

	/**
	 * Guarda empleados de una lista en un fichero binario. Si no está creado, lo
	 * creará y guardará los empleados. Si ya está creado, añadirá los nuevos
	 * empleados a los que ya hubiera. Los empleados se guardarán siempre de uno en
	 * uno.
	 * 
	 * @param listaEmpleados
	 */
	public static void guardarEmpleados(List<Empleado> listaEmpleados) {

		String rutaArchivo = "empleados.dat";

		// Creo objeto archivo para comprobar si existe
		File archivo = new File(rutaArchivo);

		// Stream de salida
		FileOutputStream fis = null;

		// Si el archivo existe, escribimos con la clase MyObjectOutPutStream
		if (archivo.exists()) {

			MyObjectOutPutStream miStream = null;

			try {

				fis = new FileOutputStream(rutaArchivo);
				miStream = new MyObjectOutPutStream(fis);

				for (Empleado e : listaEmpleados) {
					miStream.writeObject(e);
				}

			} catch (SecurityException | IOException e) {

				e.printStackTrace();

			} finally {

				closeStream(fis, miStream);

			}

			// Si no existe usamos ObjectOutputStream
		} else {

			ObjectOutputStream oos = null;

			try {

				fis = new FileOutputStream(rutaArchivo);
				oos = new ObjectOutputStream(fis);

				for (Empleado e : listaEmpleados) {
					oos.writeObject(e);
				}

			} catch (IOException e) {

				e.printStackTrace();

			} finally {
				closeStream(fis, oos);
			}

		}

	}

	/**
	 * Imprime los empleados por pantalla o en un fichero de texto de nombre
	 * "empleados.txt", dependiendo del parámetro pasado. En el caso de imprimir en
	 * el fichero, si no está creado, lo creará y guardará los empleados. Si ya está
	 * creado, añadirá los nuevos empleados a los que ya hubiera. Los empleados se
	 * guardarán siempre de uno en uno.
	 * 
	 * @param printPantalla
	 */
	public static void imprimirFicheroEmpleados(boolean printPantalla) {

		String archivoBinario = "empleados.dat";

		// Si se imprime por pantalla
		if (printPantalla) {

			// Salida por fichero
		} else {

			File salida = new File("empleados.txt");

			if (salida.exists()) {

				// Si el archivo no existe
			} else {

				try {

					// Abrir archivo binario para leer
					FileInputStream archivoEntrada = new FileInputStream(archivoBinario);
					BufferedInputStream bufferEntrada = new BufferedInputStream(archivoEntrada);

					// Abir archivo de texto para escribir
					FileWriter writer = new FileWriter(salida);
					BufferedWriter bufferSalida = new BufferedWriter(writer);

					int byteLeido;

					while ((byteLeido = bufferEntrada.read()) != -1) {
						char caracter = (char) byteLeido;
						bufferSalida.write(caracter);
					}

					bufferEntrada.close();
					bufferSalida.close();
				} catch (IOException e) {

					e.printStackTrace();

				}

			}

		}

	}

	/**
	 * Cierra el número de objetos pasados que hereden de la classe OutputStream
	 * 
	 * @param stream(s)
	 */
	private static void closeStream(OutputStream... stream) {

		for (OutputStream os : stream) {

			try {

				if (os != null) {
					os.close();
				}

			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

}
