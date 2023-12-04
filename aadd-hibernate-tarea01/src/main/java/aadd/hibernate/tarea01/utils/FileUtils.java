package aadd.hibernate.tarea01.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

	private String archivo;

	/**
	 * @param archivo
	 */
	public FileUtils(String archivo) {
		this.archivo = archivo;
	}

	/**
	 * Devuelve una lista con usuarios del fichero
	 * 
	 * @return
	 */
	public List<String[]> getDatosUsuariosDeFichero() {

		List<String[]> datosUsuarios = new ArrayList<>();

		try {

			BufferedReader reader = new BufferedReader(new FileReader(archivo));

			String linea;

			while ((linea = reader.readLine()) != null) {
				String[] datosUsuario = linea.split(",");

				datosUsuarios.add(datosUsuario);
			}

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return datosUsuarios;
	}

}
