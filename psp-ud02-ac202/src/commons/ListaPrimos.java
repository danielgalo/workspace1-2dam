package commons;

import java.util.ArrayList;
import java.util.List;

public class ListaPrimos {

	private static List<Long> primos = new ArrayList<>();

	/**
	 * 
	 * @param primo
	 */
	public synchronized static void addPrimo(Long primo) {
		if (primo != null) {
			primos.add(primo);
		}
	}

	/**
	 * @return the primos
	 */
	public static List<Long> getPrimos() {
		return primos;
	}

	/**
	 * 
	 */
	public static void printLista() {
		System.out.println(primos);
	}
}
