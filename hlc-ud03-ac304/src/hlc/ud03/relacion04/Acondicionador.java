package hlc.ud03.relacion04;

public class Acondicionador {

	private static final char[] caracteresCss = { '\'', '(', '<', '>', '&', ')', '/', '\"', '\\' };

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
		String sinComillasSimp = contenidoConEspacios.replaceAll("'", "\\27");
		String sinComillasDob = sinComillasSimp.replaceAll("\"", "\\22");
		String sinParentApert = sinComillasDob.replaceAll("\\(", "\\28");
		String sinParentCierre = sinParentApert.replaceAll("\\)", "\\29");
		String sinEtiqApert = sinParentCierre.replaceAll("<", "\\3c");
		String sinEtiqCierre = sinEtiqApert.replaceAll(">", "\\3e");
		String sinBarraInvertida = sinEtiqCierre.replaceAll("\\\\", "\\5c");
		String sinBarra = sinBarraInvertida.replaceAll("/", "\\2f");
		String resultado = sinBarra.replaceAll("&", "\\26");

		System.out.println(resultado);

		return resultado;
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static String acondicionaUrl(String url) {
		// TODO Auto-generated method stub
		return url;
	}

	/**
	 * 
	 * @param literalSql
	 * @return
	 */
	public static String acondicionaLiteralSql(String literalSql) {
		// TODO Auto-generated method stub
		return literalSql;
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
