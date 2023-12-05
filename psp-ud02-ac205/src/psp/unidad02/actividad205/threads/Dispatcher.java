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
import java.util.Map;

import psp.unidad02.actividad205.indexes.SharedIndex;
import psp.unidad02.actividad205.loggers.IndexServerLogger;
import psp.unidad02.actividad205.utils.IndexServerFileWriter;

/**
 * Hilo maestro de la aplicación, encargado de vigilar una carpeta y crear hilos
 * hijos. Imprime el índice cuando acaba de vigilar.
 */
public class Dispatcher extends Thread {

	/** Carpeta a monitorear */
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

		// Vigilar la carpeta
		monitorFolder(folderMonitor);
		// Cuando acabe de vigilar la carpeta, imprimir el índice
		printSharedIndexMap();

		printLogs("./logs/log.txt");
	}

	private void printLogs(String folder) {
		createFolderIfNotExists(folder);
		IndexServerFileWriter writer = new IndexServerFileWriter(IndexServerLogger.getBuilder(), folder);
		writer.writeFile();
	}

	/**
	 * 
	 */
	private void printSharedIndexMap() {
		for (Map.Entry<String, StringBuilder> entry : SharedIndex.getIndexes().entrySet()) {
			System.out.println(entry.getKey() + ": ");
			System.out.println(entry.getValue().toString().trim());
			System.out.println();
		}
	}

	/**
	 * Monitorea una carpeta. Espera a eventos de entrada de archivos y crea hilos
	 * según ocurran estos eventos.
	 * 
	 * @param folderPathStr ruta de la carpeta a vigilar
	 */
	public static void monitorFolder(String folderPathStr) {
		// Verificar que la carpeta exista
		createFolderIfNotExists(folderPathStr);
		try {
			Path folderPath = Path.of(folderPathStr);
			WatchService watchService = FileSystems.getDefault().newWatchService();
			folderPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

			System.out.println("Monitoring folder: " + folderPath);

			while (true) {
				WatchKey key = watchService.take();

				boolean end = false;

				for (WatchEvent<?> event : key.pollEvents()) {
					WatchEvent.Kind<?> kind = event.kind();

					if (kind == StandardWatchEventKinds.OVERFLOW) {
						continue;
					}

					if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
						// Crear worker, si el worker detecta archivo .end, la bandera de acabar el
						// ciclo pasa a true
						end = createWorker(folderPath, event);
					}
				}

				if (!key.reset() || end) {
					System.out.println("Acabando monitorizacion.");
					break;
				}
			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea hilos worker a los que se le pasan rutas de archivos encontrados
	 * 
	 * @param folderPath
	 * @param event
	 */
	private static boolean createWorker(Path folderPath, WatchEvent<?> event) {
		Path newPath = (Path) event.context();
		Path newFilePath = folderPath.resolve(newPath);

		boolean end = false;

		// Comprobar que la ruta se refiera a un archivo de texto
		if (Files.isRegularFile(newFilePath) && newFilePath.toString().endsWith(".txt")) {
			System.out.println("File is txt, creating worker");
			WorkerThread worker = new WorkerThread(newFilePath.toString());
			worker.start();

		} else if (newFilePath.toString().endsWith(".end")) {
			end = true;
		} else {
			System.out.println("File is not txt");
		}

		return end;
	}

	/**
	 * Verifica que la carpeta exista, si no la crea.
	 * 
	 * @param folderPath carpeta a comprobar y/o crear
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
