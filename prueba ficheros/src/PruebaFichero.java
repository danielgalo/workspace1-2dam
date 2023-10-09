import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PruebaFichero {

	public static void main(String[] args) {

		String archivo = "empleado.dat";

		File archivoEmp = new File(archivo);

		if (archivoEmp.exists()) {
			System.out.println("Existe");
		} else {
			System.out.println("No existe");

			try {
				FileOutputStream salida = new FileOutputStream(archivoEmp);
				salida.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			FileOutputStream salida = new FileOutputStream(archivoEmp);

			salida.write(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
