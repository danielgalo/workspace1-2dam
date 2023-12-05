package psp.unidad02.actividad205.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase que escribe a un archivo el contenido de un StringBuilder
 */
public class IndexServerFileWriter {

	private String outputFile;
	private StringBuilder builder;

	public IndexServerFileWriter(StringBuilder builder, String outputFile) {
		this.builder = builder;
		this.outputFile = outputFile;
	}

	public void writeFile() {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

			writer.write(builder.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
