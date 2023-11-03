package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase usada para operar con los ficheros
 */
public class FileUtils {

	/** ruta al fichero */
	private String filePath;

	/**
	 * @param filePath
	 */
	public FileUtils(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Lee un fichero y obtiene una lista con un array de datos
	 * 
	 * @return la lista de datos
	 */
	public List<String[]> getDataList() {

		BufferedReader reader = null;
		List<String[]> listaDatos = null;

		try {

			reader = new BufferedReader(new FileReader(filePath));

			String lineaLeida;

			listaDatos = new ArrayList<>();

			while ((lineaLeida = reader.readLine()) != null) {

				String[] linea = lineaLeida.split(";");

				listaDatos.add(linea);

			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		return listaDatos;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
