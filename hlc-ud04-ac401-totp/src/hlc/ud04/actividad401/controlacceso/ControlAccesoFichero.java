package hlc.ud04.actividad401.controlacceso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hlc.ud04.actividad401.persistencia.ServicioPermisosFichero;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;
import hlc.ud04.appsec.seguridad.controlacceso.ControlAcceso;
import hlc.ud04.appsec.seguridad.controlacceso.Operacion;
import hlc.ud04.appsec.seguridad.controlacceso.Recurso;

public class ControlAccesoFichero implements ControlAcceso {

	private static final String FICHERO = "permisos.txt";
	private static final String PERMISO_NULO = "none";
	private static final String PERMISO_LECTURA = "r";
	private static final String PERMISO_ESCRITURA = "w";

	@Override
	public boolean estaPermitido(Usuario usuario, Operacion operacion, Recurso arg2) {

		ServicioPermisosFichero permisosFichero = new ServicioPermisosFichero(FICHERO);
		String permisosString = permisosFichero.getPermisosUsuario(usuario);
		String[] permisos = permisosString.split("");

		List<String> listaPermisos = new ArrayList<>();

		Collections.addAll(listaPermisos, permisos);

		if (listaPermisos.contains(PERMISO_LECTURA) && listaPermisos.contains(PERMISO_ESCRITURA)) {
			return true;
		} else if (listaPermisos.contains(PERMISO_LECTURA)) {
			return operacion == Operacion.LECTURA;
		} else if (listaPermisos.contains(PERMISO_ESCRITURA)) {
			return operacion == Operacion.ESCRITURA;
		} else if (listaPermisos.contains(PERMISO_NULO)) {
			return false;
		} else {
			return false;
		}

	}

}
