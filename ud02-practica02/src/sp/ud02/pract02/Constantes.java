package sp.ud02.pract02;

/**
 * Clase de constantes
 */
public abstract class Constantes {

	/**
	 * Constructor privado para evitar instancias
	 */
	private Constantes() {
	}

	public static byte[] SAL = { 21, 19, 8, 34, 40 };
	public static final int ITERACIONES = 10;
	public static final int KEY_SIZE = 128;
	public static final String AES = "AES";
	public static final String KEY_ALGORITHM = "PBKDF2WithHmacSHA1";

}
