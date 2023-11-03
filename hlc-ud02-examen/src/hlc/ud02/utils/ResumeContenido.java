package hlc.ud02.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase que se encarga de resumir el contenido de un array de bytes
 */
public class ResumeContenido {

	/** Algoritmo usado */
	private static final String ALGORITMO = "SHA-256";

	/** Fichero a resumir */
	private byte[] contenido;

	/**
	 * Constructor
	 * 
	 * @param fichero
	 */
	public ResumeContenido(byte[] contenido) {
		setContenido(contenido);
	}

	/**
	 * Obtiene el resumen del contenido de un array de bytes
	 * 
	 * @return el contenido resumido en un array de bytes
	 */
	public byte[] getContenidoReducido() {

		byte[] reduccion = null;

		try {

			// Objeto MessageDirect con el algoritmo especificado
			MessageDigest md = MessageDigest.getInstance(ALGORITMO);

			// Actualizar el objeto con el contenido a resumir
			md.update(contenido);

			// Resumir el contenido
			reduccion = md.digest();

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();

		}

		return reduccion;

	}

	/**
	 * @return the contenido
	 */
	public byte[] getContenido() {
		return contenido;
	}

	/**
	 * @param contenido the contenido to set
	 */
	public void setContenido(byte[] contenido) {
		this.contenido = contenido;
	}

}
