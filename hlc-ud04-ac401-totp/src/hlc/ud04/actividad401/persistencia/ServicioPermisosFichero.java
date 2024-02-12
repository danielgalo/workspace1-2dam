package hlc.ud04.actividad401.persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hlc.ud04.appsec.seguridad.autenticacion.Usuario;

public class ServicioPermisosFichero {

	private String rutaFichero;

	/**
	 * @param rutaFichero
	 */
	public ServicioPermisosFichero(String rutaFichero) {
		this.rutaFichero = rutaFichero;
	}

	/**
	 * Devuelve los permisos de un usuario guardado en un fichero
	 * 
	 * @param usuario
	 * @return una cadena con los permisos de un usuario
	 */
	public String getPermisosUsuario(Usuario usuario) {

		String permisosUsuario = null;
		// Mapa con <id_usuario, permisos_usuario>
		Map<String, String> mapaPermisos = new HashMap<>();
		// Id en cadena del usuario
		String idUser = String.valueOf(usuario.getUid());

		// Lee el fichero
		try (BufferedReader buffer = new BufferedReader(new FileReader(rutaFichero))) {

			String line;

			while ((line = buffer.readLine()) != null) {
				// Separar cada línea por :
				String[] lineaPermisos = line.split(":");

				// Verificar si la línea tiene el formato esperado
				if (lineaPermisos.length >= 2) {
					// Asignar un valor por defecto si alguno de los valores es vacío o nulo
					String id = (lineaPermisos[0] != null && !lineaPermisos[0].isEmpty()) ? lineaPermisos[0] : "";
					String permisos = (lineaPermisos[1] != null && !lineaPermisos[1].isEmpty()) ? lineaPermisos[1] : "";

					mapaPermisos.put(id, permisos);
				} else {
					// Manejar el caso cuando la línea no tiene el formato esperado
					permisosUsuario = "none";
				}
			}

			// Si el mapa contiene el id como clave, obtener su valor(permisos)
			if (mapaPermisos.containsKey(idUser)) {
				permisosUsuario = mapaPermisos.get(idUser);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return permisosUsuario;

	}

	/**
	 * @return the rutaFichero
	 */
	public String getRutaFichero() {
		return rutaFichero;
	}

	/**
	 * @param rutaFichero the rutaFichero to set
	 */
	public void setRutaFichero(String rutaFichero) {
		this.rutaFichero = rutaFichero;
	}

}
