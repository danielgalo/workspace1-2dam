package utils;

import java.util.regex.Pattern;

public class Validation {

	private static final String[] titles = { "Doctor", "Señor", "Señora" };
	private static final String REGEX_NAME = "^[\\p{L}ÇçÑñ\\s']{1,50}$";
	private static final String REGEX_SURNAME = "^[\\p{L}ÇçÑñ\\s']{1,100}$";
	private static final String REGEX_PHONE_NUMBER = "^[6-9]\\d{8}$";
	private static final String REGEX_CP = "^(0[1-9]|[1-4][0-9]|5[0-2])\\d{3}$";
	private static final String REGEX_EMAIL = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	private static final String REGEX_URL = "^(http://|https://)(www\\.)?[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,})?(:\\d+)?(/\\S*)?$";
	private static final String REGEX_USERNAME = "[a-zA-Z_\\-\\d]{1,10}";
	private static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[.;,:/*%$()])[a-zA-Z\\d.;,:/*%$()]{8,16}$";
	private static final String REGEX_FECHA = "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";

	/**
	 * Private constructor so it can't be instantiated
	 */
	private Validation() {

	}

	/**
	 * Validates a person's title (Obligatory field)
	 * 
	 * @param title
	 * @return true only if the title is correct
	 */
	public static boolean validateTitle(String title) {
		boolean result = false;

		if (title != null) {
			for (int i = 0; i < titles.length; i++) {
				if (titles[i].equals(title)) {
					result = true;
					break;
				}
			}
		}

		return result;
	}

	/**
	 * Validates a person's name (Obligatory field)
	 * 
	 * @param name
	 * @return true only if the name is between 1 and 50 characters. Can't be blank
	 */
	public static boolean validateName(String name) {

		if (name != null) {
			return Pattern.matches(REGEX_NAME, name);
		} else {
			return false;
		}

	}

	/**
	 * Validates a person's name (Obligatory field)
	 * 
	 * @param name
	 * @return true only if the name is between 1 and 50 characters. Can't be blank
	 */
	public static boolean validateSurNames(String surName) {

		if (surName != null) {
			return Pattern.matches(REGEX_SURNAME, surName);
		} else {
			return false;
		}

	}

	/**
	 * Validates a phone number in Spanish format (Obligatory field)
	 * 
	 * @param phoneNumber
	 * @return true only if the number has 9 digits and starts with a number between
	 *         6 and 9 inclusive
	 */
	public static boolean validatePhoneNumber(String phoneNumber) {

		if (phoneNumber != null) {
			return Pattern.matches(REGEX_PHONE_NUMBER, phoneNumber);
		} else {
			return false;
		}
	}

	/**
	 * Validates a Spanish Postal code (Obligatory field)
	 * 
	 * @param postalCode
	 * @return true if it begins with a number between 01 and 52 inclusive and
	 *         follows by 3 digits
	 */
	public static boolean validateCP(String postalCode) {

		if (postalCode != null) {
			return Pattern.matches(REGEX_CP, postalCode);
		} else {
			return false;
		}

	}

	/**
	 * Validates an email direction (Obligatory field)
	 * 
	 * @param email
	 * @return true only if it's a valid email format
	 */
	public static boolean validateEmail(String email) {
		if (email != null) {
			return Pattern.matches(REGEX_EMAIL, email);
		} else {
			return false;
		}
	}

	/**
	 * Validates an URL (Optional)
	 * 
	 * @param url
	 * @return true if it's a valid url, or the field it's empty because it's
	 *         optional
	 */
	public static boolean validateURL(String url) {

		boolean result = false;

		if (url != null) {
			result = Pattern.matches(REGEX_URL, url) || url.isBlank();
		}

		return result;
	}

	/**
	 * Validates an username (Obligatory field)
	 * 
	 * @param username
	 * @return true only if the username is between 1 and 10 characters long and
	 *         only contains characters like letters, digits or [_], [-]. It also
	 *         cannot be null
	 */
	public static boolean validateUserName(String username) {

		boolean result = false;

		if (username != null) {
			result = Pattern.matches(REGEX_USERNAME, username);

		}

		return result;

	}

	/**
	 * Validates a password (Obligatory field)
	 * 
	 * @param password
	 * @return true only if the password is between 8 and 16 characters long and
	 *         contains at least an uppercase letter, a lowercase letter, a digit
	 *         and a special character. Cant be null
	 */
	public static boolean validatePassword(String password) {

		boolean result = false;

		if (password != null) {
			result = Pattern.matches(REGEX_PASSWORD, password);
		}
		return result;
	}

	/**
	 * Comprueba una fecha de nacimiento
	 * 
	 * @param registerDate
	 * @return true sólo si la fecha es menor a la actual, cumple con el formato
	 *         "dd/MM/yyyy" y no está vacía
	 */
	public static boolean validateRegisterDate(String registerDate) {
		boolean result = false;

		if (registerDate != null) {
			result = Pattern.matches(REGEX_FECHA, registerDate) && !registerDate.isBlank();
		}

		return result;
	}

}
