package psp.ud02.actividad203.workers;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 * Main thread that actively recieves information of an images stored in a
 * folder and gives it to children Trheads that proccess them
 */
public class Dispatcher extends Thread {

	private String inputFolder;
	private int maxWidth;
	private int maxHeigth;

	/**
	 * @param inputFolder
	 * @param maxWidth
	 * @param maxHeigth
	 */
	public Dispatcher(String inputFolder, int maxWidth, int maxHeigth) {
		super();
		this.inputFolder = inputFolder;
		this.maxWidth = maxWidth;
		this.maxHeigth = maxHeigth;
	}

	@Override
	public void run() {

		// Crear una instancia de la clase Dispatcher y empezar a monitorizar la carpeta
		monitorFolder();
	}

	/**
	 * 
	 * @param folderPath
	 */
	private void monitorFolder() {

		try {
			Path folder = Paths.get(inputFolder);
			// Crear el WatchService
			WatchService watchService = FileSystems.getDefault().newWatchService();
			folder.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

			// Iniciar el bucle de monitoreo
			while (true) {
				WatchKey key = watchService.take(); // Bloquear hasta que se reciba un evento

				// Procesar eventos
				for (WatchEvent<?> event : key.pollEvents()) {
					if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
						Path imagePath = (Path) event.context();
						System.out.println("Nueva imagen detectada: " + imagePath);

						// Crear y ejecutar el hilo de procesamiento de imagen
						Worker processorThread = new Worker(imagePath.toString(), maxWidth, maxHeigth);
						processorThread.start();
						System.out.println(imagePath.toString());
					}
				}

				// Resetear el key para futuros eventos
				key.reset();
			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
