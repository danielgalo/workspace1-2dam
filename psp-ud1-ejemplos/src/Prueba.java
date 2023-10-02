
public class Prueba {

	public static void main(String[] args) {

		System.out.println("Se han pasado " + args.length + " parámetros.");
		
		for (int i = 0; i < args.length; i++) {
			System.out.println("Parámetro " + i + " = " + args[i]);
		}
		
	}

}
