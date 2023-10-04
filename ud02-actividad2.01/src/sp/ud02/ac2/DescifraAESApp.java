package sp.ud02.ac2;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class DescifraAESApp {

	private static byte[] SAL = { 21, 19, 8, 34, 40 };
	private static final int ITERACIONES = 10;
	static final int KEY_SIZE = 128;

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
		String mensajeCifrado = descifraMensaje(param.getMensaje(), param.getPassword());

		// Mostrar el mensaje
		System.out.println("Mensaje descifrado: " + mensajeCifrado);
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
	private static String descifraMensaje(String mensaje, String password)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeySpecException {

		// Descodifica el mensaje de manera que sea legible
		byte[] mensajeOriginal = Base64.getDecoder().decode(mensaje);

		// Preparar clave (password a clave aes, obtener objeto secret key)
		Key clave = preparaClave(password);

		/* Objeto Cipher para cifrar, iniciarlo y cifrar */
		Cipher cifrador = Cipher.getInstance("AES");

		cifrador.init(Cipher.DECRYPT_MODE, clave);

		byte[] mensajeDescifrado = cifrador.doFinal(mensajeOriginal);

		/* Pasar a base64 */
		return new String(mensajeDescifrado);
	}

	/**
	 * 
	 * @param password
	 * @return
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	private static Key preparaClave(String password) throws InvalidKeySpecException, NoSuchAlgorithmException {

		// Crea factoria de claves
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		// Prepara la especificacion de la clave
		PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), SAL, ITERACIONES, KEY_SIZE);
		// Genera la clave
		SecretKey sk = factory.generateSecret(spec);
		// La convertimos a AES y la devolvemos
		return new SecretKeySpec(sk.getEncoded(), "AES");

		/*
		 * // Generador de claves, la instancio con AES KeyGenerator kg =
		 * KeyGenerator.getInstance("AES");
		 * 
		 * // Inicio la clave kg.init(128);
		 * 
		 * return kg.generateKey();
		 * 
		 */
	}

}
