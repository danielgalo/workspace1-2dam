package hlc.ud03.examen.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Clase que aporta métodos estáticos para validación de datos
 */
public class Validation {

	private static final String REGEX_DOMINIO = "^([A-Za-z\\d]+\\.?)+";
	private static final int MAX_LENGTH_NOMBRE = 200;
	private static final String REGEX_REFERENCIA = "^(3[4-9]|6[0-7])\\d{11}$";
	private static final String REGEX_DECIMAL_UN_DIGITO = "^\\d+\\.\\d{1}$";
	private static final String REGEX_DECIMAL_DOS_DIGITOS = "^\\d+\\.\\d{2}$";
	private static final String REGEX_FECHA = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
	private static final String REGEX_URL = "^(http://|https://)(www\\.)?[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,})?(:\\d+)?(/\\S*)?$";
	private static final String REGEX_EMAIL = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	private static final String[] PUNTOS_DEBILES_FUERTES = { "Precio", "Tecnologia", "Durabilidad", "Disponibilidad",
			"Marca", "Sostenible", "Calidad", "Tamaño" };

	/**
	 * Constructor privado
	 */
	private Validation() {

	}

	/**
	 * Valida la referencia (obligatoria, 13 caracteres)
	 * 
	 * @param referencia
	 * @return
	 */
	public static boolean validaReferencia(String referencia) {
		return verificaObligatorio(referencia) && Pattern.matches(REGEX_REFERENCIA, referencia);
	}

	/**
	 * Valida el nombre (obligatorio, máximo 200 caracteres)
	 * 
	 * @param nombre
	 * @return
	 */
	public static boolean validaNombre(String nombre) {
		return verificaObligatorio(nombre) && nombre.length() <= MAX_LENGTH_NOMBRE;
	}

	/**
	 * Valida la marca, máximo 100 caracteres (Opcional)
	 * 
	 * @param marca
	 * @return
	 */
	public static boolean validaMarca(String marca) {

		boolean resultado = false;

		// Si no se especifica
		if (marca == null || marca.isBlank()) {
			resultado = true;
		} else {
			// Si se especifica, que cumpla requisitos
			resultado = marca.length() <= 100;
		}

		return resultado;
	}

	/**
	 * Valida el precio (Obligatorio) debe ser mayor a cero y un número con dos
	 * decimales
	 * 
	 * @param precioStr
	 * @return
	 */
	public static boolean validaPrecio(String precioStr) {
		boolean resultado = false;

		try {

			// Verifica que se haya introducido algo
			if (verificaObligatorio(precioStr)) {
				double precio = Double.parseDouble(precioStr);
				// Si el decimal tiene un digito y está entre 0 y 5 incluidos
				if (Pattern.matches(REGEX_DECIMAL_DOS_DIGITOS, precioStr) && precio >= 0) {
					resultado = true;
				}
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	/**
	 * Valida la puntuacion (obligatorio) debe ser un número con un decimal entre 0
	 * y 5
	 * 
	 * @param puntuacionStr
	 * @return
	 */
	public static boolean validaPuntuacion(String puntuacionStr) {
		boolean resultado = false;

		try {

			// Verifica que se haya introducido algo
			if (verificaObligatorio(puntuacionStr)) {
				double puntuacion = Double.parseDouble(puntuacionStr);
				// Si el decimal tiene un digito y está entre 0 y 5 incluidos
				if (Pattern.matches(REGEX_DECIMAL_UN_DIGITO, puntuacionStr) && (puntuacion >= 0 && puntuacion <= 5)) {
					resultado = true;
				}
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	/**
	 * Valida una fecha (obligatorio) con formato dd/MM/yyyy. Comprueba que sea
	 * anterior a la actual.
	 * 
	 * @param fechaVenta
	 * @return
	 */
	public static boolean validaFechaVenta(String fechaVenta) {

		boolean resultado = false;

		// Verifica que sea obligatorio
		if (verificaObligatorio(fechaVenta)) {
			// Que sea fecha en formato correcto y anterior a la actual
			if (Pattern.matches(REGEX_FECHA, fechaVenta) && isFechaAnterior(fechaVenta)) {
				resultado = true;
			}
		}
		return resultado;
	}

	/**
	 * Valida una url (obligatorio)
	 * 
	 * @param url
	 * @return
	 */
	public static boolean validaUrl(String url) {
		return verificaObligatorio(url) && Pattern.matches(REGEX_URL, url);
	}

	/**
	 * Valida un correo (obligatorio)
	 * 
	 * @param correo
	 * @return
	 */
	public static boolean validaCorreo(String correo) {
		return verificaObligatorio(correo) && Pattern.matches(REGEX_EMAIL, correo);
	}

	/**
	 * Valida los puntos fuertes y debiles (opcional), no pueden repetirse
	 * 
	 * @param puntos
	 * @return
	 */
	public static boolean validaPuntos(String[] puntos) {
		List<String> listaPuntos = new ArrayList<>();

		boolean resultado = false;

		for (String punto : PUNTOS_DEBILES_FUERTES) {
			listaPuntos.add(punto);
		}

		// Comprobar que coincide
		for (String elemento : puntos) {
			if (listaPuntos.contains(elemento)) {
				resultado = true;
			}
		}

		// Si uno falla
		for (String elemento : puntos) {
			if (!listaPuntos.contains(elemento)) {
				resultado = false;
			}
		}

		return resultado;
	}

	/**
	 * Verifica un dominio (obligatorio)
	 * 
	 * @param dominio
	 * @return
	 */
	public static boolean validaDominio(String dominio) {
		return verificaObligatorio(dominio) && Pattern.matches(REGEX_DOMINIO, dominio);
	}

	/**
	 * Verifica que un campo sea obligatorio
	 * 
	 * @param cadena
	 * @return
	 */
	private static boolean verificaObligatorio(String cadena) {
		return cadena != null && !cadena.isBlank();
	}

	/**
	 * Comprueba que una fecha sea anterior a la actual
	 * 
	 * @param inputDate
	 * @return
	 */
	private static boolean isFechaAnterior(String inputDate) {
		// Define el formato de la fecha esperada
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);

		try {
			// Parsea la cadena de entrada en un objeto Date
			Date date = sdf.parse(inputDate);

			// Obtiene la fecha actual
			Date currentDate = new Date();

			// Compara las fechas
			return date.before(currentDate);
		} catch (ParseException e) {
			// La cadena no es un formato de fecha válido
			return false;
		}
	}

}
