package psp.ud03.practica02;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class MainFileClientApp {

	private static final String SERVER_DEFAULT = "localhost";
	private static final int PUERTO_DEFAULT = 4321;

	public static void main(String[] args) {

		// Procesamiento de Parámetros
		String server = "";
		int puerto = 0;

		if (args.length < 1) {
			server = SERVER_DEFAULT;
		} else {
			server = args[0];
		}

		if (args.length < 2) {
			puerto = PUERTO_DEFAULT;
		} else {
			puerto = Integer.parseInt(args[1]);
		}

		// iniciar cliente
		try (DatagramSocket socket = new DatagramSocket()) {
			InetAddress servidorDireccion = InetAddress.getByName(server);

			Scanner sc = new Scanner(System.in);

			while (true) {
				// Leer la cadena desde el usuario
				System.out.print("Introduce la ruta de un fichero (fin para terminar): ");
				String mensajeUsuario = sc.nextLine();

				byte[] bufferEnvio = mensajeUsuario.getBytes();

				// Enviar el mensaje al servidor
				DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, servidorDireccion,
						puerto);
				socket.send(paqueteEnvio);

				// Recibir la respuesta del servidor
				byte[] bufferRecepcion = new byte[1024];
				DatagramPacket paqueteRecepcion = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);
				socket.receive(paqueteRecepcion);

				String[] a = mensajeUsuario.split("/");
				String filePath = a[a.length - 1];

				try {
					File file = new File(filePath);

					// Check if the file doesn't exist, create it
					if (!file.exists()) {
						if (file.createNewFile()) {
							System.out.println("File created successfully!");
						} else {
							System.out.println("Failed to create the file.");
						}
					}

					try (FileOutputStream fos = new FileOutputStream(file)) {
						// Write the received data to the file
						fos.write(paqueteRecepcion.getData(), 0, paqueteRecepcion.getLength());

						System.out.println("File received and saved successfully!");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				String mensajeRespuesta = new String(paqueteRecepcion.getData(), 0, paqueteRecepcion.getLength());
				System.out.println("Respuesta del servidor: " + mensajeRespuesta);

				// Si el mensaje está en blanco, acabar.
				if (mensajeUsuario.equals("fin")) {
					System.out.println("Cerrando el cliente...");
					sc.close();
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
