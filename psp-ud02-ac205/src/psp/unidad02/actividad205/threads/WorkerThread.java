package psp.unidad02.actividad205.threads;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import psp.unidad02.actividad205.indexes.Index;
import psp.unidad02.actividad205.indexes.SharedIndex;

/**
 * Hilo hijo, encargado de procesar un archivo y guardar información de su
 * indexación.
 */
public class WorkerThread extends Thread {

	/** Expresión regular que verifica una palabra */
	private static final String REGEX_WORD = "^[a-zA-ZáéíóúüñÁÉÍÓÚÜÑ]+$";
	/** Lista de indices */
	private static List<Index> indexes = new ArrayList<>();

	/** Ruta del archivo de texto */
	private String filePath;

	/**
	 * Constructor
	 * 
	 * @param filePath
	 */
	public WorkerThread(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void run() {

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

			String line;
			int lineCounter = 0;
			// Leer el archivo
			while ((line = reader.readLine()) != null) {
				// Conteo de líneas
				lineCounter++;
				// Palabras de la línea
				String[] lineWords = line.split("\\s+");

				// Por cada palabra
				for (int i = 0; i < lineWords.length; i++) {

					// Si es una palabra correcta
					if (Pattern.matches(REGEX_WORD, lineWords[i])) {

						String word = lineWords[i].toLowerCase();

						Index idx = new Index(word, (i + 1), lineCounter, filePath);
						indexes.add(idx);

						SharedIndex.addIndex(idx.getWord(), idx);

					}

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
