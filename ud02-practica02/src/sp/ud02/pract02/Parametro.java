package sp.ud02.pract02;

public class Parametro {

	/** Ruta del fichero a cifrar */
	private String rutaFicheroEntrada;

	/** Ruta del fichero de salida */
	private String rutaFicheroSalida;

	/** Contraseña para cifrar */
	private String password;

	/**
	 * Constructor
	 * 
	 * @param parametros
	 */
	public Parametro(String[] parametros) {

		if (parametros.length < 3) {
			System.err.println("Error, se deben pasar 3 argumentos (pasados: " + parametros.length + ").");
		} else {
			setRutaFicheroEntrada(parametros[0]);
			setRutaFicheroSalida(parametros[1]);
			setPassword(parametros[2]);
		}

	}

	/**
	 * @return the rutaFicheroEntrada
	 */
	public String getRutaFicheroEntrada() {
		return rutaFicheroEntrada;
	}

	/**
	 * @param rutaFicheroEntrada the rutaFicheroEntrada to set
	 */
	public void setRutaFicheroEntrada(String rutaFicheroEntrada) {
		this.rutaFicheroEntrada = rutaFicheroEntrada;
	}

	/**
	 * @return the rutaFicheroSalida
	 */
	public String getRutaFicheroSalida() {
		return rutaFicheroSalida;
	}

	/**
	 * @param rutaFicheroSalida the rutaFicheroSalida to set
	 */
	public void setRutaFicheroSalida(String rutaFicheroSalida) {
		this.rutaFicheroSalida = rutaFicheroSalida;
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
