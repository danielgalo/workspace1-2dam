package sp.ud02.pract02;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;

/**
 * Clase usada para descifrar un fichero pasado por parámetro con el algoritmo
 * AES
 */
public class DescifraFicheroAESApp {

	/**
	 * Método principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Instancia de la clase Parametro para controlar los parámetros pasados
		Parametro param = new Parametro(args);

		// Cifrar el archivo
		descifraFichero(param.getRutaFicheroEntrada(), param.getRutaFicheroSalida(), param.getPassword());

	}

	/**
	 * Descifra el fichero
	 * 
	 * @param rutaFicheroEntrada fichero cifrado a descifrar
	 * @param rutaFicheroSalida  fichero ya descifrado
	 * @param password
	 */
	private static void descifraFichero(String rutaFicheroEntrada, String rutaFicheroSalida, String password) {

		// Declaro flujos
		FileInputStream ficheroCifrado = null;
		FileOutputStream ficheroDescifrado = null;
		CipherInputStream flujoDescifrado = null;

		// Conseguir clave
		Key clave = Clave.getClave(password);

		try {

			// Objeto cipher para descifrar
			Cipher cifrador = Cipher.getInstance(Constantes.AES);

			// Inicio el cifrador
			cifrador.init(Cipher.DECRYPT_MODE, clave);

			// Inicio fichero de entrada a descifrar
			ficheroCifrado = new FileInputStream(rutaFicheroEntrada);

			// Inicio fichero de salida a descifrar
			ficheroDescifrado = new FileOutputStream(rutaFicheroSalida);

			// Flujo de descifrado
			flujoDescifrado = new CipherInputStream(ficheroCifrado, cifrador);

			// Leer el archivo cifrado y descifrarlo
			byte[] buffer = new byte[1024];

			int bytesRead;

			while ((bytesRead = flujoDescifrado.read(buffer)) != -1) {
				ficheroDescifrado.write(buffer, 0, bytesRead);
			}

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IOException e) {

			e.printStackTrace();

		} finally {

			try {

				// Cierro flujos si no son nulos
				if (ficheroDescifrado != null && flujoDescifrado != null && ficheroCifrado != null) {

					ficheroDescifrado.close();
					flujoDescifrado.close();
					ficheroCifrado.close();
				}

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

	}

}
