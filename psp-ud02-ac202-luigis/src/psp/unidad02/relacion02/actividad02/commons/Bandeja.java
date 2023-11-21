package psp.unidad02.relacion02.actividad02.commons;

import java.util.ArrayList;
import java.util.List;

import psp.unidad02.relacion02.actividad02.models.Pizza;

/**
 * Representa una bandeja de pizzas, esta podrá tener infinitas pizzas aunque
 * nunca pizzas negativas. Los Pizzeros depositarán pizzas y los Clientes las
 * recojeran si están disponibles
 */
public class Bandeja {

	/** Lista de pizzas de la bandeja */
	private static List<Pizza> pizzas = new ArrayList<>();

	/**
	 * Añade una pizza
	 */
	public static synchronized void addPizza(Pizza p) {
		pizzas.add(p);
		System.out.println("Pizza añadida a la bandeja.");
	}

	/**
	 * Coge la primera pizza de la bandeja, si hay pizzas disponibles. Elimina a su
	 * vez esta pizza de la bandeja
	 * 
	 * @return true si pudo tomar una pizza
	 */
	public static synchronized boolean cogePizza() {

		boolean isPizzaCogida = false;

		// Si hay al menos una pizza
		if (pizzas.size() > 0) {

			pizzas.remove(pizzas.get(0));
			System.out.println("--- Pizza recojida de la bandeja ---");
			isPizzaCogida = true;

			// Si la bandeja está vacía
		} else {
			System.out.println("--- No hay pizzas disponibles ---");
		}

		return isPizzaCogida;
	}

	/**
	 * @return the numeroPizzas
	 */
	public static int getNumeroPizzas() {
		return pizzas.size();
	}

}
