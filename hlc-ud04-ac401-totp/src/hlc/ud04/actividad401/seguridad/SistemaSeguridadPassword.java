package hlc.ud04.actividad401.seguridad;

import java.util.Scanner;

import hlc.ud04.actividad401.autenticador.DesafioPassword;
import hlc.ud04.actividad401.autenticador.RespuestaDesafioPassword;
import hlc.ud04.appsec.seguridad.autenticacion.Autenticador;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;
import hlc.ud04.appsec.seguridad.controlacceso.ControlAcceso;
import hlc.ud04.appsec.seguridad.controlacceso.Operacion;
import hlc.ud04.appsec.seguridad.controlacceso.Recurso;
import hlc.ud04.appsec.seguridad.core.SistemaSeguridad;

public class SistemaSeguridadPassword implements SistemaSeguridad {

	private Autenticador autenticador;
	private ControlAcceso controlAcceso;

	/**
	 * @param autenticador
	 * @param controlAcceso
	 */
	public SistemaSeguridadPassword(Autenticador autenticador, ControlAcceso controlAcceso) {
		this.autenticador = autenticador;
		this.controlAcceso = controlAcceso;
	}

	@Override
	public Usuario autentica() {
		// Scanner para interactuar con el usuario
		Scanner sc = new Scanner(System.in);

		// Solicita el nombre de usuario
		System.out.print("Introduzca el nombre del usuario: ");
		String usuario = sc.nextLine();

		// Crea un nuevo desafío
		DesafioPassword desafio = (DesafioPassword) autenticador.iniciaAutenticacion(usuario);

		// Solicita la contraseña
		System.out.print("Introduzca la contraseña: ");
		String password = sc.nextLine();
		// Termina la autenticacion enviando el resultado al autenticador
		// Devuelve el resultado de la autenticación
		return autenticador.finalizaAutenticacion(desafio, new RespuestaDesafioPassword(password));
	}

	@Override
	public boolean estaPermitido(Usuario usuario, Operacion operacion, Recurso recurso) {
		return controlAcceso.estaPermitido(usuario, operacion, recurso);
	}

}
