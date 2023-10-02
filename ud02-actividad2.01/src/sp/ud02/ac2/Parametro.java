package sp.ud02.ac2;

public class Parametro {

	private static final String MENSAJE_DEFECTO = "mensaje";
	private static final String PSWD_DEFECTO = "password";

	private String mensaje;
	private String password;

	/**
	 * Constructor
	 * 
	 * @param parametros
	 */
	public Parametro(String[] parametros) {

		if (parametros.length < 1) {
			this.mensaje = MENSAJE_DEFECTO;
		} else {
			mensaje = parametros[0];
		}

		if (parametros.length < 2) {
			this.password = PSWD_DEFECTO;
		} else {
			password = parametros[1];
		}

	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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
