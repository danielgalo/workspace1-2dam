package aadd.ud1.ej1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Ficheros {

	public static void main(String[] args) {

		PrintWriter salida = null;
		BufferedReader buffer = null;
		try {
			
//			salida = new PrintWriter(new FileWriter("prueba.txt"));
//			for (int i = 0; i < 100; i++) {
//				salida.println(i);
//			}
			
			buffer = new BufferedReader(new FileReader("prueba.txt"));
			
			String linea;
			
			while ((linea = buffer.readLine()) != null) {
				System.out.println(linea);
			}
		
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} finally {
			
		//	salida.close();
			
		}
		
	}

}
