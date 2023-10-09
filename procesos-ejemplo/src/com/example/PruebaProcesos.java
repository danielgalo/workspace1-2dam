package com.example;

import java.io.IOException;

// proceso padre
public class PruebaProcesos {

	public static void main(String args[]) throws IOException {
		// Ejemplos de creación distintos processbuilder

		// Padre de proceso e hijo de la clase
		ProcessBuilder builder1 = new ProcessBuilder("comando/programa");
		ProcessBuilder builder2 = new ProcessBuilder("comando/programa parámetros");
		ProcessBuilder builder3 = new ProcessBuilder("comando/programa", "parámetro", "parámetro");

		ProcessBuilder builder = new ProcessBuilder("xcopy", "origen.txt", "destinoooo.txt");

		// hijo del builder1
		Process proceso = builder.start();

		System.out.println(builder.command());

	}

}
