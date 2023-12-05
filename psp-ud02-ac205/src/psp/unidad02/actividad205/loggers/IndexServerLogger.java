package psp.unidad02.actividad205.loggers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Logger de la aplicación
 */
public class IndexServerLogger {

	/** Nivel de información */
	private static final String INFO_LEVEL = "INFO";
	/** Nivel de problema */
	private static final String PROBLEM_LEVEL = "PROBLEM";
	/** StringBuilder para guardar los mensajes */
	private static final StringBuilder BUILDER = new StringBuilder();

	/**
	 * Constructor privado para evitar instancias
	 */
	private IndexServerLogger() {

	}

	/**
	 * Log de información para el debug de la aplicación
	 * 
	 * @param msg    de información
	 * @param module el módulo que produce el mensaje
	 */
	public static void info(String msg, String module) {

		log(INFO_LEVEL, module, msg);

	}

	/**
	 * Log de problema para cualquier excepción o problema del programa
	 * 
	 * @param msg    mensaje de error
	 * @param module el módulo que produce el error
	 */
	public static void problem(String msg, String module) {

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
	 * @return the builder
	 */
	public static StringBuilder getBuilder() {
		return BUILDER;
	}

}
