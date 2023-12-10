package psp.unidad02.actividad205.threads;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

import psp.unidad02.actividad205.indexes.Index;
import psp.unidad02.actividad205.indexes.SharedIndex;
import psp.unidad02.actividad205.loggers.Logger;

/**
 * Hilo hijo, encargado de procesar un archivo y guardar información de su
 * indexación.
 */
public class WorkerThread extends Thread {

	/** Nombre de la clase para usar en logs */
	private static final String CLASS_NAME = WorkerThread.class.getName();
	/** Expresión regular que verifica una palabra */
	private static final String REGEX_WORD = "^[a-zA-ZáéíóúüñÁÉÍÓÚÜÑ]+$";

	/** Ruta del archivo de texto */
	private String filePath;
	/** Id del worker */
	private int workerId;

	/**
	 * Constructor
	 * 
	 * @param filePath
	 */
	public WorkerThread(String filePath, int workerId) {
		this.filePath = filePath;
		this.workerId = workerId;
	}

	@Override
	public void run() {

		Logger.info("Hilo worker empezando. ID: " + workerId, CLASS_NAME);

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
						SharedIndex.addIndex(idx.getWord(), idx);

					}

				}
			}

			Logger.info("Hilo worker terminó. ID: " + workerId, CLASS_NAME);

		} catch (IOException e) {
			Logger.problem("Hilo worker tuvo un problema accediendo al archivo. ID: " + workerId + ". Error: "
					+ e.getMessage(), CLASS_NAME);
		}
	}

	/**
	 * @return the workerId
	 */
	public int getWorkerId() {
		return workerId;
	}

}
