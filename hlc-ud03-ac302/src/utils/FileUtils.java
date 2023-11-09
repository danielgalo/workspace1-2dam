package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtils {

	private String filePath;

	public Map<String, String> mapContent() {

		BufferedReader reader = null;
		Map<String, String> contenidoFichero = new HashMap<>();

		try {

			reader = new BufferedReader(new FileReader(filePath));

			String linea;
			List<String[]> listaLineas = new ArrayList<>();

			while ((linea = reader.readLine()) != null) {
				listaLineas.add(linea.split("="));
			}

			for (String[] arr : listaLineas) {
				contenidoFichero.put(arr[0], arr[1]);
			}

		} catch (IOException e) {

			e.printStackTrace();

		}
		return contenidoFichero;
	}

	/**
	 * 
	 * @param filePath
	 */
	public FileUtils(String filePath) {
		setFilePath(filePath);
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
