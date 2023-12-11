package hlc.ud03.examen.main;

import hlc.ud03.examen.datos.BloqueDatos;
import hlc.ud03.examen.datos.BloqueDatosEnFichero;
import hlc.ud03.examen.validation.Validation;
import hlc.ud03.examen.validation.ValidationInfo;

/**
 * Clase principal
 */
public class ValidarProductosApp {

	/**
	 * Método principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Si no se han introducido parámetros
		if (args.length < 1) {
			System.err.println("Error, se debe de introducir un parámetro referido a un archivo.");
			return;
		}

		// Procesar archivo
		String archivo = args[0];
		BloqueDatos bloque = new BloqueDatosEnFichero(archivo);

		// Conseguir datos
		String referencia = bloque.getDato("referencia");
		String nombre = bloque.getDato("nombre");
		String marca = bloque.getDato("marca");
		String precio = bloque.getDato("precio");
		String puntuacion = bloque.getDato("puntuacion");
		String fechaInicio = bloque.getDato("fecha_inicio_venta");
		String dominio = bloque.getDato("dominio");
		String url = bloque.getDato("url");
		String correoPedidos = bloque.getDato("correo_pedidos");
		String puntosFuertes = bloque.getDato("puntos_fuertes");
		String puntosDebiles = bloque.getDato("puntos_debiles");

		// Validar datos
		validaDato("referencia", referencia, Validation.validaReferencia(referencia));
		validaDato("nombre", nombre, Validation.validaNombre(nombre));
		validaDato("marca", marca, Validation.validaMarca(marca));
		validaDato("precio", precio, Validation.validaPrecio(precio));
		validaDato("puntuacion", puntuacion, Validation.validaPuntuacion(puntuacion));
		validaDato("fecha_inicio_venta", fechaInicio, Validation.validaFechaVenta(fechaInicio));
		validaDato("dominio", dominio, Validation.validaDominio(dominio));
		validaDato("url", url, Validation.validaUrl(url));
		validaDato("correo_pedidos", correoPedidos, Validation.validaCorreo(correoPedidos));

		if (Validation.validaPuntos(puntosFuertes.split(","))) {
			ValidationInfo.addDatoOk("Puntos fuertes OK ");
		} else {
			ValidationInfo.addError("Puntos fuertes Erróneo ");
		}

		if (Validation.validaPuntos(puntosDebiles.split(","))) {
			ValidationInfo.addDatoOk("Puntos débiles OK ");
		} else {
			ValidationInfo.addError("Puntos débiles Erróneo ");
		}

		// Imprime resultados
		if (!ValidationInfo.getErrores().isEmpty()) {
			System.out.println("Se encontraron los siguientes " + ValidationInfo.getErrores().size() + " errores:");
			ValidationInfo.imprimeErrores();
		} else {
			System.out.println("No se han encontrado errores");
		}

	}

	/**
	 * Valida un dato
	 * 
	 * @param campo
	 * @param dato
	 * @param validacion
	 */
	public static void validaDato(String campo, String dato, boolean validacion) {
		if (validacion) {
			ValidationInfo.addDatoOk(campo + " OK: " + dato);
		} else {
			ValidationInfo.addError(campo + " Erróneo: " + dato);
		}
	}
}
