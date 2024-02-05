package hlc.ud04.actividad401.autenticador;

import hlc.ud04.appsec.seguridad.autenticacion.Desafio;

public class DesafioTOTP implements Desafio {

	private String usuario;
	private String secreto;

	/**
	 * @param usuario
	 * @param pinOTP
	 */
	public DesafioTOTP(String usuario, String secreto) {
		this.usuario = usuario;
		this.secreto = secreto;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the secreto
	 */
	public String getSecreto() {
		return secreto;
	}

	/**
	 * @param secreto the secreto to set
	 */
	public void setSecreto(String secreto) {
		this.secreto = secreto;
	}

}
