package hlc.ud04.actividad401.autenticador;

import hlc.ud04.appsec.seguridad.autenticacion.RespuestaDesafio;

public class RespuestaDesafioPassword implements RespuestaDesafio {

	private String password;

	public RespuestaDesafioPassword(String password) {
		this.password = password;
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
