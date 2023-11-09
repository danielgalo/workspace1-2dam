package main;

import java.util.Scanner;

import utils.NeptunoProcedures;

/**
 * App principal
 *
 */
public class App {

	/**
	 * Método principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		cambiaIva(sc);

		sc.close();
	}

	/**
	 * Cambia el iva en la tabla productos de la base de datos bd_neptuno2 gracias a
	 * un procedure
	 */
	private static void cambiaIva(Scanner sc) {

		double iva = 0;
		boolean isIvaCorrecto = true;

		try {

			// Pide datos hasta que sean correctos
			do {
				System.out.println("Introduce el iva a cambiar: ");
				iva = Double.parseDouble(sc.nextLine());
			} while (!isIvaCorrecto);

			NeptunoProcedures.callIvaNota(iva);

		} catch (NumberFormatException e) {

			isIvaCorrecto = false;
			System.err.println("Introduzca un dato válido.");
		}

	}

}
