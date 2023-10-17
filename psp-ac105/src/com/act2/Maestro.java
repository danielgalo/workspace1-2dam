package com.act2;

import java.io.IOException;

public class Maestro {

	public static void main(String[] args) {

		ProcessBuilder builder = new ProcessBuilder("java", "-jar", "Generador.jar");
		try {
			builder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
