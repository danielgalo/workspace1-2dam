package psp.ud03.ejemplos.echo.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Worker extends Thread {

  // Reader para leer desde la conexión
  BufferedReader input;
  // PrintWriter para enviar a la conexión
  PrintWriter output; 

  /**
   * Constructor
   * @param connection Conexión a emplear para comunicar con el cliente
   */
  public Worker(Socket connection) throws IOException {
    // Crea los flujos de entrada y salida a partir de la conexión
    input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    output = new PrintWriter(connection.getOutputStream());
  }

  @Override
  public void run() {
    try {
      // Mientras haya mensajes por la conexión de entrada
      String request = null;
      do {
        request = input.readLine();
        // Si se pudo recibir (si la conexión no se ha cerrado)
        if (request != null) {
          // Lo convertimos a mayúsculas
          String response = request.toUpperCase();
          // Y lo enviamos como respuesta
          output.println(response);
          output.flush();
        }
      } while (request != null);
    } catch (IOException e) {
      // Error de conexion.
      // Termina
      return;
    }
  }
}
