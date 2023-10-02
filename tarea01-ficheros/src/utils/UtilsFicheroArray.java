package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import models.Empleado;

public class UtilsFicheroArray {

	/**
	 * Stores employees from given list into a file
	 * 
	 * @param employeeList
	 * 
	 */
	public static void toArchivoTexto(List<Empleado> employeeList) {

		PrintWriter writer = null;

		try {

			writer = new PrintWriter(new FileWriter("ficheros/datos.txt"));

			for (Empleado e : employeeList) {
				writer.println("EMPLEADO");
				writer.println(e.toString());
				writer.println("-----------");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}

	}

	/**
	 * Stores into a binary file all employees from given list
	 * 
	 * @param employeeList
	 */
	public static void toArchivoBinarioInverso(List<Empleado> employeeList) {
		FileOutputStream fos = null;

		try {

			fos = new FileOutputStream("ficheros/inverso.txt");

			for (int i = employeeList.size() - 1; i >= 0; i--) {
				fos.write(employeeList.get(i).toString().getBytes());
			}

		} catch (IOException e) {

			e.printStackTrace();
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

			System.err.println("No se encontró el archivo." + e.getMessage());

		} catch (IOException e) {

			System.err.println("Error accediendo al archivo." + e.getMessage());
		}

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
	public static void leerEmpleadosB(String archivo) {

		FileInputStream fis = null;

		try {
			fis = new FileInputStream(archivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
