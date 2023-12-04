package psp.unidad02.actividad205.threads;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 * 
 */
public class Dispatcher extends Thread {

	/** Folder to monitor */
	private String folderMonitor;

	/**
	 * Constructor
	 * 
	 * @param folderMonitor
	 */
	public Dispatcher(String folderMonitor) {
		this.folderMonitor = folderMonitor;
		// Establecer prioridad para el hilo
		this.setPriority(Thread.MAX_PRIORITY);
	}

	@Override
	public void run() {

		// Monitor the folder
		monitorFolder(folderMonitor);
	}

	/**
	 * Monitors a folder and watches for entry events
	 * 
	 * @param folderPathStr
	 */
	public static void monitorFolder(String folderPathStr) {
		createFolderIfNotExists(folderPathStr);
		try {
			Path folderPath = Path.of(folderPathStr);
			WatchService watchService = FileSystems.getDefault().newWatchService();
			folderPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

			System.out.println("Monitoring folder: " + folderPath);

			while (true) {
				WatchKey key = watchService.take();

				for (WatchEvent<?> event : key.pollEvents()) {
					WatchEvent.Kind<?> kind = event.kind();

					if (kind == StandardWatchEventKinds.OVERFLOW) {
						continue;
					}

					if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
						createWorker(folderPath, event);
					}
				}

				if (!key.reset()) {
					break;
				}
			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a worker that recieves a file path
	 * 
	 * @param folderPath
	 * @param event
	 */
	private static void createWorker(Path folderPath, WatchEvent<?> event) {
		Path newPath = (Path) event.context();
		Path newFilePath = folderPath.resolve(newPath);

		// Check if the file path is a txt file
		if (Files.isRegularFile(newFilePath) && newFilePath.toString().endsWith(".txt")) {
			System.out.println("File is txt, creating worker");
			WorkerThread worker = new WorkerThread(newFilePath.toString());
			worker.start();

		} else {
			System.out.println("File is not txt");
		}
	}

	/**
	 * Check if a folder is created, if not, creates it.
	 * 
	 * @param folderPath folder to check or/and create.
	 */
	private static void createFolderIfNotExists(String folderPath) {
		// Crear un objeto File con la ruta proporcionada
		File folder = new File(folderPath);

		// Verificar si la carpeta ya existe
		if (!folder.exists()) {
			// Intentar crear la carpeta
			boolean created = folder.mkdirs();

			if (created) {
				System.out.println("La carpeta ha sido creada con éxito.");
			} else {
				System.err.println("No se pudo crear la carpeta.");
			}
		} else {
			System.out.println("La carpeta ya existe.");
		}
	}
}
