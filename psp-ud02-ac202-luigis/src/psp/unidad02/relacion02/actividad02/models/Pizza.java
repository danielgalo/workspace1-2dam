package psp.unidad02.relacion02.actividad02.models;

import java.util.Random;

/**
 * Representa una Pizza
 */
public class Pizza {

	/** Tipos de pizza */
	private static String[] tipos = { "Margarita", "Cuatro quesos", "Barbacoa", "Atún" };

	/** Tipo de la pizza */
	private String tipoPizza;

	/**
	 * Constructor
	 */
	public Pizza() {
		setTipoPizza(tipoPizza);
	}

	/**
	 * @return the tipoPizza
	 */
	public String getTipoPizza() {
		return tipoPizza;
	}

	/**
	 * Asigna un valor aleatorio
	 * 
	 * @param tipoPizza the tipoPizza to set
	 */
	public void setTipoPizza(String tipoPizza) {
		this.tipoPizza = getRandomTipoPizza();
	}

	/**
	 * Devuelve aleatoriamente un tipo de pizza
	 * 
	 * @return
	 */
	private String getRandomTipoPizza() {
		return tipos[generarNumeroAleatorio()];
	}

	/**
	 * Genera un número aleatorio entre 0 y la longitud del array de tipos
	 * 
	 * @return
	 */
	public static int generarNumeroAleatorio() {
		Random random = new Random();

		// Genera un número aleatorio entre 0 y la longitud del array de tipos
		int numeroAleatorio = random.nextInt(tipos.length);

		return numeroAleatorio;
	}

}
