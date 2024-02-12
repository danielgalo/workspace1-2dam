package hlc.ud04.actividad401.controlacceso;

import hlc.ud04.actividad401.persistencia.UsuarioManagementSQLite;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;
import hlc.ud04.appsec.seguridad.controlacceso.ControlAcceso;
import hlc.ud04.appsec.seguridad.controlacceso.Operacion;
import hlc.ud04.appsec.seguridad.controlacceso.Recurso;

public class ControlAccesoDB implements ControlAcceso {

	private static final String PERMISO_LEER = "r";
	private static final String PERMISO_ESCRIBIR = "w";
	private static final String PERMISO_TODO = "todo";

	/** Ruta a la base de datos */
	private static final String DB_PATH = "db/base.db";

	@Override
	public boolean estaPermitido(Usuario usuario, Operacion operacion, Recurso arg2) {

		// Servicio para leer permisos de la base de datos
		UsuarioManagementSQLite userManagement = new UsuarioManagementSQLite(DB_PATH);

		// Obtener el permiso del usuario
		String permiso = userManagement.getPermisosUsuario(usuario);

		// Devolver el permiso correspondiente
		if (permiso.equals(PERMISO_LEER)) {

			return operacion == Operacion.LECTURA;

		} else if (permiso.equals(PERMISO_ESCRIBIR)) {

			return operacion == Operacion.ESCRITURA;

		} else if (permiso.equals(PERMISO_TODO)) {

			return true;

		} else {
			return false;
		}

	}

}
