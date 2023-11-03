package ejemplo;

public class Perro extends Animal implements Runnable {

	private String raza;

	/**
	 * 
	 */
	public Perro(String raza) {
		super();

	}

	public void ladra() {
		System.out.println("guau");
	}

	/*
	 * Para hacer hilos de una clase que ya hereda de alguien, implementar la
	 * interfaz Runnable
	 */
	@Override
	public void run() {
		ladra();
	}

}
