package hlc.ud04.actividad401.autenticador;

import hlc.ud04.appsec.seguridad.autenticacion.RespuestaDesafio;

/**
 * Respuesta del usuario al desafío impuesto a el mismo. Contiene la respuesta
 * del usuario, que puede ser correcta o no
 */
public class RespuestaDesafioTOTP implements RespuestaDesafio {

	/** Pin introducido por el usuario */
	private String pin;

	/**
	 * @param secreto
	 */
	public RespuestaDesafioTOTP(String pin) {
		this.pin = pin;

	}

	/**
	 * @return the totpPassword
	 */
	public String getPin() {
		return pin;
	}

	/**
	 * @param totpPassword the totpPassword to set
	 */
	public void setPin(String pin) {
		this.pin = pin;
	}

}
