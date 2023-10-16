package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Maestro {

	private static final int PROCESOS = 4;
	private static final String CLASE_ESCLAVO = "com.example.Esclavo";
	private static final int[] DATOS_PRUEBA = { 1, 42, 5, 44, 7, 8, 0, 6, 3, 32, 91, 4, 15, 6, 78, 7 };

	public static void main(String[] args) throws Exception {

		// obtener el número de procesadores
		int procesadores = Runtime.getRuntime().availableProcessors();

		// Creamos factoria procesos
		ProcessBuilder builder = new ProcessBuilder("java", CLASE_ESCLAVO);

		// Crear procesos
		List<Process> procesos = new ArrayList<Process>();
		for (int i = 0; i < PROCESOS; i++) {
			procesos.add(builder.start());
		}

		int indice = 0;
		// Para cada proceso
		for (Process p : procesos) {
			BufferedWriter escritor = p.outputWriter();
			PrintWriter printer = new PrintWriter(escritor);
			// Le pasamos los datos
			for (int i = indice * (DATOS_PRUEBA.length / PROCESOS); i < ((indice + 1) * (DATOS_PRUEBA.length / PROCESOS)
					- 1); i++) {
				printer.println(DATOS_PRUEBA[i]);
			}
			printer.println();
			printer.flush();
			indice++;
		}

		int mayor = 0;

		// Leo resultados desde los esclavos
		for (int i = 0; i < procesos.size(); i++) {
			BufferedReader reader = procesos.get(i).inputReader();
			String linea = reader.readLine();
			int resultado = Integer.parseInt(linea);

			if ((i == 0) || (resultado > mayor)) {
				mayor = resultado;
			}
		}

		// Imprimimos el resultado
		System.out.println("El mayor es " + mayor);

	}

}
