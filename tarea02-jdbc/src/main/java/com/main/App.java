package com.main;

import com.utils.CreateTable;

/**
 * Aplicacion principal
 *
 */
public class App {

	/**
	 * Método principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Creando tabla Provincia...");
		CreateTable.createProvincia();

		System.out.println("Creando tabla Localidad...");
		CreateTable.createLocalidad();

		System.out.println("Creando tabla Empleado...");
		CreateTable.createEmpleado();

	}
}
