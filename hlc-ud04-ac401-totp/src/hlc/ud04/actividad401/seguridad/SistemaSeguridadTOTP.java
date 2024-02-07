package hlc.ud04.actividad401.seguridad;

import java.util.Scanner;

import hlc.ud04.actividad401.autenticador.DesafioTOTP;
import hlc.ud04.actividad401.autenticador.RespuestaDesafioTOTP;
import hlc.ud04.appsec.seguridad.autenticacion.Autenticador;
import hlc.ud04.appsec.seguridad.autenticacion.Usuario;
import hlc.ud04.appsec.seguridad.controlacceso.ControlAcceso;
import hlc.ud04.appsec.seguridad.controlacceso.Operacion;
import hlc.ud04.appsec.seguridad.controlacceso.Recurso;
import hlc.ud04.appsec.seguridad.core.SistemaSeguridad;

/**
 * Sistema de seguridad TOTP
 */
public class SistemaSeguridadTOTP implements SistemaSeguridad {

	/** Autenticador, verifica a el usuario */
	private Autenticador autenticador;

	/** Control de acceso, verifica qué puede hacer el usuario */
	private ControlAcceso controlAcceso;

	/**
	 * @param autenticador
	 * @param controlAcceso
	 */
	public SistemaSeguridadTOTP(Autenticador autenticador, ControlAcceso controlAcceso) {
		this.autenticador = autenticador;
		this.controlAcceso = controlAcceso;
	}

	@Override
	public Usuario autentica() {

		// Pide el nombre del usuario
		Scanner sc = new Scanner(System.in);
		System.out.print("Introduce el nombre de usuario: ");
		String usuario = sc.nextLine();

		// Busca al usuario en la base de datos, devuelve un desafio con el secreto del
		// usuario
		DesafioTOTP desafio = (DesafioTOTP) autenticador.iniciaAutenticacion(usuario);

		System.out.println("Tu secreto es: " + desafio.getSecreto());

		// Pedir el pin al usuario
		System.out.print("Introduce el PIN: ");
		String pin = sc.nextLine();

		sc.close();
		// Finalizar la autenticación. Comprueba que el pin introducido en la respuesta
		// sea válido
		return autenticador.finalizaAutenticacion(desafio, new RespuestaDesafioTOTP(pin));
	}

	@Override
	public boolean estaPermitido(Usuario arg0, Operacion arg1, Recurso arg2) {
		return controlAcceso.estaPermitido(arg0, arg1, arg2);
	}

}
