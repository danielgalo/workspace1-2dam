package sp.ud02.pract02;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Clase usada para obtener una clave para encriptar o desencriptar
 */
public class Clave {

	/**
	 * Prepara y devuelve la clave para encriptar o desencriptar
	 * 
	 * @param password
	 * @return la clave
	 */
	public static Key getClave(String password) {

		// Declarar key
		Key key = null;

		try {

			// Crear factoría de claves
			SecretKeyFactory fact = SecretKeyFactory.getInstance(Constantes.KEY_ALGORITHM);

			// Preparar la especificación de la clave
			PBEKeySpec espec = new PBEKeySpec(password.toCharArray(), Constantes.SAL, Constantes.ITERACIONES,
					Constantes.KEY_SIZE);

			// Generar la clave
			SecretKey sk = fact.generateSecret(espec);

			// Convertir la clave a AES y conseguir objeto Key
			key = new SecretKeySpec(sk.getEncoded(), Constantes.AES);

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();

		} catch (InvalidKeySpecException e) {

			e.printStackTrace();
		}

		// Devolver la clave
		return key;
	}

}
