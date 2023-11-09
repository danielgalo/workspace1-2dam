package ejemplo;

public class HilosApp {

	public static void main(String[] args) {

		Hilo1 hilo = new Hilo1(1);
		Hilo1 hilo2 = new Hilo1(2);

		hilo.start();
		hilo2.start();

		Hilo2 hilo3 = new Hilo2();
		hilo3.numeroHilo = 3;
		hilo3.start();
		// Para crear un hilo desde aquí de una clase que ya hereda:
		Thread hiloPerro = new Thread(new Perro("Golden"));
		hiloPerro.start();

	}

}
