package psp.unidad02.actividad205;

import psp.unidad02.actividad205.properties.PropertiesProcessor;
import psp.unidad02.actividad205.threads.Dispatcher;

/**
 * 
 */
public class IndexServer {

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		PropertiesProcessor properties = new PropertiesProcessor("server.properties");

		System.out.println("Output: " + properties.getOutputFile() + "\nInput: " + properties.getInputFolder());

		Dispatcher d = new Dispatcher(properties.getInputFolder());
		d.start();

	}
}
