package psp.ud03.practica02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Clase servidor
 */
public class MainFileServerApp {

	/** Puerto por defecto */
	private static final int PUERTO_DEFECTO = 4321;

	/**
	 * Método principal
	 * 
	 * @param args argumentos del programa
	 */
	public static void main(String[] args) {

		// Asignar el valor del puerto pasado por parámetros
		int puerto;

		if (args.length < 1) {
			puerto = PUERTO_DEFECTO;
		} else {
			puerto = Integer.parseInt(args[0]);
		}

		// Iniciar servidor
		try (DatagramSocket socket = new DatagramSocket(puerto)) {

			boolean continuarServer = true;

			while (continuarServer) {

				// Recibir paquete
				byte[] buffer = new byte[1024];
				DatagramPacket paqueteEntrada = new DatagramPacket(buffer, buffer.length);
				socket.receive(paqueteEntrada);

				// Convertir paquete recibido a cadena
				String mensajeEntrada = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());

				if (mensajeEntrada.equalsIgnoreCase("fin")) {
					continuarServer = false;
				} else {

					InetAddress clienteDireccion = paqueteEntrada.getAddress();
					int clientePuerto = paqueteEntrada.getPort();

					// Obtener fichero por la ruta recibida
					File fileFound = getFileIfExists(mensajeEntrada);

					// Convertirlo a bytes
					byte[] bufferRespuesta = convertFileToBytes(fileFound);

					// Enviarlo
					DatagramPacket paqueteSalida = new DatagramPacket(bufferRespuesta, bufferRespuesta.length,
							clienteDireccion, clientePuerto);
					socket.send(paqueteSalida);
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static byte[] convertFileToBytes(File file) throws IOException {
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(file);
			long length = file.length();

			if (length > Integer.MAX_VALUE) {
				throw new IOException("File is too large to read into a byte array.");
			}

			byte[] bytes = new byte[(int) length];
			int offset = 0;
			int numRead = 0;

			while (offset < bytes.length && (numRead = fis.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}

			if (offset < bytes.length) {
				throw new IOException("Could not completely read file " + file.getName());
			}

			return bytes;
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
	}

	public static File getFileIfExists(String filePath) {
		File file = new File(filePath);

		// Check if the file exists
		if (file.exists() && !file.isDirectory()) {
			return file;
		} else {
			System.out.println("File does not exist or is a directory.");
			return null;
		}
	}
}
