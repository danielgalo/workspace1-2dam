package utils;

import java.util.regex.Pattern;

public class Validation {

	public static boolean checkNombreApellidos(String nombreCompleto) {

		return validate("^[a-zA-Z\\s]+", nombreCompleto);
	}

	/**
	 * Valida la fecha de nacimiento
	 * 
	 * @param fecha
	 * @return
	 */
	public static boolean checkFecha(String fecha) {

		return validate("^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$", fecha);
	}

	private static boolean validate(String regex, String strValidate) {
		return Pattern.matches(regex, strValidate);
	}
}
