package psp.unidad02.relacion02.actividad02.models;

import java.util.Random;

import psp.unidad02.relacion02.actividad02.PizzeriaLuigi;
import psp.unidad02.relacion02.actividad02.commons.Bandeja;
import psp.unidad02.relacion02.actividad02.utils.UsuarioPizzeriaI;

/**
 * Representa a un Pizzero de la Pizzeria Luigi's
 */
public class Pizzero extends Thread implements UsuarioPizzeriaI {

	private static final int MIN_TIEMPO_COCINA = 5000;
	private static final int MAX_TIEMPO_COCINA = 10000;

	/** Número identificador del pizzero */
	int numeroPizzero;

	/**
	 * Constructor
	 * 
	 * @param numeroPizzero
	 */
	public Pizzero(int numeroPizzero) {
		this.numeroPizzero = numeroPizzero;
	}

	@Override
	public void run() {
		// Pizzero empieza a trabajar
		System.out.println(msgUsuario() + " empieza a trabajar...");

		try {
			// Hace pizzas mientras haya clientes
			do {

				System.out.println(msgUsuario() + " empieza a cocinar...");
				// Cocina la pizza
				cocinaPizza();
				// Cuando termine, crear una pizza y añadirla a la bandeja
				addPizzaToBandeja();

			} while (PizzeriaLuigi.getClientela() > 0);

			System.out.println(msgUsuario() + " acabó su jornada.");

		} catch (InterruptedException e) {
			System.err.println("Error interrumpiendo el hilo.");
		}

	}

	/**
	 * Cocina la pizza. Duerme el hilo
	 * 
	 * @throws InterruptedException
	 */
	private void cocinaPizza() throws InterruptedException {
		// Calcular el tiempo que tarda en cocinar una pizza
		int tiempoCocina = getTiempoEspera(MAX_TIEMPO_COCINA, MIN_TIEMPO_COCINA);
		sleep(tiempoCocina);
		int segundosCocina = tiempoCocina / 1000;
		System.out.println(msgUsuario() + " terminó de cocinar en " + segundosCocina + " segundos.");
	}

	/**
	 * Añade una pizza a la bandeja
	 */
	private void addPizzaToBandeja() {
		Pizza pizza = new Pizza();
		Bandeja.addPizza(pizza);
		System.out.println(msgUsuario() + " añade la pizza " + pizza.getTipoPizza() + " a la bandeja, que ahora tiene "
				+ Bandeja.getNumeroPizzas() + " pizza(s).");
	}

	@Override
	public int getTiempoEspera(int max, int min) {

		Random random = new Random();
		int numeroAleatorio = random.nextInt(max - min + 1) + min;
		return numeroAleatorio;

	}

	@Override
	public String msgUsuario() {
		return "Pizzero " + numeroPizzero;
	}

}
