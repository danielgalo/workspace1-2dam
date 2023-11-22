package psp.unidad02.relacion02.actividad02.utils;

/**
 * Clase usada para controlar los parametros de la Clase principal PizzeriaLuigi
 */
public class Parametros {

	/** Número de pizzeros por defecto */
	private static final int PIZZEROS_DEFECTO = 2;
	/** Número de clientes por defecto */
	private static final int CLIENTES_DEFECTO = 5;

	/** Número de pizzeros */
	private int pizzeros;
	/** Número de clientes */
	private int clientes;

	/**
	 * Constructor
	 * 
	 * @param argumentos
	 */
	public Parametros(String[] argumentos) {

		// Valores por defecto
		pizzeros = PIZZEROS_DEFECTO;
		clientes = CLIENTES_DEFECTO;

		int numeroPizzeros = 0;
		int numeroClientes = 0;
		try {
			numeroPizzeros = Integer.parseInt(argumentos[0]);
			numeroClientes = Integer.parseInt(argumentos[1]);
		} catch (NumberFormatException e) {
			System.err.println("Error al parsear los parámetros. Se utilizarán los valores por defecto.");
		}

		// Si se proporciona al menos un parámetro actualizar los pizzeros
		if (argumentos.length > 0 && numeroPizzeros > 0) {

			pizzeros = numeroPizzeros;

		}

		// Si se han proporcionado los dos parámetros o más, actualizar también los
		// clientes
		if (argumentos.length > 1 && numeroClientes > 0) {

			clientes = numeroClientes;

		}

	}

	/**
	 * @return the pizzeros
	 */
	public int getPizzeros() {
		return pizzeros;
	}

	/**
	 * @param pizzeros the pizzeros to set
	 */
	public void setPizzeros(int pizzeros) {
		this.pizzeros = pizzeros;
	}

	/**
	 * @return the clientes
	 */
	public int getClientes() {
		return clientes;
	}

	/**
	 * @param clientes the clientes to set
	 */
	public void setClientes(int clientes) {
		this.clientes = clientes;
	}

}
