package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import models.Empleado;

/**
 * Utility class to work with text and binary files
 */
public class UtilsFicheroArray {

	/**
	 * Stores employees from given list into a file
	 * 
	 * @param employeeList
	 * 
	 */
	public static void toArchivoTexto(List<Empleado> employeeList) {

		// Printwriter object
		PrintWriter writer = null;

		try {

			// Initialize printwriter with the file to work with
			writer = new PrintWriter(new FileWriter("ficheros/datos.txt"));

			// Print into the file toString method from all the employees in the list
			for (Empleado e : employeeList) {
				writer.println("EMPLEADO");
				writer.println(e.toString());
				writer.println("-----------");
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			// Close the writer if not null
			if (writer != null) {
				writer.close();
			}
		}

	}

	/**
	 * Stores into a binary file all employees from given list in reverse order
	 * 
	 * @param employeeList
	 */
	public static void toArchivoBinarioInverso(List<Empleado> employeeList) {

		ObjectOutputStream oos = null;

		try {
			// Output object with the file to work with
			oos = new ObjectOutputStream(new FileOutputStream("ficheros/inverso.dat"));

			// Loop through the list in reverse order
			for (int i = employeeList.size() - 1; i >= 0; i--) {

				// Write employee's data
				oos.writeObject(employeeList.get(i).toString());

			}

		} catch (IOException e) {

			e.printStackTrace();
		} finally {

			// Close output stream if not null
			if (oos != null) {

				try {

					oos.close();

				} catch (IOException e) {

					e.printStackTrace();
				}
			}

		}

	}

	/**
	 * Creates a list of employees with the data from a text
	 * 
	 * @param args
	 * @return
	 */
	public static List<Empleado> leerEmpleados(String file) {

		// Buffer to read file
		BufferedReader buffer;

		// Line of the file
		String line;

		// Data of the employee
		String[] employeeData;
		String empEmpresa;
		int empEdad;
		int empNumEmps;

		// Employee list
		List<Empleado> employeeList = new ArrayList<Empleado>();

		try {

			// Inicio el buffer con el fichero a trabajar
			buffer = new BufferedReader(new FileReader(file));

			// Read the file
			while ((line = buffer.readLine()) != null) {

				// Split words by coma
				employeeData = line.split(",");

				// Assing the data to the array
				empEmpresa = employeeData[0];
				empEdad = Integer.valueOf(employeeData[1]);
				empNumEmps = Integer.valueOf(employeeData[2]);

				// Create employee with the data
				Empleado emp = new Empleado(empEmpresa, empEdad, empNumEmps);

				// Add the employee to the list
				employeeList.add(emp);
			}

		} catch (FileNotFoundException e) {

			System.err.println("File not found." + e.getMessage());

		} catch (IOException e) {

			System.err.println("Error accesing the file ." + e.getMessage());
		}

		// Print all employees data
		for (Empleado e : employeeList) {
			System.out.println("EMPLEADO");
			System.out.println(e.toString());
			System.out.println("--------");
		}

		return employeeList;
	}

	/**
	 * Reads employees from a binary file and prints it
	 * 
	 * @param archivo
	 */
	public static void leerEmpleadosB(String pathToBinaryFile) {

		FileInputStream fis = null;

		try {

			// File input we want to read
			fis = new FileInputStream(pathToBinaryFile);

			// byte readen
			int readByte;

			// if there is a byte to read, print it
			while ((readByte = fis.read()) != -1) {

				System.out.print((char) readByte);

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {

				// Close inputstream if not null
				if (fis != null) {
					fis.close();
				}

			} catch (IOException e) {

				e.printStackTrace();
			}

		}

	}

}
