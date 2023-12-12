package psp.unidad02.actividad205.indexes;

import java.util.Map;
import java.util.TreeMap;

import psp.unidad02.actividad205.loggers.Logger;

/**
 * Clase estática usada para guardar y obtener el índice compartido entre hilos.
 */
public class SharedIndex {

	/** Mapa en el cual se guarda el índice */
	private static Map<String, StringBuilder> indexes = new TreeMap<>();
	/** Nombre de la clase para logs */
	private static final String CLASS_NAME = SharedIndex.class.getName();

	/**
	 * Constuctor privado para evitar instancias
	 */
	private SharedIndex() {

	}

	/**
	 * Añade al mapa compartido entre hilos, la palabra como clave y como valor un
	 * StringBuilder. El método es sincronizado, ya que a este intentarán acceder
	 * varios hilos al mismo tiempo
	 * <p>
	 * El contenido del StringBuilder coincide con una Tripleta en formato: <br>
	 * (fichero,linea,posicion)
	 * <p>
	 * 
	 * Si la clave ya existe, añade al StringBuilder la tripleta, si no, lo crea.
	 * 
	 * @param word palabra del índice
	 * @param idx  Indice que contiene la información de la tripleta
	 */
	public static synchronized void addIndex(String word, Index idx) {

		if (!word.isEmpty()) {

			Logger.info("Añadiendo índice a la palabra: \"" + word + "\"", CLASS_NAME);
			// Añade un StringBuilder a la clave, si ya existe la clave añade una tripleta
			indexes.computeIfAbsent(word, k -> new StringBuilder()).append("  ").append(idx.getMsgTupla()).append("\n");
		} else {
			Logger.problem("Palabra entró vacía", CLASS_NAME);
		}
	}

	/**
	 * @return the indexes
	 */
	public static Map<String, StringBuilder> getIndexes() {
		return indexes;
	}

}
