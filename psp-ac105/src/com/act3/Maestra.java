package com.act3;

import java.io.IOException;

public class Maestra {

	public static void main(String[] args) {

		ProcessBuilder builder = new ProcessBuilder("java", "-cp", "bin", "com.act3.GeneradorFichero");

		try {

			// Añadir argumento al builder para el proceso 1
			builder.command().add("archivo1.txt");
			Process proceso1 = builder.inheritIO().start();

			// Eliminar el ultimo argumento del proceso 1
			builder.command().remove(builder.command().size() - 1);

			builder.command().add("archivo2.txt");
			Process proceso2 = builder.inheritIO().start();

			// Eliminar el ultimo argumento del proceso 1
			builder.command().remove(builder.command().size() - 1);

			// Tercer proceso sin parámetros
			Process proceso3 = builder.inheritIO().start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
