package hilos;

import commons.CalculaPrimosApp;

/**
 * Clase que crea hilos para devolver números primos
 */
public class HiloCalculaPrimo extends Thread {

	int minimo;

	int maximo;

	/**
	 * @param minimo
	 * @param maximo
	 */
	public HiloCalculaPrimo(int minimo, int maximo) {
		this.minimo = minimo;
		this.maximo = maximo;
	}

	@Override
	public void run() {
		CalculaPrimosApp.imprimirNumerosPrimosEntre(minimo, maximo);
		System.out.println("\nTiempo del hilo: " + System.currentTimeMillis());
		System.out.println("----------------------------------");
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
