package hlc.ud04.actividad401.seguridad;

import java.util.Scanner;

import hlc.ud04.actividad401.autenticador.DesafioTOTP;
import hlc.ud04.appsec.seguridad.autenticacion.Autenticador;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;
import hlc.ud04.appsec.seguridad.controlacceso.ControlAcceso;
import hlc.ud04.appsec.seguridad.controlacceso.Operacion;
import hlc.ud04.appsec.seguridad.controlacceso.Recurso;
import hlc.ud04.appsec.seguridad.core.SistemaSeguridad;

public class SistemaSeguridadTOTP implements SistemaSeguridad {

	private Autenticador autenticador;
	private ControlAcceso ControlAcceso;

	/**
	 * @param autenticador
	 * @param controlAcceso
	 */
	public SistemaSeguridadTOTP(Autenticador autenticador, ControlAcceso controlAcceso) {
		this.autenticador = autenticador;
		ControlAcceso = controlAcceso;
	}

	@Override
	public Usuario autentica() {

		// Pide el nombre del usuario
		Scanner sc = new Scanner(System.in);
		System.out.print("Introduce el nombre de usuario: ");
		String usuario = sc.nextLine();

		// Inicia el desafio (comprobar q existe, mostrar secreto, pedir y guardar pin)
		DesafioTOTP desafio = (DesafioTOTP) autenticador.iniciaAutenticacion(usuario);

		System.out.println("Tu secreto es: " + desafio.getSecreto());
		System.out.print("Introduce el PIN: ");
		String pin = sc.nextLine();

		// desafio, new respuesta(pin)
		return autenticador.finalizaAutenticacion(desafio, null);
	}

	@Override
	public boolean estaPermitido(Usuario arg0, Operacion arg1, Recurso arg2) {
		return false;
	}

}
