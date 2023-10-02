package aadd.ud1.ej1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import aadd.ud1.ej1.dao.Empleado;

public class EmpleadosManagement {

	public static void main(String[] args) {

		// Buffer para leer
		BufferedReader buffer = null;

		String line;
		String[] employeeData = null;
		String empEmpresa;
		int empEdad;
		int empNumEmps;
		List<Empleado> employeeList = new ArrayList<>();

		try {

			// Inicio el buffer con el fichero a trabajar
			buffer = new BufferedReader(new FileReader(args[0]));

			while ((line = buffer.readLine()) != null) {

				employeeData = line.split(",");

				empEmpresa = employeeData[0];

				empEdad = Integer.valueOf(employeeData[1]);

				empNumEmps = Integer.valueOf(employeeData[2]);

				Empleado emp = new Empleado(empEmpresa, empEdad, empNumEmps);

				employeeList.add(emp);
			}

		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}

		for (Empleado e : employeeList) {
			System.out.println("--EMPLEADO--");
			System.out.println(e.toString());
			System.out.println("------------");
		}

	}

}
