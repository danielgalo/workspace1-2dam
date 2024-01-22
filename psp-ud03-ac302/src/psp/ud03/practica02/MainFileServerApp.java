package psp.ud03.practica02;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

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

		try (DatagramSocket socket = new DatagramSocket(puerto)) {

			boolean continuarServer = true;

			while (continuarServer) {

				byte[] buffer = new byte[1024];
				DatagramPacket paqueteEntrada = new DatagramPacket(buffer, buffer.length);
				socket.receive(paqueteEntrada);

				String mensajeEntrada = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());

				if (mensajeEntrada.equalsIgnoreCase("fin")) {
					continuarServer = false;
				} else {

				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
