package psp.unidad02.actividad205;

import psp.unidad02.actividad205.loggers.IndexServerLogger;
import psp.unidad02.actividad205.properties.PropertiesProcessor;
import psp.unidad02.actividad205.threads.Dispatcher;

/**
 * Aplicación principal
 */
public class IndexServer {

	/**
	 * Método principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Procesar propiedades
		PropertiesProcessor properties = new PropertiesProcessor("server.properties");

		IndexServerLogger.info(
				"Output file: " + properties.getOutputFile() + " | Input folder: " + properties.getInputFolder(),
				IndexServer.class.toString());

		// Iniciar hilo principal
		Dispatcher d = new Dispatcher(properties.getInputFolder());
		d.start();

	}
}
