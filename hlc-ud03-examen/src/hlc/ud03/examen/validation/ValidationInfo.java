package hlc.ud03.examen.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase usada para obtener mensajes de validacion
 */
public class ValidationInfo {

	private static List<String> datosOk = new ArrayList<>();
	private static List<String> errores = new ArrayList<>();

	public static void addDatoOk(String msg) {

		datosOk.add(msg);

	}

	public static void addError(String msg) {

		errores.add(msg);

	}

	public static void imprimeErrores() {
		imprimeLista(errores);
	}

	public static void imprimeDatosOk() {
		imprimeLista(datosOk);
	}

	private static void imprimeLista(List<String> lista) {
		for (String string : lista) {
			System.out.println(string);
		}
	}

	/**
	 * @return the datosOk
	 */
	public static List<String> getDatosOk() {
		return datosOk;
	}

	/**
	 * @param datosOk the datosOk to set
	 */
	public static void setDatosOk(List<String> datosOk) {
		ValidationInfo.datosOk = datosOk;
	}

	/**
	 * @return the errores
	 */
	public static List<String> getErrores() {
		return errores;
	}

	/**
	 * @param errores the errores to set
	 */
	public static void setErrores(List<String> errores) {
		ValidationInfo.errores = errores;
	}

}
