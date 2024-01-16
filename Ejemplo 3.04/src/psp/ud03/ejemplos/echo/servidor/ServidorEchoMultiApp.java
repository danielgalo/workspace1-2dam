package psp.ud03.ejemplos.echo.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEchoMultiApp {
  
  // Puerto en el que se escucha por defecto
  private static final int DEFAULT_PORT = 1234;
  
  // Puerto en el que se escucha
  private int port;

  public ServidorEchoMultiApp(String[] args) {
    // Procesa los argumentos
    processArguments(args);
  }
  
  public static void main(String[] args) {
    // Creamos un objeto de la clase y lo lanzamos
    ServidorEchoMultiApp app = new ServidorEchoMultiApp(args);
    app.run();
  }

  private void run() {
    try (ServerSocket socket = new ServerSocket(port)) {
      // Creamos el socket de servidor

      // Esperamos conexiones una detrás de otra (Si se produce un error, se termina)
      for(;;) {
        //try  {
          // Aceptamos una conexion
          Socket conexion = socket.accept();
          // Crea un hilo Worker y le pasa la conexión recién recibida
          Worker worker = new Worker(conexion);
          // Lanza el worker. Este se ocupará de manejar la conexión
          worker.start();
        //} catch ()
      }
    } catch (IOException e) {
      // Error en la conexión
      System.err.println("Error en la conexión. El error original es " + e.getMessage());
      return;
    }
  }

  /**
   * Procesa los argumentos de la linea de comandos
   * @param args Argumentos
   */
  private void processArguments(String[] args) {
    // Intenta obtener el puerto a partir del primer parámetro
    try {
      port = Integer.parseInt(args[0]);
    } catch (Exception e) {
      // Si no se puede usa el puerto por defecto
      port = DEFAULT_PORT;
    }
  }
}
