package psp.unidad02.relacion02.actividad01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import commons.ListaPrimos;
import exceptions.ParametrosException;
import hilos.Worker;
import utils.ProcesaParametros;

public class CalculaPrimosCompartidosApp {

	private static final int NUMERO_PROCESADORES = Runtime.getRuntime().availableProcessors();

	public static void main(String[] args) throws InterruptedException {

		try {

			ProcesaParametros params = new ProcesaParametros(args);
			List<Worker> hilos = getListaHilos(params);

			for (Worker worker : hilos) {
				worker.start();
			}

			for (Worker worker : hilos) {
				worker.join();
			}

			Collections.sort(ListaPrimos.getPrimos());
			ListaPrimos.printLista();

		} catch (ParametrosException e) {
			System.err.println(e.getLocalizedMessage());
		}

	}

	/**
	 * Obtiene una lista de hilos con los rangos repartidos para calcular los
	 * números primos entre este rango
	 * 
	 * @param param
	 * @return la lista con los hilos
	 */
	private static List<Worker> getListaHilos(ProcesaParametros param) {

		// Rango de números a calcular
		int rango = param.getRango();

		// Cantidad de números a repartir entre hilos
		int reparticion = rango / NUMERO_PROCESADORES;

		int elementosExtra = rango % NUMERO_PROCESADORES;

		System.out.println("Repartición: " + reparticion + " | " + "Sobrante: " + elementosExtra);

		// Iniciar lista de hilos
		List<Worker> hilos = new ArrayList<Worker>();

		int numHilo = 1;
		int contador = 0;
		for (int i = 0; i < NUMERO_PROCESADORES; i++) {

			// Mínimo del hilo, empieza en el mínimo, se le suma la repartición cada
			// vuelta
			int minimo = param.getMinimo() + contador;

			/*
			 * Máximo del hilo, empieza por la cantidad a repartir, se le va sumando esta
			 * misma a cada vuelta, Si hay elementos extra se le suma uno
			 */
			int maximo = contador + reparticion;

			if (elementosExtra > 0) {
				maximo++;
				minimo++;
				elementosExtra--;
			}

			hilos.add(new Worker(minimo, maximo, numHilo));

			contador += reparticion;
			numHilo++;
		}

		return hilos;

	}

}
