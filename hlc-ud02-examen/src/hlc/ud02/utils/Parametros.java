package hlc.ud02.utils;

/**
 * Clase usada para manejar los parámetros de la clase FirmaApp
 */
public class Parametros {

	/** Nombre o ruta del archivo a procesar */
	private String archivo;

	/** Contraseña para desbloquear el keystore y la clave privada del usuario */
	private String password;

	/**
	 * Constructor
	 */
	public Parametros(String[] params) {

		// Los 2 parámetros son obligatorios
		if (params.length < 2) {
			System.err.println("Error, se deben de pasar 2 parámetros. Parámetros pasados: " + params.length);
		} else {
			archivo = params[0];
			password = params[1];
		}

	}

	/**
	 * @return the archivo
	 */
	public String getArchivo() {
		return archivo;
	}

	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
