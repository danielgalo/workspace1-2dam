package hlc.ud03.relacion04;

public class Acondicionador {

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

		// Quitar dobles comillas
		String sinComillasDob = contenidoCss.replaceAll("\"", "\\" + Integer.toHexString('"'));
		String sinComillasSimp = sinComillasDob.replaceAll("'", "\\27");

		return contenidoCss;
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

}
