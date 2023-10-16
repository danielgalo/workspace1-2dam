package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Esclavo {

	public static void main(String[] args) {

		// Lee lista de numeros

		// Obtener reader
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		// Calcula el mayor
		int mayor = 0;
		boolean primero = true;

		String linea;
		try {
			while (!(linea = reader.readLine()).isEmpty()) {

				int numero = Integer.parseInt(linea);

				if (primero || numero > mayor) {
					mayor = numero;
					primero = false;
				}

			}
		} catch (NumberFormatException | IOException e) {
			System.out.println(Integer.MIN_VALUE);
		}

		// Devuelve el mayor
		System.out.println(mayor);
	}

}
