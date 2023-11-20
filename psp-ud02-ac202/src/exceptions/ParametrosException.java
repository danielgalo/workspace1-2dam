package exceptions;

/**
 * Clase de excepciones ocurridas por mal uso de parámetros
 */
public class ParametrosException extends Exception {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param mensaje
	 */
	public ParametrosException(String mensaje) {
		super(mensaje);
	}
}
