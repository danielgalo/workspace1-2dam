package main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import models.Empleado;
import utils.MyObjectOutPutStream;

public class MainApp {

	public static void main(String[] args) {

		Empleado empleado1 = new Empleado("Fernando", "Ureña", 23, 800);
		Empleado empleado2 = new Empleado("Antonio", "Lopez", 35, 1000);
		Empleado empleado3 = new Empleado("Paco", "pere", 22, 800);
		Empleado empleado4 = new Empleado("marco", "baena", 32, 100);

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

		guardarEmpleados(listaEmpleados);

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

		// Si el archivo existe, escribimos con la clase MyObjectOutPutStream
		if (archivo.exists()) {
			// TODO falla al imprimir cuando entra por aqui
			try (MyObjectOutPutStream oos = new MyObjectOutPutStream(new FileOutputStream("empleados.dat"))) {

				// Escribimos en un fichero
				for (Empleado e : listaEmpleados) {
					oos.writeObject(e);
				}

				oos.close();
			} catch (IOException e) {

				e.printStackTrace();

			}

			// Si no existe usamos ObjectOutputStream
		} else {

			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("empleados.dat"))) {

				// Escribimos en un fichero
				for (Empleado e : listaEmpleados) {
					oos.writeObject(e);
				}
				oos.close();
			} catch (IOException e) {

				e.printStackTrace();

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

			try (FileInputStream fis = new FileInputStream(archivoBinario);
					ObjectInputStream ois = new ObjectInputStream(fis)) {

				while (true) {
					try {
						// Leer objetos del archivo binario
						Object objetoLeido = ois.readObject();

						if (objetoLeido instanceof Empleado) {
							Empleado empleado = (Empleado) objetoLeido;
							System.out.println("Nombre: " + empleado.getNombre());
							System.out.println("Edad: " + empleado.getEdad());
							System.out.println("Salario: " + empleado.getSalario());
							System.out.println("-------------------------");
						}
					} catch (EOFException e) {
						// Lee hasta que da error
						break;
					}
				}

				fis.close();
				ois.close();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}

			// Salida por fichero
		} else {

			File salida = new File("empleados.txt");

			if (salida.exists()) {

				// Si el archivo no existe
			} else {

				try (FileInputStream fis = new FileInputStream(archivoBinario);
						ObjectInputStream ois = new ObjectInputStream(fis);
						FileWriter writer = new FileWriter("empleados.txt")) {

					while (true) {
						try {
							// Leer un objeto de Empleado desde el archivo binario
							Empleado empleado = (Empleado) ois.readObject();

							// Escribir la información del empleado en el archivo de texto
							writer.write("Nombre: " + empleado.getNombre() + "\n");
							writer.write("Edad: " + empleado.getEdad() + "\n");
							writer.write("Salario: " + empleado.getSalario() + "\n\n");
						} catch (EOFException e) {
							// Lee hasta que da error
							break;
						}
					}

					System.out.println("Contenido del archivo binario leído y guardado en el archivo de texto.");
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}

			}

		}

	}

}
