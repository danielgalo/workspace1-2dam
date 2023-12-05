package psp.unidad02.actividad205.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase que escribe a un archivo el contenido de un StringBuilder
 */
public class IndexServerFileWriter {

	private String outputFolder;
	private StringBuilder builder;

	public IndexServerFileWriter(StringBuilder builder, String outputFolder) {
		this.builder = builder;
		this.outputFolder = outputFolder;
	}

	public void writeFile() {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFolder))) {

			writer.write(builder.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
