package sp.ud02.pract02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class CifraFicheroAESApp {

	public static void main(String[] args) {

		// Cifrar el archivo
		cifraFichero(args[0], args[1], args[2]);

	}

	public static void cifraFichero(String ficheroEntrada, String ficheroSalida, String password) {

		// Conseguir clave
		Key clave = preparaClave(password);

		// Iniciar los flujos de ficheros
		FileInputStream ficheroOrig = null;
		FileOutputStream ficheroCifrado = null;
		CipherOutputStream flujoCifrado = null;

		try {

			// Objeto cipher
			Cipher cifrador = Cipher.getInstance(Constantes.ALGORITMO);

			// Iniciar cifrador
			cifrador.init(Cipher.ENCRYPT_MODE, clave);

			// Preparar archivo de entrada y salida
			ficheroOrig = new FileInputStream(ficheroEntrada);
			ficheroCifrado = new FileOutputStream(ficheroSalida);

			// Preparar un flujo de cifrado
			flujoCifrado = new CipherOutputStream(ficheroCifrado, cifrador);

			// Leer el archivo original y cifrarlo
			byte[] buffer = new byte[1024];
			int bytesRead;

			while ((bytesRead = ficheroOrig.read(buffer)) != -1) {
				flujoCifrado.write(buffer, 0, bytesRead);
			}

		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {

			e.printStackTrace();

		} catch (InvalidKeyException e) {

			e.printStackTrace();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			// Cerrar los flujos si no son nulos
			if (flujoCifrado != null && ficheroCifrado != null && ficheroOrig != null) {

				try {
					flujoCifrado.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

				try {
					ficheroOrig.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

				try {
					ficheroCifrado.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Prepara la clave para encriptar
	 * 
	 * @param password
	 * @return la clave
	 */
	private static Key preparaClave(String password) {

		// Declarar key
		Key key = null;

		try {

			// Crear factoría de claves
			SecretKeyFactory fact = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

			// Preparar la especificación de la clave
			PBEKeySpec espec = new PBEKeySpec(password.toCharArray(), Constantes.SAL, Constantes.ITERACIONES,
					Constantes.KEY_SIZE);

			// Generar la clave
			SecretKey sk = fact.generateSecret(espec);

			// Convertir la clave a AES y conseguir objeto Key
			key = new SecretKeySpec(sk.getEncoded(), Constantes.ALGORITMO);

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();

		} catch (InvalidKeySpecException e) {

			e.printStackTrace();
		}

		// Devolver la clave
		return key;
	}

}
