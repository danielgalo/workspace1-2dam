package hilos;

import commons.CalculaPrimosApp;
import commons.ListaPrimos;

public class Worker extends Thread {

	int minimo;
	int maximo;
	int numHilo;

	public Worker(int minimo, int maximo, int numHilo) {
		setMinimo(minimo);
		setMaximo(maximo);
		setNumHilo(numHilo);
	}

	@Override
	public void run() {

		for (int i = minimo; i <= maximo; i++) {
			if (CalculaPrimosApp.esPrimo(i)) {
				ListaPrimos.addPrimo((long) i);
			}
		}

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

	/**
	 * @return the numHilo
	 */
	public int getNumHilo() {
		return numHilo;
	}

	/**
	 * @param numHilo the numHilo to set
	 */
	public void setNumHilo(int numHilo) {
		this.numHilo = numHilo;
	}

}
