package com.act4;

import java.io.File;
import java.io.IOException;

public class MaestraSumador {

	public static void main(String[] args) {

		ProcessBuilder builder = new ProcessBuilder("java", "-jar", "sumador.jar");

		builder.redirectInput(new File("numeros.txt"));
		builder.redirectOutput(new File("salidasumador.txt"));

		try {
			builder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
