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
		System.out.println("--- Pizza añadida a la bandeja (" + pizzas.size() + ") restantes ---");
	}

	/**
	 * Coge la primera pizza de la bandeja y se la añade a la lista de pizzas
	 * tomadas del cliente si hay pizzas disponibles. Elimina a su vez esta pizza de
	 * la bandeja.
	 * 
	 * @param pizzasCliente lista de pizzas del cliente, donde se añadirá la pizza
	 *                      si se pudo tomar
	 * @return true si pudo tomar una pizza
	 */
	public static synchronized boolean cogePizza(List<Pizza> pizzasCliente) {

		boolean isPizzaCogida = false;

		// Si hay al menos una pizza
		if (pizzas.size() > 0) {

			// Le da la pizza al cliente
			Pizza pizzaTomada = pizzas.get(0);
			pizzasCliente.add(pizzaTomada);

			// La elimina de la bandeja
			pizzas.remove(pizzas.get(0));

			System.out.println("--- Pizza recojida de la bandeja ---");
			isPizzaCogida = true;

			// Si la bandeja está vacía
		} else {
			System.out.println("--- No hay pizzas disponibles para tomar ---");
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
