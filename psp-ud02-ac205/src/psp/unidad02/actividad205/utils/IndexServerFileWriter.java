package psp.unidad02.actividad205.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import psp.unidad02.actividad205.loggers.Logger;

/**
 * Clase que escribe a un archivo el contenido de un StringBuilder
 */
public class IndexServerFileWriter {

	/** Nombre de la clase para usar en logs */
	private static final String CLASS_NAME = IndexServerFileWriter.class.getName();

	/** Archivo de salida */
	private String outputFile;
	/** StringBuilder para el contenido */
	private StringBuilder builder;

	/**
	 * Constructor
	 * 
	 * @param builder
	 * @param outputFile
	 */
	public IndexServerFileWriter(StringBuilder builder, String outputFile) {
		this.builder = builder;
		this.outputFile = outputFile;
	}

	/**
	 * Escribe el contenido del StringBuilder en el archivo de salida
	 */
	public void writeFile() {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

			if (builder != null) {
				Logger.info("Escribiendo información del StringBuilder...", CLASS_NAME);
				writer.write(builder.toString());
			} else {
				Logger.problem("El StringBuilder entró null.", CLASS_NAME);
			}

		} catch (IOException e) {
			Logger.info("Hubo un problema accediendo o escribiendo en el archivo " + outputFile, CLASS_NAME);
		}

	}

}
