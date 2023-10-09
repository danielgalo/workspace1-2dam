package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import models.Empleado;
import utils.MyObjectOutPutStream;

public class MainApp {

	public static void main(String[] args) {
		Empleado empleado1 = new Empleado("Fernando", "Ureña", 23, 800);
		Empleado empleado2 = new Empleado("Antonio", "Lopez", 35, 1000);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\empleados.ddr"))) {
			// Escribimos en un fichero
			oos.writeObject(empleado1);
			oos.writeObject(empleado2);
		} catch (IOException e) {
		}
	}

	/**
	 * Guarda empleados de una lista en un fichero binario. Si no está creado, lo
	 * creará y guardará los empleados. Si ya está creado, añadirá los nuevos
	 * empleados a los que ya hubiera. Los empleados se guardarán siempre de uno en
	 * uno.
	 * 
	 * @param listaEmpleados
	 */
	public void guardarEmpleados(List<Empleado> listaEmpleados) {

		String rutaArchivo = "empleados.dat";

		File archivo = new File(rutaArchivo);

		FileOutputStream fis = null;

		// Si el archivo existe, escribimos con la clase MyObjectOutPutStream
		if (archivo.exists()) {

			MyObjectOutPutStream miStream = null;

			try {

				miStream = new MyObjectOutPutStream();

			} catch (SecurityException | IOException e) {

				e.printStackTrace();

			} finally {

				try {
					miStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

			// Si no existe usamos ObjectOutputStream
		} else {

			ObjectOutputStream oos = null;

			try {

				fis = new FileOutputStream(rutaArchivo);
				oos = new ObjectOutputStream(fis);

				// Escribir los empleados
				for (Empleado e : listaEmpleados) {
					oos.writeObject(e);
				}

			} catch (IOException e) {

				e.printStackTrace();

			} finally {

				try {

					oos.close();
					fis.close();

				} catch (IOException e) {

					e.printStackTrace();

				}
			}

		}

	}

}
