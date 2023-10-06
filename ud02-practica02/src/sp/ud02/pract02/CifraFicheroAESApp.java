package sp.ud02.pract02;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;

/**
 * Clase para cifrar ficheros usando el algoritmo AES
 */
public class CifraFicheroAESApp {

	/**
	 * Método principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Instancia de la clase Parametro para controlar los parámetros pasados
		Parametro param = new Parametro(args);

		// Cifrar el archivo
		cifraFichero(param.getRutaFicheroEntrada(), param.getRutaFicheroSalida(), param.getPassword());

	}

	/**
	 * Cifra un fichero con el algoritmo AES
	 * 
	 * @param ficheroEntrada a cifrar
	 * @param ficheroSalida  ya cifrado
	 * @param password
	 */
	public static void cifraFichero(String ficheroEntrada, String ficheroSalida, String password) {

		// Conseguir clave
		Key clave = Clave.getClave(password);

		// Iniciar los flujos de ficheros
		FileInputStream ficheroOrig = null;
		FileOutputStream ficheroCifrado = null;
		CipherOutputStream flujoCifrado = null;

		try {

			// Objeto cipher
			Cipher cifrador = Cipher.getInstance(Constantes.AES);

			// Iniciar cifrador
			cifrador.init(Cipher.ENCRYPT_MODE, clave);

			// Preparar archivo de entrada y salida
			ficheroOrig = new FileInputStream(ficheroEntrada);
			ficheroCifrado = new FileOutputStream(ficheroSalida);

			// Preparar un flujo de cifrado
			flujoCifrado = new CipherOutputStream(ficheroCifrado, cifrador);

			byte[] buffer = new byte[1024];
			int bytesRead;

			// Recorro el archivo original y lo cifro al desitno
			while ((bytesRead = ficheroOrig.read(buffer)) != -1) {
				flujoCifrado.write(buffer, 0, bytesRead);
			}

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IOException e) {

			e.printStackTrace();

		} finally {

			// Cerrar los flujos si no son nulos
			if (flujoCifrado != null && ficheroCifrado != null && ficheroOrig != null) {

				try {

					flujoCifrado.close();
					ficheroCifrado.close();
					ficheroOrig.close();

				} catch (IOException e) {

					e.printStackTrace();
				}

			}
		}

	}

}
