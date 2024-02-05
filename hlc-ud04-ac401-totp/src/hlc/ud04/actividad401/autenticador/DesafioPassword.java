package hlc.ud04.actividad401.autenticador;

import hlc.ud04.appsec.seguridad.autenticacion.Desafio;

public class DesafioPassword implements Desafio {

	private String usuario;

	public DesafioPassword(String usuario) {
		this.usuario = usuario;
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

}
