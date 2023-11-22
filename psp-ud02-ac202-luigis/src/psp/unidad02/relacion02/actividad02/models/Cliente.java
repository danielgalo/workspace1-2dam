package psp.unidad02.relacion02.actividad02.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import psp.unidad02.relacion02.actividad02.PizzeriaLuigi;
import psp.unidad02.relacion02.actividad02.commons.Bandeja;
import psp.unidad02.relacion02.actividad02.utils.UsuarioPizzeriaI;

/**
 * Cliente de la pizzeria Luigi
 */
public class Cliente extends Thread implements UsuarioPizzeriaI {

	/** Tiempo de espera máximo después de haber tomado una pizza */
	private static final int COMIDO_MAX_ESPERA = 30000;
	/** Tiempo de espera mínimo después de haber tomado una pizza */
	private static final int COMIDO_MIN_ESPERA = 20000;

	/** Tiempo de espera máximo después de NO haber tomado una pizza */
	private static final int NO_COMIDO_MAX_ESPERA = 15000;
	/** Tiempo de espera máximo después de NO haber tomado una pizza */
	private static final int NO_COMIDO_MIN_ESPERA = 10000;

	/** Pizzas máximas a tomar por cada cliente */
	private static final int MAX_PIZZAS_TOMADAS = 5;

	/** Lista de pizzas tomadas por el cliente */
	private List<Pizza> pizzasTomadas;

	/** Identificador del cliente */
	private int numeroCliente;

	/**
	 * @param numeroCliente
	 */
	public Cliente(int numeroCliente) {
		this.numeroCliente = numeroCliente;
		pizzasTomadas = new ArrayList<>();
	}

	@Override
	public void run() {

		System.out.println(msgUsuario() + " llega a la pizzería.");
		boolean primeraVuelta = false;

		try {

			do {

				// Si ya lleva una vuelta y no supera las 5 pizzas
				if (primeraVuelta && pizzasTomadas.size() < MAX_PIZZAS_TOMADAS) {
					System.out.println(msgUsuario() + " volvió del paseo.");
				}

				// Calcular tiempos de espera
				int esperaPizzaNoTomada = getTiempoEspera(NO_COMIDO_MAX_ESPERA, NO_COMIDO_MIN_ESPERA);
				int esperaPizzaTomada = getTiempoEspera(COMIDO_MAX_ESPERA, COMIDO_MIN_ESPERA);

				// Intenta tomar una pizza, si pudo tomarla
				if (Bandeja.cogePizza(pizzasTomadas)) {
					// Toma la pizza
					pizzaTomada(esperaPizzaTomada);

				} else {
					// Si no pudo tomar la pizza al haberlo intentado
					pizzaNoTomada(esperaPizzaNoTomada);

				}

				primeraVuelta = true;
			} while (pizzasTomadas.size() < MAX_PIZZAS_TOMADAS);

			System.out.println(
					msgUsuario() + " abandona el local despues de tomarse " + pizzasTomadas.size() + " pizzas.");

			// Infomar de que el cliente abandona el local
			PizzeriaLuigi.reduceClientela();

		} catch (InterruptedException e) {
			System.err.println("Error interrumpiendo el hilo.");
		}

	}

	/**
	 * Informa con mensajes por pantalla que no pudo tomar la pizza y duerme el hilo
	 * 
	 * @param esperaPizzaNoTomada tiempo de espera en ms después de no haber
	 *                            conseguido tomar una pizza
	 * @throws InterruptedException
	 */
	private void pizzaNoTomada(int esperaPizzaNoTomada) throws InterruptedException {
		System.out.println(msgUsuario() + ". No pudo tomar una pizza, paseando hambriento... " + msgPizzasTomadas());
		// Pasear menos al no haber tomado la pizza
		sleep(esperaPizzaNoTomada);
	}

	/**
	 * Añade una pizza a la lisa de pizzas tomadas, duerme el hilo
	 * 
	 * @param esperaPizzaTomada tiempo de espera en ms después de haber tomado una
	 *                          pizza
	 * @throws InterruptedException
	 */
	private void pizzaTomada(int esperaPizzaTomada) throws InterruptedException {

		System.out.println(
				msgUsuario() + " tomó una pizza de " + pizzasTomadas.get(pizzasTomadas.size() - 1).getTipoPizza()
						+ ". Pizzas restantes: " + Bandeja.getNumeroPizzas());
		System.out.println(msgUsuario() + " paseando lleno... " + msgPizzasTomadas());
		// Pasear el tiempo de reposo
		sleep(esperaPizzaTomada);
	}

	@Override
	public int getTiempoEspera(int max, int min) {

		Random random = new Random();
		int numeroAleatorio = random.nextInt(max - min + 1) + min;
		return numeroAleatorio;

	}

	@Override
	public String msgUsuario() {
		return "Cliente " + numeroCliente;
	}

	/**
	 * Devuelve un mensaje con las pizzas tomadas por el cliente
	 * 
	 * @return
	 */
	private String msgPizzasTomadas() {
		return "Ha tomado " + pizzasTomadas.size() + " pizza(s) en total.";
	}

}
