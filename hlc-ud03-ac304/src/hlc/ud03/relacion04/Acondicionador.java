package hlc.ud03.relacion04;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

public class Acondicionador {

	private static final String REGEX_URL = "^[A-Za-z\\-\\._~\\d@]$";
	private static final char[] caracteresCss = { '\'', '(', '<', '>', '&', ')', '/', '\"', '\\' };
	private static final char[] caracteresSql = { '\'', '%', '_', '\"', '\\' };

	/**
	 * 
	 * @param elementoHtml
	 * @return
	 */
	public static String acondicionaElementoHtml(String elementoHtml) {

		String htmlAcondicionado = gethtmlAcondicionado(elementoHtml, false);

		return htmlAcondicionado;
	}

	/**
	 * 
	 * @param atributoHtml
	 * @return
	 */
	public static String acondicionaAtributoHtml(String atributoHtml) {

		String atributoAcondicionado = gethtmlAcondicionado(atributoHtml, true);

		return atributoAcondicionado;
	}

	/**
	 * 
	 * @param contenidoCss
	 * @return
	 */
	public static String acondicionaContenidoCss(String contenidoCss) {

		// Añadir espacios si tiene un número despues de un carácter especial
		String contenidoConEspacios = insertarEspacio(contenidoCss);

		// Sustituir carácteres especiales paso a paso
		String sinComillasSimp = contenidoConEspacios.replaceAll("'", "\\\\27");
		String sinComillasDob = sinComillasSimp.replaceAll("\"", "\\\\22");
		String sinParentApert = sinComillasDob.replaceAll("\\(", "\\\\28");
		String sinParentCierre = sinParentApert.replaceAll("\\)", "\\\\29");
		String sinEtiqApert = sinParentCierre.replaceAll("<", "\\\\3c");
		String sinEtiqCierre = sinEtiqApert.replaceAll(">", "\\\\3e");
		String sinBarraInvertida = sinEtiqCierre.replaceAll("\\\\", "\\\\5c");
		String sinBarra = sinBarraInvertida.replaceAll("/", "\\\\2f");
		String resultado = sinBarra.replaceAll("&", "\\\\26");

		System.out.println(resultado);

		return resultado;
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static String acondicionaUrl(String url) {

		StringBuilder builder = new StringBuilder();

		// Recorrer la cadena
		for (int i = 0; i < url.length(); i++) {

			// Obtener el caracter actual
			char currentChar = url.charAt(i);

			// Si el carácter no es correcto para una url
			if (!isCaracterOkUrl(currentChar)) {

				// Convertirlo a UTF-8 y añadirlo al builder
				String utfChar = charToUTF8(currentChar);
				builder.append(utfChar);

			} else {
				// Si es correcto, añadirlo directamente
				builder.append(currentChar);
			}
		}

		String result = builder.toString();
		return result;
	}

	/**
	 * 
	 * @param literalSql
	 * @return
	 */
	public static String acondicionaLiteralSql(String literalSql) {

		StringBuilder builder = new StringBuilder();

		// Recorrer la cadena
		for (int i = 0; i < literalSql.length(); i++) {
			// Obtengo el carácter actual
			char currentChar = literalSql.charAt(i);

			// Si es especial de SQL le añado \
			if (isCaracterSql(currentChar)) {
				String caracterNoSql = new String("\\" + currentChar);
				builder.append(caracterNoSql);
			} else {
				// Si no es especial lo añado tal cual
				builder.append(currentChar);
			}

		}

		return builder.toString();
	}

	/**
	 * Obtains the UTF-8 of a character
	 * 
	 * @param inputChar
	 * @return the UTF-8 value of the input character
	 */
	public static String charToUTF8(char inputChar) {
		try {
			byte[] utf8Bytes = String.valueOf(inputChar).getBytes("UTF-8");

			StringBuilder result = new StringBuilder();
			for (byte b : utf8Bytes) {
				result.append(String.format("%%%02X", b));
			}

			return result.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Verifica si un caracter es especial de SQL
	 * 
	 * @param caracter
	 * @return true solo si el carácter es un carácter especial SQL
	 */
	private static boolean isCaracterSql(char caracter) {

		boolean result = Boolean.FALSE;

		for (char c : caracteresSql) {
			if (c == caracter) {
				result = Boolean.TRUE;
				break;
			}
		}

		return result;

	}

	/**
	 * 
	 * @param caracter
	 * @return
	 */
	private static boolean isCaracterOkUrl(char caracter) {
		return Pattern.matches(REGEX_URL, String.valueOf(caracter));
	}

	/**
	 * Acondiciona un elemento html
	 * 
	 * @param html     elemento a acondicionar
	 * @param atributo si es true se acondicionará como atributo, si es false como
	 *                 elemento
	 * @return elemento acondicionado
	 */
	private static String gethtmlAcondicionado(String html, boolean atributo) {

		String sinAmp = html.replaceAll("&", "&amp;");
		String sinApertura = sinAmp.replaceAll("<", "&lt;");
		String sinCierre = sinApertura;

		// Si no es atributo quitar el >
		if (!atributo) {
			sinCierre = sinApertura.replaceAll(">", "&gt;");
			return sinCierre;
		} else {
			// Si es atributo no quitar > y quitar las comillas simples y dobles
			String sinComillaSimple = sinCierre.replaceAll("\"", "&#34;");
			String atributoAcondicionado = sinComillaSimple.replaceAll("'", "&#39;");
			return atributoAcondicionado;
		}

	}

	/**
	 * Obtiene una cadena con espacios insertados después de los carácteres
	 * especiales de CSS si a estos le sigue un número
	 * 
	 * @param input
	 * 
	 * @return cadena con los espacios insertados
	 */

	public static String insertarEspacio(String input) {
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < input.length(); i++) {
			char currentChar = input.charAt(i);
			result.append(currentChar);

			// Verificar si el carácter actual es el especificado y si le sigue un número
			for (int j = 0; j < caracteresCss.length; j++) {
				if (caracteresCss[j] == currentChar && i + 1 < input.length()
						&& Character.isDigit(input.charAt(i + 1))) {
					result.append(' ');
				}
			}
		}

		return result.toString();
	}

}
