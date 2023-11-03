package hlc.ud02.utils;

import java.io.File;
import java.security.Key;
import java.security.KeyStore;

import javax.crypto.Cipher;

/**
 * Clase que se encarga de cifrar un array de bytes
 */
public class CifraContenido {

	/** Algoritmo usado */
	private static final String ALGORITMO_RSA = "RSA";

	/** Alias de la pareja de claves */
	private static final String ALIAS = "default";

	/** Contraseña de la keystore */
	private String password;

	/** Ruta al archivo de claves */
	private String archivoClaves;

	/**
	 * Constructor
	 * 
	 * @param password
	 */
	public CifraContenido(String password, String archivoClaves) {
		setPassword(password);
		setArchivoClaves(archivoClaves);
	}

	/**
	 * Obtiene la firma del contenido de un array de bytes
	 * 
	 * @param contenido
	 * @return la firma en un array de bytes
	 */
	public byte[] getFirma(byte[] contenido) {
		byte[] mensajeCifrado = null;

		try {

			// Crear almacen de claves
			KeyStore almacen = KeyStore.getInstance(new File(archivoClaves), password.toCharArray());

			Key clavePrivada = almacen.getKey(ALIAS, password.toCharArray());

			// Cifrador
			Cipher cifrador = Cipher.getInstance(ALGORITMO_RSA);
			cifrador.init(Cipher.ENCRYPT_MODE, clavePrivada);

			// Cifrar el mensaje
			mensajeCifrado = cifrador.doFinal(contenido);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mensajeCifrado;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the archivoClaves
	 */
	public String getArchivoClaves() {
		return archivoClaves;
	}

	/**
	 * @param archivoClaves the archivoClaves to set
	 */
	public void setArchivoClaves(String archivoClaves) {
		this.archivoClaves = archivoClaves;
	}

}
