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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import psp.unidad02.actividad205.indexes.SharedIndex;
import psp.unidad02.actividad205.loggers.Logger;
import psp.unidad02.actividad205.utils.IndexServerFileWriter;

/**
 * Hilo maestro de la aplicación, encargado de vigilar una carpeta y crear hilos
 * hijos. Imprime el índice cuando acaba de vigilar.
 */
public class Dispatcher extends Thread {

	/** Nombre de la clase para usar en logs */
	private static final String CLASS_NAME = Dispatcher.class.getName();
	/** Lista de hilos worker */
	private static List<WorkerThread> workers = new ArrayList<>();

	/** Carpeta a monitorear */
	private String folderMonitor;
	/** Archivo de salida */
	private String outputFile;

	/**
	 * Constructor
	 * 
	 * @param folderMonitor
	 */
	public Dispatcher(String folderMonitor, String outputFile) {
		this.outputFile = outputFile;
		this.folderMonitor = folderMonitor;
		// Establecer prioridad máxima para el hilo
		this.setPriority(Thread.MAX_PRIORITY);
	}

	@Override
	public void run() {

		// Vigilar la carpeta
		monitorFolder(folderMonitor);
		// Esperar a que los hilos acaben
		waitForWorkers();
		// Imprimir en fichero el indice
		printIndex();

	}

	/**
	 * Imprime en el fichero de salida el índice en formato:<br>
	 * palabra<br>
	 * (fichero,linea,posicion)<br>
	 * (fichero,linea,posicion)<br>
	 * ... <br>
	 */
	private void printIndex() {

		StringBuilder builder = new StringBuilder();

		// Obtener el valor de todas las claves y guardarlo en el StringBuilder
		for (Map.Entry<String, StringBuilder> entry : SharedIndex.getIndexes().entrySet()) {

			// Guardar palabra
			builder.append(entry.getKey());
			builder.append("\n");
			// Guardar tuplas
			builder.append(entry.getValue().toString());

		}

		// Escribir en el fichero de salida el contenido del mapa
		IndexServerFileWriter writer = new IndexServerFileWriter(builder, outputFile);
		writer.writeFile();
	}

	/**
	 * Monitorea una carpeta. Espera a eventos de entrada de archivos y crea hilos
	 * según ocurran estos eventos.
	 * 
	 * @param folderPathStr ruta de la carpeta a vigilar
	 */
	private static void monitorFolder(String folderPathStr) {
		// Verificar que la carpeta exista, crearla si no existe
		createFolderIfNotExists(folderPathStr);
		try {

			// Conseguir ruta de la carpeta a vigilar, crear WatchEvent
			Path folderPath = Path.of(folderPathStr);
			WatchService watchService = FileSystems.getDefault().newWatchService();
			folderPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

			Logger.info("Monitoreando carpeta: " + folderPathStr, CLASS_NAME);

			boolean endMonitoring = false;
			int workerId = 0;

			// Vigilar carpeta
			while (true) {

				WatchKey key = watchService.take();

				for (WatchEvent<?> event : key.pollEvents()) {
					WatchEvent.Kind<?> kind = event.kind();

					// Si se ha introducido archivo
					if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
						Logger.info("Archivo detectado", CLASS_NAME);
						workerId++;
						// Crear worker, si el worker detecta archivo .end, la bandera de acabar el
						// ciclo pasa a true
						endMonitoring = createWorker(folderPath, event, workerId);
					}
				}

				// Acabar la monitorización
				if (!key.reset() || endMonitoring) {
					Logger.info("Acabando monitorización", CLASS_NAME);
					break;
				}
			}

		} catch (IOException | InterruptedException e) {
			Logger.problem("Hubo un problema monitoreando la carpeta: " + e.getMessage(), CLASS_NAME);
			// Interrumpir el hilo
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Crea hilos worker, encargados de procesar el archivo introducido.
	 * 
	 * @param folderPath ruta de la carpeta monitoreada
	 * @param event      evento de monitoreo
	 * @param workerId   identificador a asignar al worker
	 * @return true sólo si el archivo introducido no es ".end".
	 */
	private static boolean createWorker(Path folderPath, WatchEvent<?> event, int workerId) {
		Path newPath = (Path) event.context();
		Path newFilePath = folderPath.resolve(newPath);

		// Bandera para acabar la monitorización
		boolean end = false;

		// Comprobar que la ruta se refiera a un archivo de texto
		if (Files.isRegularFile(newFilePath) && newFilePath.toString().endsWith(".txt")) {

			Logger.info("Archivo es txt (" + newFilePath.toString() + "). Creando hilo", CLASS_NAME);

			// Crear hilo
			WorkerThread worker = new WorkerThread(newFilePath.toString(), workerId);
			// Lo añade a la lista de hilos
			workers.add(worker);
			worker.start();

			Logger.info("Hilo worker creado. Worker nº" + workerId, CLASS_NAME);

		} else if (newFilePath.toString().endsWith(".end")) {
			// Si el archivo es .end, la bandera para acabar la monitorización pasa a true
			Logger.info("El archivo (" + newFilePath.toString() + ") indica fin.", CLASS_NAME);
			end = true;
		} else {
			// Si es de cualquier otro tipo, ignorarlo (informar por log)
			Logger.info("Archivo NO es txt (" + newFilePath.toString() + ")", CLASS_NAME);
		}

		return end;
	}

	/**
	 * Espera a que los hilos worker acaben antes de finalizar el hilo Dispatcher.
	 */
	private void waitForWorkers() {
		for (WorkerThread worker : workers) {
			try {
				Logger.info("Esperando a que termine el hilo...", CLASS_NAME);
				worker.join();
			} catch (InterruptedException e) {
				Logger.problem("Error al esperar a que el hilo worker acabe. ID: " + worker.getId(), CLASS_NAME);
				Thread.currentThread().interrupt();
			}
		}
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
				Logger.info("Carpeta creada con éxito", CLASS_NAME);

			} else {
				Logger.problem("No se pudo crear la carpeta", CLASS_NAME);
			}

		} else {
			Logger.info("Carpeta ya existe", CLASS_NAME);
		}
	}
}
