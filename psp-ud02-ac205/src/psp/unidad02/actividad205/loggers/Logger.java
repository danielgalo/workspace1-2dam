package psp.unidad02.actividad205.loggers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Logger de la aplicación
 */
public class Logger {

	/** Nivel de información */
	private static final String INFO_LEVEL = "INFO";
	/** Nivel de problema */
	private static final String PROBLEM_LEVEL = "PROBLEM";
	/** StringBuilder para guardar los mensajes */
	private static final StringBuilder BUILDER = new StringBuilder();
	/** Buffer para escribir */
	private static BufferedWriter writer;
	/** Archivo de logs */
	private static final String LOGGER_FILE = "indexer.log";

	/**
	 * Constructor privado para evitar instancias
	 */
	private Logger() {

	}

	/**
	 * Inicia el BufferedReader con el archivo de logs. Si este ya existe, lo inicia
	 * en modo append (no sobreescribe el contenido existente). Si no exisite lo
	 * crea.
	 */
	public static void initializeBuffer() {
		try {
			File logFile = new File(LOGGER_FILE);

			// Verificar si el archivo ya existe
			if (!logFile.exists()) {
				// Si no existe, crearlo
				writer = new BufferedWriter(new FileWriter(logFile));
			} else {
				// Si ya existe, abrirlo sin eliminar su contenido
				writer = new BufferedWriter(new FileWriter(logFile, true));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Log de información para el debug de la aplicación
	 * 
	 * @param msg    de información
	 * @param module el módulo que produce el mensaje
	 */
	public static synchronized void info(String msg, String module) {

		log(INFO_LEVEL, module, msg);

	}

	/**
	 * Log de problema para cualquier excepción o problema del programa
	 * 
	 * @param msg    mensaje de error
	 * @param module el módulo que produce el error
	 */
	public static synchronized void problem(String msg, String module) {

		log(PROBLEM_LEVEL, module, msg);

	}

	/**
	 * Loggea un mensaje, guardándolo en el StringBuilder
	 * 
	 * @param level  el nivel del mensaje [INFO | PROBLEM]
	 * @param module la clase que produce el mensaje
	 * @param msg    el mensaje
	 */
	private static void log(String level, String module, String msg) {

		// Timestamp
		BUILDER.append("[");
		BUILDER.append(getTimeStamp());
		BUILDER.append("]");

		// Nivel
		BUILDER.append("(");
		BUILDER.append(level);
		BUILDER.append(")");

		// Modulo
		BUILDER.append("<");
		BUILDER.append(module);
		BUILDER.append(">");
		BUILDER.append(": ");

		// Mensaje
		BUILDER.append(msg);

		// Salto de línea
		BUILDER.append("\n");

		// Escribir log
		try {
			writer.write(BUILDER.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Limpiar el StringBuilder
		BUILDER.setLength(0);

	}

	/**
	 * Devuelve una cadena con el timestamp actual del sistema en formato
	 * [yyyy-MM-dd HH:mm:ss]
	 * 
	 * @return el timestamp
	 */
	private static String getTimeStamp() {
		// Obtener un timestamp actual
		Date date = new Date();
		long tiempoEnMilisegundos = date.getTime();

		// Crear un objeto SimpleDateFormat con el formato deseado
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// Formatear el timestamp
		return dateFormat.format(new Date(tiempoEnMilisegundos));
	}

	/**
	 * Cierra el Buffer del Logger
	 * 
	 * @param writer
	 */
	public static void closeWriter() {
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @return the builder
	 */
	public static StringBuilder getBuilder() {
		return BUILDER;
	}

}
