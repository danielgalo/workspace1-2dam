package hlc.ud04.actividad401.controlacceso;

import hlc.ud04.appsec.seguridad.autenticacion.Usuario;
import hlc.ud04.appsec.seguridad.controlacceso.ControlAcceso;
import hlc.ud04.appsec.seguridad.controlacceso.Operacion;
import hlc.ud04.appsec.seguridad.controlacceso.Recurso;

/**
 * Clase que representa un control de acceso simple Permite acceso total al
 * usuario con ID 1, sólo lectura al 2 y escritura al 3. Cualquier otro ID no
 * tiene acceso a nada La operación y el recurso se ignoran
 */
public class ControlAccesoSimple implements ControlAcceso {

	@Override
	public boolean estaPermitido(Usuario usuario, Operacion operacion, Recurso recurso) {

		// Usuario 1 acceso a everything
		if (usuario.getUid() == 1) {
			return true;
		} else if (usuario.getUid() == 2) {
			// El 2 tiene acceso sólo lectura
			return operacion == Operacion.LECTURA;
		} else if (usuario.getUid() == 3) {
			// El 3 sólo escritura
			return operacion == Operacion.ESCRITURA;
		} else {
			// Cualquier otro no tiene acceso
			return false;
		}
	}

}
