import java.io.File;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

import javax.crypto.Cipher;

public class PruebaAsimetrica {

	public static void main(String[] args) throws Exception {

		// Contraseña al fichero keytool
		String password = "prueba";

		// Alias del fichero keytool
		String alias = "prueba";

		// mensaje a cifrar
		String mensaje = "mensaje";

		/*
		 * Abrir archivo de claves, pasandole el fichero generado por keytool y la
		 * contraseña a este fichero pasado a char
		 */
		KeyStore almacen = KeyStore.getInstance(new File("prueba"), password.toCharArray());

		// Leer la clave privada
		Key clavePrivada = almacen.getKey(alias, password.toCharArray());

		// Sacar un certificado que contiene la clave pública
		Certificate certificado = almacen.getCertificate(alias);
		PublicKey clavePublica = certificado.getPublicKey();

		// Cifrado
		Cipher cifrado = Cipher.getInstance("RSA");
		cifrado.init(Cipher.ENCRYPT_MODE, clavePublica);

		// Cifrar el mensaje
		byte[] mensajeCifrado = cifrado.doFinal(mensaje.getBytes());

		// Si se quiere imprimir por pantalla pasar a base 64

		// Para desencriptar, no se usa la clave publica si la privada y poner
		// Cipher.DECRYPT_MODE

		// Para hacer pareja de claves
		KeyPair parejaClave = new KeyPair(clavePublica, (PrivateKey) clavePrivada);
	}

}
