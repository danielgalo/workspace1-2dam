package psp.ud03.practica02.threads;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Worker thread. Recieves information from a server and sends a package to the
 * client
 */
public class FileWorkerThread extends Thread {

	/** Message to show when something goes wrong when accessing a file */
	private static final String KO_MESSAGE = "KO\n\r";

	/** Message to show when accessing to a file goes successfully */
	private static final String OK_MESSAGE = "OK\n\r";

	/** Socket of the server */
	private DatagramSocket serverSocket;

	/** Input package */
	private DatagramPacket inputPackage;

	/** Message from the client */
	private String inputMessage;

	/**
	 * Constructor
	 * 
	 * @param serverSocket Socket of the server
	 * @param inputPackage Input package
	 * @param inputMessage Message from the client
	 */
	public FileWorkerThread(DatagramSocket serverSocket, DatagramPacket inputPackage, String inputMessage) {
		super();
		this.serverSocket = serverSocket;
		this.inputPackage = inputPackage;
		this.inputMessage = inputMessage;
	}

	@Override
	public void run() {

		if (!inputMessage.equals("fin")) {

			try {

				// Obtain address and port from client
				InetAddress clientAddress = inputPackage.getAddress();
				int clientPort = inputPackage.getPort();

				// Obtain file if exists, convert it to bytes
				File fileFound = getFileIfExists(inputMessage);

				if (fileFound != null) {

					byte[] responseBuffer = convertFileToBytes(fileFound);
					// Send package to client
					DatagramPacket paqueteSalida = new DatagramPacket(responseBuffer, responseBuffer.length,
							clientAddress, clientPort);
					serverSocket.send(paqueteSalida);

				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Converts a file to an array of bytes
	 * 
	 * @param file
	 * @return array of bytes representing the content of the file
	 * @throws IOException if the file is too large to read into a byte array or if
	 *                     could not completely read file
	 */
	private static byte[] convertFileToBytes(File file) throws IOException {

		byte[] bytes = null;

		try (FileInputStream fis = new FileInputStream(file)) {

			long length = file.length();

			// If the length of the file is excessive
			if (length > Integer.MAX_VALUE) {
				throw new IOException(KO_MESSAGE);
			}

			// Create bytes with the files lenght
			bytes = new byte[(int) length];

			int offset = 0;
			int numRead = 0;

			while (offset < bytes.length && (numRead = fis.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}

			// Could not completely read file
			if (offset < bytes.length) {
				System.out.println(KO_MESSAGE);
			}

		}

		return bytes;
	}

	/**
	 * Gets a file from a path
	 * 
	 * @param filePath path of the file to get
	 * @return null if the File does not exist or is a directory or the file in the
	 *         specified path
	 */
	private static File getFileIfExists(String filePath) {
		File file = new File(filePath);

		// Check if the file exists
		if (file.exists() && !file.isDirectory()) {
			return file;
		} else {

			// File does not exists or is a directory
			System.out.println(KO_MESSAGE);
			return null;
		}
	}

}
