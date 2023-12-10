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

		Logger.info("Inicio de la aplicación", CLASS_NAME);

		// Procesar y obtener propiedades
		PropertiesProcessor properties = new PropertiesProcessor("server.properties");
		String outputFile = properties.getOutputFile();
		String inputFolder = properties.getInputFolder();

		Logger.info("Fichero de salida: " + outputFile + " | Carpeta de entrada: " + inputFolder, CLASS_NAME);

		// Iniciar hilo principal
		Dispatcher mainThread = new Dispatcher(inputFolder, outputFile);
		mainThread.start();

	}
}
