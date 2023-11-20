package main;

import java.util.ArrayList;
import java.util.List;

import exceptions.ParametrosException;
import hilos.HiloCalculaPrimo;
import utils.ProcesaParametros;

/**
 * 
 */
public class CalculaPrimosHilosApp {

	/** Número de procesadores del equipo */
	private static final int NUMERO_PROCESADORES = Runtime.getRuntime().availableProcessors();

	public static void main(String[] args) {

		try {
			// Procesar parámetros
			ProcesaParametros params = new ProcesaParametros(args);

			List<HiloCalculaPrimo> hilos = getListaHilos(params);

			for (HiloCalculaPrimo hilo : hilos) {
				hilo.run();
			}

			// Si los parámetros no son correctos
		} catch (ParametrosException e) {

			e.printStackTrace();

		}

	}

	/**
	 * Obtiene una lista de hilos con los rangos repartidos para calcular los
	 * números primos entre este rango
	 * 
	 * @param param
	 * @return la lista con los hilos
	 */
	private static List<HiloCalculaPrimo> getListaHilos(ProcesaParametros param) {

		// Rango de números a calcular
		int rango = param.getRango();

		// Cantidad de números a repartir entre hilos
		int reparticion = rango / NUMERO_PROCESADORES;

		int elementosExtra = rango % NUMERO_PROCESADORES;

		System.out.println("Repartición: " + reparticion + " | " + "Sobrante: " + elementosExtra);

		// Iniciar lista de hilos
		List<HiloCalculaPrimo> hilos = new ArrayList<HiloCalculaPrimo>();

		int numHilo = 1;
		int contador = 0;
		for (int i = 0; i < NUMERO_PROCESADORES; i++) {

			// Mínimo del hilo, empieza en el mínimo, se le suma la repartición cada
			// vuelta
			int minimo = param.getMinimo() + contador;

			/*
			 * Máximo del hilo, empieza por la cantidad a repartir, se le va sumando esta
			 * misma a cada vuelta, Si hay elementos extra se le suma uno al final
			 */
			int maximo = contador + reparticion;

			if (elementosExtra > 0) {
				maximo++;
				elementosExtra--;
			}

			hilos.add(new HiloCalculaPrimo(minimo, maximo, numHilo));

			contador += reparticion;
			numHilo++;
		}

		return hilos;

	}

}
