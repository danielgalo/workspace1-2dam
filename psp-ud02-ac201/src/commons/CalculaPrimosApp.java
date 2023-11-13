package commons;

import exceptions.ParametrosException;
import utils.ProcesaParametros;

public class CalculaPrimosApp {

	public static void main(String[] args) {

		// Controlar parámetros
		ProcesaParametros params;

		try {

			params = new ProcesaParametros(args);
			imprimirNumerosPrimosEntre(params.getMinimo(), params.getMaximo());

			// Si los parámetros no son correctos
		} catch (ParametrosException e) {

			e.printStackTrace();
		}

	}

	/**
	 * Comprueba si un número es primo
	 * 
	 * @param numero
	 * @return true solo si el número es primo
	 */
	public static boolean esPrimo(int numero) {
		if (numero <= 1) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(numero); i++) {
			if (numero % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Imprime los números primos comprendidos en un rango
	 * 
	 * @param inicio del rango
	 * @param fin    del rango, debe ser mayor que el inicio
	 */
	public synchronized static void imprimirNumerosPrimosEntre(int inicio, int fin) {
		System.out.println("Números primos entre " + inicio + " y " + fin + ":");
		for (int i = inicio; i <= fin; i++) {
			if (esPrimo(i)) {
				System.out.print(i + ", ");
			}
		}
	}
}
