package utils;

import exceptions.ParametrosException;

/**
 * Clase usada para procesar parámetros en clases que calculan los números
 * primos comprendidos en un rango
 */
public class ProcesaParametros {

	/** Mínimo del rango, obligatorio y menor que el maximo */
	private int minimo;

	/** Máximo del rango, obligatorio y mayor que el mínimo */
	private int maximo;

	/**
	 * Constructor
	 * 
	 * @throws ParametrosException
	 * 
	 */
	public ProcesaParametros(String[] parametros) throws ParametrosException {

		// Si no se introducen al menos 2 parámetros
		if (parametros.length < 2) {

			throw new ParametrosException("Error, se deben pasar 2 parámetros. Pasados: " + parametros.length);

			// Si el minimo es mayor al maximo
		} else if (Integer.valueOf(parametros[0]) > Integer.valueOf(parametros[1])) {

			throw new ParametrosException(
					"Error, el inicio (" + parametros[0] + ") debe ser menor al final (" + parametros[1] + ").");

		} else {
			setMinimo(Integer.valueOf(parametros[0]));
			setMaximo(Integer.valueOf(parametros[1]));
		}

	}

	/**
	 * Obtiene la diferencia de rango
	 * 
	 * @return la diferencia entre el máximo y el mínimo
	 */
	public int getRango() {
		return getMaximo() - getMinimo();

	}

	/**
	 * @return the minimo
	 */
	public int getMinimo() {
		return minimo;
	}

	/**
	 * @param minimo the minimo to set
	 */
	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}

	/**
	 * @return the maximo
	 */
	public int getMaximo() {
		return maximo;
	}

	/**
	 * @param maximo the maximo to set
	 */
	public void setMaximo(int maximo) {
		this.maximo = maximo;
	}

}
