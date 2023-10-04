package sp.ud02.ac2;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

public class CifraAESApp {

	/**
	 * 
	 * @param args
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeySpecException
	 */
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {

		// Procesamos los parametros
		Parametro param = new Parametro(args);

		// Cifrar el mensaje
		String mensajeCifrado = cifraMensaje(param.getMensaje(), param.getPassword());

		// Mostrar el mensaje
		System.out.println("Mensaje cifrado: " + mensajeCifrado);
	}

	/**
	 * 
	 * @param mensaje
	 * @param password
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeySpecException
	 */
	private static String cifraMensaje(String mensaje, String password)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeySpecException {

		/*
		 * Preparar clave (password a clave aes, obtener objeto secret key)
		 */
		Key clave = preparaClave(password);

		/* Objeto Cipher para cifrar, iniciarlo y cifrar */
		Cipher cifrador = Cipher.getInstance("AES");

		cifrador.init(Cipher.ENCRYPT_MODE, clave);

		byte[] mensajeCifrado = cifrador.doFinal(mensaje.getBytes());
		/* Obtener el mensaje */

		/* Pasar a base64 */
		return Base64.getEncoder().encodeToString(mensajeCifrado);

	}

	/**
	 * 
	 * @param password
	 * @return
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	private static Key preparaClave(String password) throws InvalidKeySpecException, NoSuchAlgorithmException {

		// Generador de claves, la instancio con AES
		KeyGenerator kg = KeyGenerator.getInstance("AES");
		// Inicio la clave
		kg.init(128);
		return kg.generateKey();
	}

}
