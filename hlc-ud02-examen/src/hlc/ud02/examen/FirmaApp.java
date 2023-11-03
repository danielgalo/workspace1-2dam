package hlc.ud02.examen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

import hlc.ud02.utils.CifraContenido;
import hlc.ud02.utils.Parametros;
import hlc.ud02.utils.ResumeContenido;

/**
 * Realiza la firma digital de un archivo
 */
public class FirmaApp {

	/** Fichero donde se guardan las claves */
	private static final String FICHERO_CLAVES = "claves.keystore";

	/**
	 * Método principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Controlar parámetros
		Parametros param = new Parametros(args);

		// Obtener contenido fichero
		byte[] contenido = getContenidoBytes(param.getArchivo());

		// Obtener el resumen del archivo
		ResumeContenido resumen = new ResumeContenido(contenido);
		byte[] contenidoReducido = resumen.getContenidoReducido();

		// Cifrar el resumen
		CifraContenido cifrador = new CifraContenido(param.getPassword(), FICHERO_CLAVES);
		byte[] firma = cifrador.getFirma(contenidoReducido);

		// Pasar la firma de binario a Base64
		String firmaBase64 = Base64.getEncoder().encodeToString(firma);

		// Imprimir resultado
		System.out.println(firmaBase64);
	}

	/**
	 * Saca a un array de bytes el contenido de un fichero
	 * 
	 * @param rutaArchivo
	 * @return el contenido en un array de bytes
	 */
	public static byte[] getContenidoBytes(String rutaArchivo) {

		byte[] buffer = null;
		FileInputStream fis = null;
		File archivo = null;

		try {

			archivo = new File(rutaArchivo);
			fis = new FileInputStream(archivo);

			// Longitud del archivo
			int fileSize = (int) archivo.length();

			// Buffer del archivo
			buffer = new byte[fileSize];
			fis.read(buffer);

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			closeInputStream(fis);

		}

		return buffer;
	}

	/**
	 * Cierra el inputstream
	 * 
	 * @param fis
	 */
	private static void closeInputStream(FileInputStream fis) {
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
