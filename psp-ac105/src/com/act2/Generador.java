package com.act2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Generador {

	public static void main(String args[]) {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("fichero.txt"));
			writer.write("Hola");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
