package psp.unidad02.actividad205;

import psp.unidad02.actividad205.loggers.Logger;
import psp.unidad02.actividad205.properties.PropertiesProcessor;
import psp.unidad02.actividad205.threads.Dispatcher;

/**
 * Aplicación principal
 */
public class IndexServer {

	/** Nombre de la clase para usar en logs */
	private static final String CLASS_NAME = IndexServer.class.getName();

	/**
	 * Método principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Logger.initializeBuffer();

		Logger.info("Inicio de la aplicación", CLASS_NAME);

		// Procesar y obtener propiedades
		PropertiesProcessor properties = new PropertiesProcessor("server.properties");
		String outputFile = properties.getOutputFile();
		String inputFolder = properties.getInputFolder();

		Logger.info("Fichero de salida: " + outputFile + " | Carpeta de entrada: " + inputFolder, CLASS_NAME);
		// Iniciar hilo principal
		Dispatcher mainThread = new Dispatcher(inputFolder, outputFile);
		mainThread.start();

		try {
			// Esperar a que el hilo Dispatcher termine
			mainThread.join();
		} catch (InterruptedException e) {
			Logger.problem("Error al esperar a que el hilo principal termine. " + e.getMessage(), CLASS_NAME);
			Thread.currentThread().interrupt();
		} finally {
			Logger.info("Fin de la aplicación", CLASS_NAME);
			// Cerrar el BufferedWriter
			Logger.closeWriter();

		}

	}
}
