package com.act3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GeneradorFichero {

	private static final String NOMBRE_FICHERO_DEFECTO = "default.txt";

	public static void main(String[] args) {

		String nombreFichero;

		if (args.length < 1) {
			nombreFichero = NOMBRE_FICHERO_DEFECTO;
		} else {
			nombreFichero = args[0];
		}

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(nombreFichero));
			writer.write("Holaa");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
