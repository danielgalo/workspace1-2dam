package psp.unidad02.relacion02.actividad02.models;

import java.util.Random;

import psp.unidad02.relacion02.actividad02.commons.Bandeja;

/**
 * Representa a un Pizzero de la Pizzeria Luigi's
 */
public class Pizzero extends Thread {

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

		try {

			// Pizzero empieza a trabajar
			System.out.println(msgPizzero() + " empieza a trabajar...");
			System.out.println(msgPizzero() + " empieza a cocinar...");

			// Calcular el tiempo que tarda en cocinar una pizza
			int tiempoCocina = getTiempoCocina();
			sleep(tiempoCocina);
			int segundosCocina = tiempoCocina / 1000;
			System.out.println(msgPizzero() + " terminó de cocinar en " + segundosCocina + " segundos.");

			// Cuando termine, crear una pizza y añadirla a la bandeja
			Pizza pizza = new Pizza();
			Bandeja.addPizza(pizza);
			System.out.println(msgPizzero() + " añade la pizza a la bandeja, que ahora tiene "
					+ Bandeja.getNumeroPizzas() + " pizza(s).");

			// TODO añadir todo esto a un bucle, que acabe cuando no hayan clientes
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Genera un número aleatorio entre MIN_TIEMPO_COCINA y MAX_TIEMPO_COCINA
	 * 
	 * @return un entero aleatorio en rango
	 */
	private int getTiempoCocina() {

		Random random = new Random();
		int numeroAleatorio = random.nextInt(MAX_TIEMPO_COCINA - MIN_TIEMPO_COCINA + 1) + MIN_TIEMPO_COCINA;
		return numeroAleatorio;

	}

	/**
	 * Mensaje del pizzero
	 * 
	 * @return
	 */
	private String msgPizzero() {
		return "Pizzero " + numeroPizzero;
	}

}
