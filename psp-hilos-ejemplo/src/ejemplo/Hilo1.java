package ejemplo;

public class Hilo1 extends Thread {

	private int numHilo;

	public Hilo1(int numHilo) {
		setNumHilo(numHilo);
	}

	/*
	 * Es como el main en una app normal. Este método nunca se llama, para llamar a
	 * esta clase se llama al método start(), el cual va a este método.
	 * 
	 * Si se llama este método no se crea el hilo. Para crearlo llamar el método
	 * start() el cual viene con la clase Thread que heredamos.
	 * 
	 * Cuando se termina el método run se termina el hilo. Se termina pero no se
	 * destruye
	 */
	@Override
	public void run() {
		for (int i = 1; i <= 100; i++) {
			System.out.println("Hilo: " + numHilo + " | Número: " + i);
		}
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
