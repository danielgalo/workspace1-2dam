package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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

		imprimirFicheroEmpleados(true);

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

	public static void imprimirFicheroEmpleados(boolean printPantalla) {

		String archivo = "empleados.dat";

		// Si se elige imprimir por pantalla
		if (printPantalla) {

			try {

				FileInputStream fis = new FileInputStream(archivo);
				ObjectInputStream ois = new ObjectInputStream(fis);

				Empleado emp;

				while (ois.available() > 0) {

					emp = (Empleado) ois.readObject();
					System.out.println(emp);
				}

				fis.close();
			} catch (FileNotFoundException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Si se elige salida a fichero de texto
		} else {

			PrintWriter writer = null;

			try {

				writer = new PrintWriter(new FileWriter("empleados.txt"));

				FileInputStream fis = new FileInputStream(archivo);
				ObjectInputStream ois = new ObjectInputStream(fis);

				Empleado emp;

				while (ois.available() > 0) {

					emp = (Empleado) ois.readObject();
					writer.print(emp);
				}

				fis.close();
				writer.close();
			} catch (IOException e) {

				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
