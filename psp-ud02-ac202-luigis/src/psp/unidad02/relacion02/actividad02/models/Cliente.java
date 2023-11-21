package psp.unidad02.relacion02.actividad02.models;

import java.util.Random;

import psp.unidad02.relacion02.actividad02.PizzeriaLuigi;
import psp.unidad02.relacion02.actividad02.commons.Bandeja;

/**
 * Cliente de la pizzeria Luigi
 */
public class Cliente extends Thread {

	/** Cantidad de pizzas tomadas por el cliente, el máximo serán 5 */
	private static int pizzasTomadas = 0;

	private static final int COMIDO_MAX_ESPERA = 30000;
	private static final int COMIDO_MIN_ESPERA = 20000;

	private static final int NO_COMIDO_MAX_ESPERA = 15000;
	private static final int NO_COMIDO_MIN_ESPERA = 10000;

	/** Identificador del cliente */
	private int numeroCliente;

	/**
	 * @param numeroCliente
	 */
	public Cliente(int numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	@Override
	public void run() {

		System.out.println(msgCliente() + " llega a la pizzería.");

		try {

			do {

				if (pizzasTomadas > 0 && pizzasTomadas < 5) {
					System.out.println(msgCliente() + " volvió del paseo.");
				}

				// Tiempos de espera
				int esperaPizzaNoTomada = getTiempoEspera(NO_COMIDO_MAX_ESPERA, NO_COMIDO_MIN_ESPERA);
				int esperaPizzaTomada = getTiempoEspera(COMIDO_MAX_ESPERA, COMIDO_MIN_ESPERA);
				// Si pudo tomar una pizza
				if (Bandeja.cogePizza()) {

					// Añadir una pizza a las tomadas
					System.out
							.println(msgCliente() + " tomó una pizza. Pizzas restantes: " + Bandeja.getNumeroPizzas());
					pizzasTomadas++;

					System.out.println(msgCliente() + msgPizzasTomadas() + " paseando lleno...");
					// Esperar el tiempo de reposo
					sleep(esperaPizzaTomada);

				} else {
					// Si no pudo tomar la pizza
					System.out.println(msgCliente() + " no pudo tomar una pizza.");
					System.out.println(msgCliente() + msgPizzasTomadas() + " paseando hambriento..");

					// Esperar menos al no haber tomado la pizza
					sleep(esperaPizzaNoTomada);

				}
			} while (pizzasTomadas < 5);

			// Infomar de que el cliente abandona el local
			PizzeriaLuigi.reduceClientela();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Genera un número aleatorio entre MIN_TIEMPO_COCINA y MAX_TIEMPO_COCINA
	 * 
	 * @return un entero aleatorio en rango
	 */
	private int getTiempoEspera(int max, int min) {

		Random random = new Random();
		int numeroAleatorio = random.nextInt(max - min + 1) + min;
		return numeroAleatorio;

	}

	/**
	 * Mensaje del pizzero
	 * 
	 * @return
	 */
	private String msgCliente() {
		return "Cliente " + numeroCliente;
	}

	private String msgPizzasTomadas() {
		return " se ha comido " + pizzasTomadas + " pizza(s) en total, ";
	}

}
