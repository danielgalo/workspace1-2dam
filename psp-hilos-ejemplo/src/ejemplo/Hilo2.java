package ejemplo;

public class Hilo2 extends Thread {

	public int numeroHilo;

	@Override
	public void run() {
		for (int i = 1000; i > 900; i--) {
			System.out.println("Hilo: " + numeroHilo + " | Número: " + i);
		}
	}

}
