package psp.unidad02.actividad205.indexes;

import java.util.Map;
import java.util.TreeMap;

/**
 * Clase estática usada para guardar y obtener índices.
 */
public class SharedIndex {

	/** Mapa en el cual se guarda el índice */
	private static Map<String, StringBuilder> indexes = new TreeMap<>();

	/**
	 * Constuctor privado para evitar instancisas
	 */
	private SharedIndex() {

	}

	/**
	 * Añade al mapa compartido entre hilos, la palabra como clave y como valor un
	 * StringBuilder. El método es sincronizado, ya que a este intentarán acceder
	 * varios hilos al mismo tiempo
	 * <p>
	 * El contenido del StringBuilder coincide con una Tripleta en
	 * formato["(fichero,linea,posicion)"].
	 * <p>
	 * 
	 * Si la clave ya existe, añade al StringBuilder la tripleta, si no, lo crea.
	 * 
	 * @param palabra palabra del índice
	 * @param idx     Indice que contiene la información de la tripleta
	 */
	public static synchronized void addIndex(String palabra, Index idx) {
		if (!palabra.isEmpty()) {
			// Agregar la ocurrencia de la palabra junto con la información de la línea
			indexes.computeIfAbsent(palabra, k -> new StringBuilder()).append("    ").append("(")
					.append(idx.getFileName()).append(",").append(idx.getLine()).append(",")
					.append(idx.getWordPosition()).append(") ").append("\n");
		}
	}

	/**
	 * @return the indexes
	 */
	public static Map<String, StringBuilder> getIndexes() {
		return indexes;
	}

}
